package com.mitden.gosercuri.gestionnaire_equipement.dao.impl;


import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.mitden.gosercuri.gestionnaire_equipement.dao.IApiTcg;
import com.mitden.gosercuri.gestionnaire_equipement.dao.model.User;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Profile("default")
@Repository
public class FirebaseApiImpl implements IApiTcg {
    Logger LOG = LoggerFactory.getLogger(FirebaseApiImpl.class);
    private static final long TIMEOUT_SECONDS = 5;
    Firestore db;



    @SneakyThrows
    @Override
    public List<User> getUsers() throws Exception {

                 db = FirestoreClient.getFirestore();
                 List<User> users = new ArrayList<>();

        //asynchronously retrieve all documents
                ApiFuture<QuerySnapshot> future = db.collection("users").get();
        // future.get() blocks on response
                List<QueryDocumentSnapshot> documents = future.get().getDocuments();
                for (QueryDocumentSnapshot document : documents) {
                    User user = document.toObject(User.class);

                    users.add(user);
                }

        return users;
    }


    public User getUser(int id) throws Exception {

        db = FirestoreClient.getFirestore();
        List<User> users = new ArrayList<>();

        //asynchronously retrieve all documents
        ApiFuture<QuerySnapshot> future = db.collection("users").whereEqualTo("Id",id).get();
        // future.get() blocks on response
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
              User user = document.toObject(User.class);
            return  user;

        }

        return null;
    }
}
