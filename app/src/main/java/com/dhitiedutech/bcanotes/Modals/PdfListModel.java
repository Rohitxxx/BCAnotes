package com.dhitiedutech.bcanotes.Modals;

public class PdfListModel {
    Integer i, imgId ;
    String fileName;

    public PdfListModel(Integer i, Integer imgId, String fileName) {
        this.i = i;
        this.imgId = imgId;
        this.fileName = fileName;
    }
    public PdfListModel(Integer imgId, String fileName) {
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
