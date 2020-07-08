package com.mitden.gosercuri.gestionnaire_equipement.dao.model;

import org.opencv.core.Mat;

public class Face {
    org.bytedeco.opencv.opencv_core.Mat data;
    Long OwnerId;

    public Face() {
    }

    public Face( org.bytedeco.opencv.opencv_core.Mat data, Long ownerId) {
        this.data = data;
        OwnerId = ownerId;
    }

    public org.bytedeco.opencv.opencv_core.Mat getData() {
        return data;
    }

    public void setData( org.bytedeco.opencv.opencv_core.Mat data) {
        this.data = data;
    }

    public Long getOwnerId() {
        return OwnerId;
    }

    public void setOwnerId(Long ownerId) {
        OwnerId = ownerId;
    }
}
