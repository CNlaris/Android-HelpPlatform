package com.potech.helpform.entity;

import java.io.Serializable;

public class User implements Serializable {

    private int id;
    private String loginName;
    private String loginPassword;
    private String userName;
    private String uuid;

    public User() {
    }

    public User(int id, String loginName, String loginPassword, String userName, String uuid) {
        this.id = id;
        this.loginName = loginName;
        this.loginPassword = loginPassword;
        this.userName = userName;
        this.uuid = uuid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
