package com.dhitiedutech.bcanotes.Modals;

public class WebData {
    String fileUrl;
    String htmlContent;
    String htmlTags;
    Integer img,i;

    public WebData(String fileUrl, String htmlContent, String htmlTags, Integer img, Integer i) {
        this.fileUrl = fileUrl;
        this.htmlContent = htmlContent;
        this.htmlTags = htmlTags;
        this.img = img;
        this.i = i;
    }

    public WebData(String fileUrl, String htmlContent, String htmlTags, Integer img) {
        this.fileUrl = fileUrl;
        this.htmlContent = htmlContent;
        this.htmlTags = htmlTags;
        this.img=img;
    }


    public WebData(String fileUrl, String htmlContent, Integer img) {
        this.fileUrl = fileUrl;
        this.htmlContent = htmlContent;
        this.img=img;
    }

    public WebData(String fileUrl, String htmlContent) {
        this.fileUrl = fileUrl;
        this.htmlContent = htmlContent;
    }

    public WebData(){}

    public Integer getI() {
        return i;
    }

    public void setI(Integer i) {
        this.i = i;
    }

    public Integer getImg() {
        return img;
    }

    public void setImg(Integer img) {
        this.img = img;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }

    public String getHtmlTags() {
        return htmlTags;
    }

    public void setHtmlTags(String htmlTags) {
        this.htmlTags = htmlTags;
    }
}
