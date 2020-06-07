package com.gosecuri_epsib3.beans;

public class Equipement {
    private String Id;
    private String Label;
    public Equipement(String Id, String Label){
        this.Id = Id;
        this.Label = Label;
    }

    public String getId() {
        return this.Id;
    }


    public String getLabel() {
        return this.Label;
    }
}

