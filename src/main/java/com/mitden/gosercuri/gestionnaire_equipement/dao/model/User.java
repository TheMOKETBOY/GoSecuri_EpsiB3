package com.mitden.gosercuri.gestionnaire_equipement.dao.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class User {
    Long Id;
    String Nom;
    String Prenom;
    String Avatar;

    String Email;
    List<String> Visages = new ArrayList<>();
    Equipements Equipements;

    public User() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String prenom) {
        Prenom = prenom;
    }

    public String getAvatar() {
        return Avatar;
    }

    public void setAvatar(String avatar) {
        Avatar = avatar;
    }



    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }



}
