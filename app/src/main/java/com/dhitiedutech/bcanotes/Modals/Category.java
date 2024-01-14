package com.dhitiedutech.bcanotes.Modals;


public class Category {
    Integer imgView;
    String tvName;

    public Category(Integer imgView, String tvName) {
        this.imgView = imgView;
        this.tvName = tvName;
    }
    public Category(){}

    public Integer getImgView() {
        return imgView;
    }

    public void setImgView(Integer imgView) {
        this.imgView = imgView;
    }

    public String getTvName() {
        return tvName;
    }

    public void setTvName(String tvName) {
        this.tvName = tvName;
    }
}
