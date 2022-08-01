package com.shubhamk.myapplicationWhatsapp.Model;

public class Users {

    String profilepic, UserName, mail, password, userId, lastmsg;

    // constructor


    public Users(String profilepic, String userName, String mail, String password, String userId, String lastmsg) {
        this.profilepic = profilepic;
        UserName = userName;
        this.mail = mail;
        this.password = password;
        this.userId = userId;
        this.lastmsg = lastmsg;
    }
    // Empty constructor

    public Users() {
    }

    // signup constructor

    public Users(String userName, String mail, String password) {
        UserName = userName;
        this.mail = mail;
        this.password = password;
    }


    // getters and setters

    public String getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(String profilepic) {
        this.profilepic = profilepic;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
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

    public String getLastmsg() {
        return lastmsg;
    }

    public void setLastmsg(String lastmsg) {
        this.lastmsg = lastmsg;
    }
}
