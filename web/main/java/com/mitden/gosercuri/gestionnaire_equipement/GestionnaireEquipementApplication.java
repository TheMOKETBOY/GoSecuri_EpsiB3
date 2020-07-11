package com.mitden.gosercuri.gestionnaire_equipement;
import com.mitden.gosercuri.gestionnaire_equipement.manager.FaceAuthManager;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.opencv.opencv_java;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GestionnaireEquipementApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestionnaireEquipementApplication.class, args);
        Loader.load(opencv_java.class);
        FaceAuthManager faceAuthManager = new FaceAuthManager();
        faceAuthManager.startTrainning();

    }

}
