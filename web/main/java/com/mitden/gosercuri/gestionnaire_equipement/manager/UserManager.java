package com.mitden.gosercuri.gestionnaire_equipement.manager;

import com.mitden.gosercuri.gestionnaire_equipement.api.model.UserFront;
import com.mitden.gosercuri.gestionnaire_equipement.dao.impl.FirebaseApiImpl;
import com.mitden.gosercuri.gestionnaire_equipement.dao.model.User;
import com.mitden.gosercuri.gestionnaire_equipement.util.exception.TechnicalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserManager {

    Logger LOG = LoggerFactory.getLogger(UserManager.class);

    @Autowired
    FirebaseApiImpl firebaseApiTcg;



    public List<UserFront> getUsers() throws Exception {
        List<UserFront> result = new ArrayList<>();

        List<User> usersFirebase = firebaseApiTcg.getUsers();

        for (User userFirebase : usersFirebase) {

            //si le personnage est mort avant la saison courante, on ne l'affiche pas

                //on transforme l'objet firebase en objet "front"
                UserFront userFront = convertToUserFront(userFirebase);
                result.add(userFront);

        }
        return result;
    }

    /**
     *
     *
     *
     *
     * @return
     * @throws TechnicalException
     */
    public UserFront convertToUserFront(User user) throws TechnicalException {
        UserFront result = new UserFront();
        //l'id
        result.setId(user.getId().toString());

        result.setPrenom(user.getPrenom());
        result.setNom(user.getNom());
        result.setAvatar(user.getAvatar());
        return result;


    }


}
