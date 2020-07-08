package com.mitden.gosercuri.gestionnaire_equipement.api.model;

public class FaceResult {
    String visage;
    String identity;
    Boolean isKnow;

    public String getVisage() {
        return visage;
    }

    public void setVisage(String visage) {
        this.visage = visage;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public Boolean getKnow() {
        return isKnow;
    }

    public void setKnow(Boolean know) {
        isKnow = know;
    }

    public FaceResult() {
    }

    public FaceResult(String visage, String identity, Boolean isKnow) {
        this.visage = visage;
        this.identity = identity;
        this.isKnow = isKnow;
    }
}
