package com.mitden.gosercuri.gestionnaire_equipement.dao.model;

import javax.print.DocFlavor;

public class Licence {
    String dtvalidite;
    String nolicence;

    public Licence(String dtvalidite, String nolicence) {
        this.dtvalidite = dtvalidite;
        this.nolicence = nolicence;
    }

    public Licence() {
    }

    public String getDtvalidite() {
        return dtvalidite;
    }

    public void setDtvalidite(String dtvalidite) {
        this.dtvalidite = dtvalidite;
    }

    public String getNolicence() {
        return nolicence;
    }

    public void setNolicence(String nolicence) {
        this.nolicence = nolicence;
    }
}
