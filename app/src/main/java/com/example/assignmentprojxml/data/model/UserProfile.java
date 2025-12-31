package com.example.assignmentprojxml.data.model;

import com.google.gson.annotations.SerializedName;

public class UserProfile {
    @SerializedName("name")
    private String name;
    
    @SerializedName("profile_picture")
    private String profilePicture;
    
    @SerializedName("email")
    private String email;
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getProfilePicture() {
        return profilePicture;
    }
    
    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
}