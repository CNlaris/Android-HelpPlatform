package com.potech.helpform.entity;

import java.io.Serializable;

public class HelperPostBar implements Serializable {

    private int id;
    private String helpUsername;
    private String helpUserUUid;
    private String helpLocation;
    private int trappedPeopleNumber;
    private String trappedTime;
    private String trappedDescription;
    private String needHelpKind;
    private String contactPeople;
    private String contactPhoneNumber;
    private String releaseTime;
    private String urgencyDegree;
    private boolean isGetHelp;

    public HelperPostBar() {

    }

    public HelperPostBar(int id, String helpUsername, String helpUserUUid, String helpLocation, int trappedPeopleNumber, String trappedTime, String trappedDescription, String needHelpKind, String contactPeople, String contactPhoneNumber, String releaseTime, String urgencyDegree, boolean isGetHelp) {
        this.id = id;
        this.helpUsername = helpUsername;
        this.helpUserUUid = helpUserUUid;
        this.helpLocation = helpLocation;
        this.trappedPeopleNumber = trappedPeopleNumber;
        this.trappedTime = trappedTime;
        this.trappedDescription = trappedDescription;
        this.needHelpKind = needHelpKind;
        this.contactPeople = contactPeople;
        this.contactPhoneNumber = contactPhoneNumber;
        this.releaseTime = releaseTime;
        this.urgencyDegree = urgencyDegree;
        this.isGetHelp = isGetHelp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHelpUsername() {
        return helpUsername;
    }

    public void setHelpUsername(String helpUsername) {
        this.helpUsername = helpUsername;
    }

    public String getHelpUserUUid() {
        return helpUserUUid;
    }

    public void setHelpUserUUid(String helpUserUUid) {
        this.helpUserUUid = helpUserUUid;
    }

    public String getHelpLocation() {
        return helpLocation;
    }

    public void setHelpLocation(String helpLocation) {
        this.helpLocation = helpLocation;
    }

    public int getTrappedPeopleNumber() {
        return trappedPeopleNumber;
    }

    public void setTrappedPeopleNumber(int trappedPeopleNumber) {
        this.trappedPeopleNumber = trappedPeopleNumber;
    }

    public String getTrappedTime() {
        return trappedTime;
    }

    public void setTrappedTime(String trappedTime) {
        this.trappedTime = trappedTime;
    }

    public String getTrappedDescription() {
        return trappedDescription;
    }

    public void setTrappedDescription(String trappedDescription) {
        this.trappedDescription = trappedDescription;
    }

    public String getNeedHelpKind() {
        return needHelpKind;
    }

    public void setNeedHelpKind(String needHelpKind) {
        this.needHelpKind = needHelpKind;
    }

    public String getContactPeople() {
        return contactPeople;
    }

    public void setContactPeople(String contactPeople) {
        this.contactPeople = contactPeople;
    }

    public String getContactPhoneNumber() {
        return contactPhoneNumber;
    }

    public void setContactPhoneNumber(String contactPhoneNumber) {
        this.contactPhoneNumber = contactPhoneNumber;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getUrgencyDegree() {
        return urgencyDegree;
    }

    public void setUrgencyDegree(String urgencyDegree) {
        this.urgencyDegree = urgencyDegree;
    }

    public boolean isGetHelp() {
        return isGetHelp;
    }

    public void setGetHelp(boolean getHelp) {
        isGetHelp = getHelp;
    }

    public String toStringWithoutId(){
        return "\"" + helpUsername+"\",\" "+helpUserUUid + "\",\" "+helpLocation + "\",\" " + String.valueOf(trappedPeopleNumber) + "\",\" "+ trappedTime + "\",\" "+trappedDescription + "\",\" "+needHelpKind + "\",\" "+contactPeople+"\",\" " +contactPhoneNumber +"\",\" "+releaseTime + "\",\" "+urgencyDegree+"\",\"" + String.valueOf(isGetHelp)+"\"";
    }
}
