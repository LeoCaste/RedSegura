package com.redsegura.models;

import java.util.List;

public class UserModel {
    private String username;
    private String phoneNumber;
    private List<String> emergencyContacts;

    public UserModel() {}

    public UserModel(String username, String phoneNumber, List<String> emergencyContacts) {
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.emergencyContacts = emergencyContacts;
    }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getPhoneNumber() { return phoneNumber; }

    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public List<String> getEmergencyContacts() { return emergencyContacts; }

    public void setEmergencyContacts(List<String> emergencyContacts) { this.emergencyContacts = emergencyContacts; }
}
