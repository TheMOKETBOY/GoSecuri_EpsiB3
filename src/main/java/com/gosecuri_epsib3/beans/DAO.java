package com.gosecuri_epsib3.beans;

import java.util.ArrayList;

public  class DAO {

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

}
