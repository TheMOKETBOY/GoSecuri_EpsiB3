package com.mitden.gosercuri.gestionnaire_equipement.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * un classe qui représente l'objet user côté front
 *
 *
 */
@ApiModel(description = "Class representing a user.")

public class UserFront {

    /**
     * id
     */
    @ApiModelProperty(notes = "id", example = "52qdqs-qdsd-sdsdqd-qdqds-qdqd-qdqd-qdqd")
    String id;

    @ApiModelProperty(notes = "prenom",example = "John")
    String prenom;

    @ApiModelProperty(notes = "nom",example = "Doe")
    String nom;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @ApiModelProperty(notes = "avatar",example = "img")
    String avatar;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public UserFront() {

    }

    public UserFront(String id, String prenom, String nom,String avatar) {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
        this.avatar = avatar;
    }
}
