package com.example.assignmentprojxml.data.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import java.util.ArrayList;

public class NavigationResponse {
    @SerializedName("result")
    private NavigationResult result;
    
    @SerializedName("session_id")
    private String sessionId;
    
    // Legacy fields for compatibility
    @SerializedName("status")
    private String status;
    
    @SerializedName("message")
    private String message;
    
    @SerializedName("data")
    private NavigationData data;
    
    // For compatibility with existing code
    public String getStatus() {
        return result != null ? "success" : (status != null ? status : "error");
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getMessage() {
        return message != null ? message : "Data loaded successfully";
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public NavigationData getData() {
        // If we have legacy data, return it
        if (data != null) {
            return data;
        }
        
        // Convert NavigationResult to NavigationData for compatibility
        if (result == null) return null;
        
        NavigationData convertedData = new NavigationData();
        
        // Convert user profile
        UserProfile userProfile = new UserProfile();
        userProfile.setName(result.getTitle());
        userProfile.setProfilePicture(result.getUserPhoto());
        userProfile.setEmail(""); // Not provided in API
        convertedData.setUserProfile(userProfile);
        
        // Convert menus to navigation items
        if (result.getMenus() != null) {
            List<NavigationItem> navigationItems = new ArrayList<>();
            
            // Group menus by type
            NavigationItem appsSection = new NavigationItem();
            appsSection.setTitle("Apps");
            appsSection.setType("apps");
            List<MenuItem> appsItems = new ArrayList<>();
            
            NavigationItem helpSection = new NavigationItem();
            helpSection.setTitle("Help More");
            helpSection.setType("help");
            List<MenuItem> helpItems = new ArrayList<>();
            
            for (MenuItemReal menuReal : result.getMenus()) {
                if (menuReal.getType() == 1) { // Regular menu items
                    MenuItem menuItem = new MenuItem();
                    menuItem.setTitle(menuReal.getLabel());
                    menuItem.setIcon(menuReal.getIcon());
                    menuItem.setUrl(menuReal.getUrl());
                    menuItem.setColor("#F5F5F5"); // Default color
                    
                    // Categorize based on class or label
                    String className = menuReal.getClassName();
                    if (isAppItem(className, menuReal.getLabel())) {
                        appsItems.add(menuItem);
                    } else if (isHelpItem(className, menuReal.getLabel())) {
                        helpItems.add(menuItem);
                    }
                }
            }
            
            appsSection.setItems(appsItems);
            helpSection.setItems(helpItems);
            
            navigationItems.add(appsSection);
            navigationItems.add(helpSection);
            
            convertedData.setNavigationItems(navigationItems);
        }
        
        return convertedData;
    }
    
    private boolean isAppItem(String className, String label) {
        if (className == null) return false;
        
        return className.contains("core_main_") ||
               className.contains("sesblog") ||
               className.contains("sesalbum") ||
               className.contains("sesjob") ||
               className.contains("sescrowdfunding") ||
               className.contains("sesevent") ||
               className.contains("sesgroup") ||
               className.contains("sesvideo") ||
               className.contains("sesmusic") ||
               className.contains("egames") ||
               className.contains("estore") ||
               className.contains("sesnews") ||
               className.contains("courses") ||
               className.contains("eclassroom") ||
               className.contains("sesquote") ||
               className.contains("sesadvpoll") ||
               className.contains("sesrecipe") ||
               className.contains("sescontest") ||
               className.contains("sesforum") ||
               className.contains("sesqa") ||
               className.contains("sesarticle") ||
               className.contains("booking") ||
               className.contains("eresume") ||
               className.contains("sesprayer") ||
               className.contains("sesthought") ||
               className.contains("seswishe") ||
               className.contains("sespage") ||
               className.contains("sesbusiness");
    }
    
    private boolean isHelpItem(String className, String label) {
        if (className == null) return false;
        
        return className.contains("core_footer_") ||
               className.contains("core_main_settings") ||
               className.equals("core_support") ||
               className.equals("core_wallet") ||
               label.equals("Settings") ||
               label.equals("Privacy") ||
               label.equals("Contact Us") ||
               label.equals("Terms of Service") ||
               label.equals("Rate Us") ||
               label.equals("Support") ||
               label.equals("Wallet");
    }
    
    public void setData(NavigationData data) {
        this.data = data;
    }
    
    // Real API getters/setters
    public NavigationResult getResult() {
        return result;
    }
    
    public void setResult(NavigationResult result) {
        this.result = result;
    }
    
    public String getSessionId() {
        return sessionId;
    }
    
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}