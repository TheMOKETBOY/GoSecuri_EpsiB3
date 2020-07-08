package com.mitden.gosercuri.gestionnaire_equipement.manager;

import com.mitden.gosercuri.gestionnaire_equipement.api.model.FaceResult;
import com.mitden.gosercuri.gestionnaire_equipement.dao.impl.FirebaseApiImpl;
import com.mitden.gosercuri.gestionnaire_equipement.dao.model.Face;
import com.mitden.gosercuri.gestionnaire_equipement.dao.model.User;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.opencv_face.LBPHFaceRecognizer;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.core.MatOfRect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.IntBuffer;
import java.util.*;

import static org.opencv.core.CvType.CV_32SC1;

@Service
public class FaceAuthManager {

    Logger LOG = LoggerFactory.getLogger(FaceAuthManager.class);

    @Autowired
    FirebaseApiImpl firebaseApiImpl = new FirebaseApiImpl();
    org.bytedeco.opencv.opencv_face.FaceRecognizer faceRecognizer =  LBPHFaceRecognizer.create();

    private Resource faceResource = new ClassPathResource("haarcascades/haarcascade_frontalface_alt.xml");
    private Resource trainData = new ClassPathResource("data/trainData.xml");
    public List<FaceResult> getIdentify(String imgbase64){


List<FaceResult> faceResults = new ArrayList<>();
        byte[] imgBytes = Base64.getDecoder().decode(imgbase64);


        Mat mat = Imgcodecs.imdecode(new MatOfByte(imgBytes), Imgcodecs.IMREAD_GRAYSCALE);

         List<Mat> faces = detectFaces(mat);
        for (Mat face :faces ) {

            faceResults.add(identifedFace(face));

        }






        return faceResults;
    }


public String MathToBases64(Mat input) throws UnsupportedEncodingException {
    MatOfByte buffer = new MatOfByte();
    // encode the frame in the buffer, according to the PNG format
    Imgcodecs.imencode(".png", input, buffer);
    String  encodedfile = new String(Base64.getEncoder().encode(buffer.toArray()), "UTF-8");
return encodedfile;
}


    public void startTrainning(){
        LOG.info("Start Trainning ....");
        LOG.info("Load Faces...");
        List<Face> faces = loadFace();
System.out.println(faces.size());
        org.bytedeco.opencv.opencv_core.Mat labelsMat = new org.bytedeco.opencv.opencv_core.Mat(faces.size(), 1, CV_32SC1);
        IntBuffer labelsBuf = labelsMat.createBuffer();
        MatVector visagesVect = new MatVector(faces.size());
        for (int i = 0; i < faces.size(); i++) {
            System.out.println(i);
            visagesVect.put(i, faces.get(i).getData()) ;
            labelsBuf.put(i,Integer.decode(faces.get(i).getOwnerId().toString()));
        }



        try {
            faceRecognizer.train(visagesVect,labelsMat);
            faceRecognizer.save(trainData.getFile().getAbsolutePath());
        } catch (IOException exception) {
            exception.printStackTrace();
        }


    }



    public FaceResult identifedFace(Mat visage){
        FaceResult faceResult = new FaceResult();
        boolean isKnow = false;
        String out = "La personne est inconnue ";
        try {
            faceRecognizer.read(trainData.getFile().getAbsolutePath());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        org.bytedeco.opencv.opencv_core.Mat imgmat =   new org.bytedeco.opencv.opencv_core.Mat((Pointer)null) { { address = visage.getNativeObjAddr(); } };

        int[] label = new int[1];
        double[] confidence = new double[1];
        try {
            faceRecognizer.predict(imgmat,label,confidence);
        }catch (Exception e){
        System.out.println(e.getMessage());
        }

        if (confidence[0] <= 95){
            isKnow = true;

            try {
              User user = (User) firebaseApiImpl.getUser(label[0]);


                    out = "Personne : "+ user.getPrenom()+" "+user.getNom()+ " detected";
            } catch (Exception e) {
                e.printStackTrace();
                out = "Personne :  not in db detected";
            }

        }
        try {
            faceResult.setVisage(MathToBases64(visage));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        faceResult.setIdentity(out);
        faceResult.setKnow(isKnow);
       System.out.println(out);
    return faceResult;
    }




    public List<Mat> detectFaces(Mat inputImg){
        List<Mat> visages = new ArrayList<Mat>();
        MatOfRect faceDetections = new MatOfRect();

        //Instantiating the CascadeClassifier

        CascadeClassifier classifier = null;
        try {
            classifier = new CascadeClassifier(faceResource.getFile().getAbsolutePath());
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        //Detecting the face in the snap
        classifier.detectMultiScale(inputImg, faceDetections,1.3,7);

        String out = String.format("Detected %s faces",
                faceDetections.toArray().length);
        System.out.println(out);

        for (Rect rect:faceDetections.toArray()) {
            Mat resizeimage = new Mat();
            Size sz = new Size(100,100);
            Imgproc.resize(  new Mat(inputImg,rect), resizeimage, sz );
            visages.add(resizeimage);
        }


    return visages;
    }



    public  List<Face>loadFace(){
        List<Face> datatraining =  new ArrayList<>();
        try {
      List<User> users =  firebaseApiImpl.getUsers();

          for (User u:users) {
              int count  = 0;
              for (String visage:u.getVisages()) {

                  try {
                      byte[] imgBytes = Base64.getDecoder().decode(visage);
                      Mat mat = Imgcodecs.imdecode(new MatOfByte(imgBytes), Imgcodecs.IMREAD_GRAYSCALE);
                      org.bytedeco.opencv.opencv_core.Mat imgmat =   new org.bytedeco.opencv.opencv_core.Mat((Pointer)null) { { address = mat.getNativeObjAddr(); } };
                      datatraining.add(new Face(imgmat,u.getId()));
                  }catch (Exception e){
                      System.out.println("Error :"+ u.getNom()+" "+u.getPrenom()+"     visage n°"+count+" of array" );

                  }

                  count ++;

              }
          }





        } catch (Exception e) {
            e.printStackTrace();
        }
        return datatraining;
    }



}
