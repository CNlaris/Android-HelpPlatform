package com.potech.helpform.entity;

import java.io.Serializable;

public class HelpPostBarMessage implements Serializable {

    private int id;
    private int helpPostBarId;
    private String messageUsername;
    private String message;
    private String messageUsernameUUid;

    public HelpPostBarMessage() {
    }

    public HelpPostBarMessage(int id, int helpPostBarId, String messageUsername, String message, String messageUsernameUUid) {
        this.id = id;
        this.helpPostBarId = helpPostBarId;
        this.messageUsername = messageUsername;
        this.message = message;
        this.messageUsernameUUid = messageUsernameUUid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHelpPostBarId() {
        return helpPostBarId;
    }

    public void setHelpPostBarId(int helpPostBarId) {
        this.helpPostBarId = helpPostBarId;
    }

    public String getMessageUsername() {
        return messageUsername;
    }

    public void setMessageUsername(String messageUsername) {
        this.messageUsername = messageUsername;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageUsernameUUid() {
        return messageUsernameUUid;
    }

    public void setMessageUsernameUUid(String messageUsernameUUid) {
        this.messageUsernameUUid = messageUsernameUUid;
    }

    public String toStringWithoutId(){
        return "\"" + helpPostBarId +"\",\"" + messageUsername+ "\",\"" + message + "\",\"" + messageUsernameUUid + "\"";
    }
}
