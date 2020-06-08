package com.gosecuri_epsib3.beans;


import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public  class DAO {

    public DAO() {

    }



    public static ArrayList<Equipement>getEquipements(){
        ArrayList<Equipement> equipements = new ArrayList<Equipement>();
        equipements.add(new Equipement("MOUSQUETON","Mousqueton"));
        equipements.add(new Equipement("GANT","Gant d'interventin"));
        equipements.add(new Equipement("CEINTURE","Ceinture de sécurité tactique"));
        equipements.add(new Equipement("DETECTEUR_METEAUX","Détecteur de métaux"));
        equipements.add(new Equipement("BRASSARD","Brassard de sécurité"));
        equipements.add(new Equipement("LAMPE_TORCHE","Lampe Torche"));
        return equipements;

    }
    //Jeux de test
    public static ArrayList<User> getUsers() throws IOException, ExecutionException, InterruptedException {
        ArrayList<User>users = new ArrayList<User>();




        return users;

    }

    public static String getValeurChamp(HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur;
        }
    }

}
