package com.dhitiedutech.bcanotes.Modals;

public class BarModel {
    Integer i, imgId ;
    String fileName;

    public BarModel(Integer i, Integer imgId, String fileName) {
        this.i = i;
        this.imgId = imgId;
        this.fileName = fileName;
    }
    public BarModel(Integer imgId, String fileName) {
        this.imgId = imgId;
        this.fileName = fileName;
    }
    public Integer getI() {
        return i;
    }

    public void setI(Integer i) {
        this.i = i;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(Integer imgId) {
        this.imgId = imgId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}
