package com.example.assignmentprojxml.data.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class NavigationData {
    @SerializedName("user_profile")
    private UserProfile userProfile;
    
    @SerializedName("navigation_items")
    private List<NavigationItem> navigationItems;
    
    public UserProfile getUserProfile() {
        return userProfile;
    }
    
    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }
    
    public List<NavigationItem> getNavigationItems() {
        return navigationItems;
    }
    
    public void setNavigationItems(List<NavigationItem> navigationItems) {
        this.navigationItems = navigationItems;
    }
}