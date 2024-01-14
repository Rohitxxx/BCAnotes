package com.dhitiedutech.bcanotes.Modals;

public class Users {
    String mail,password,userId,userName,contactNo;

    public Users(String mail, String password, String userId, String userName, String contactNo) {
        this.mail = mail;
        this.password = password;
        this.userId = userId;
        this.userName = userName;
        this.contactNo = contactNo;
    }

//    public Users(String mail, String password, String userId, String userName) {
//        this.mail = mail;
//        this.password = password;
//        this.userId = userId;
//        this.userName = userName;
//    }
    public Users(String mail, String password, String userName,String contactNo) {
        this.mail = mail;
        this.password = password;
        this.userName = userName;
        this.contactNo=contactNo;
    }
    public Users(){}

    public Users(String mail, String password, String userName) {
        this.mail = mail;
        this.password = password;
        this.userName = userName;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
