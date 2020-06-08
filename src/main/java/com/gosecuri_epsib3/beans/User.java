package com.gosecuri_epsib3.beans;

import java.io.File;
import java.util.ArrayList;

public class User {
    private String Id;
    private String FirstName;
    private String LastName;
    private ArrayList<File> Visages = new ArrayList<File>();

    public ArrayList<String> getRoles() {
        return Roles;
    }

    public void setRoles(ArrayList<String> roles) {
        Roles = roles;
    }

    public void addRole(String value){
        this.Roles.add(value);
    }

    private ArrayList<String> Roles = new ArrayList<String>();
    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }
    public String getFullName(){
        return this.FirstName+" "+this.LastName;
    }
    public ArrayList<File> getVisages() {
        return Visages;
    }

    public void setVisages(ArrayList<File> visages) {
        Visages = visages;
    }
    public void addVisage(File visage){
        this.Visages.add(visage);
    }
}
