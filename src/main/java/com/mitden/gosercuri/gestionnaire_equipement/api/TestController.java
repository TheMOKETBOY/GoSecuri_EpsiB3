package com.mitden.gosercuri.gestionnaire_equipement.api;

import com.mitden.gosercuri.gestionnaire_equipement.api.model.FaceResult;
import com.mitden.gosercuri.gestionnaire_equipement.dao.IApiTcg;
import com.mitden.gosercuri.gestionnaire_equipement.dao.impl.FirebaseApiImpl;
import com.mitden.gosercuri.gestionnaire_equipement.manager.FaceAuthManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {
    IApiTcg apiTcg = new FirebaseApiImpl();
    @Autowired
    com.mitden.gosercuri.gestionnaire_equipement.manager.UserManager UserManager;

    @Autowired
    FaceAuthManager faceAuthManager;


    @RequestMapping(value = "/?facesdb", method = RequestMethod.GET)
    public String getUsers(@RequestParam(value = "season", defaultValue = "1") String season) throws Exception {
        faceAuthManager.startTrainning();

        return "Trainning" ;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public List<FaceResult> FaceIdentify(@RequestBody String imgbase64) throws Exception {



        return faceAuthManager.getIdentify(imgbase64);
    }
    @RequestMapping(value = "/detectedfaces", method = RequestMethod.POST)
    public List<FaceResult> getFaces(@RequestBody List<String> imgbase64) throws Exception {

        List<FaceResult> faceResults = new ArrayList<>();
        System.out.println("Images envoyés"+ imgbase64.size());



        for (String image:imgbase64 ) {

            List<FaceResult> result = faceAuthManager.getIdentify(image);

            for (FaceResult f:result) {
                faceResults.add(f);
            }
        }



        return faceResults;
    }

}
