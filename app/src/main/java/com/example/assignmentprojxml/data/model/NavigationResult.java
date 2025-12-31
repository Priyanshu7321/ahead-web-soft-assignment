package com.example.assignmentprojxml.data.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class NavigationResult {
    @SerializedName("title")
    private String title;
    
    @SerializedName("user_photo")
    private String userPhoto;
    
    @SerializedName("cover_photo")
    private String coverPhoto;
    
    @SerializedName("wallet_amount")
    private String walletAmount;
    
    @SerializedName("wallet_url")
    private String walletUrl;
    
    @SerializedName("menus")
    private List<MenuItemReal> menus;
    
    @SerializedName("notification_count")
    private int notificationCount;
    
    @SerializedName("friend_req_count")
    private int friendReqCount;
    
    @SerializedName("message_count")
    private int messageCount;
    
    @SerializedName("loggedin_user_id")
    private int loggedinUserId;
    
    // Getters and setters
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getUserPhoto() {
        return userPhoto;
    }
    
    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }
    
    public String getCoverPhoto() {
        return coverPhoto;
    }
    
    public void setCoverPhoto(String coverPhoto) {
        this.coverPhoto = coverPhoto;
    }
    
    public String getWalletAmount() {
        return walletAmount;
    }
    
    public void setWalletAmount(String walletAmount) {
        this.walletAmount = walletAmount;
    }
    
    public String getWalletUrl() {
        return walletUrl;
    }
    
    public void setWalletUrl(String walletUrl) {
        this.walletUrl = walletUrl;
    }
    
    public List<MenuItemReal> getMenus() {
        return menus;
    }
    
    public void setMenus(List<MenuItemReal> menus) {
        this.menus = menus;
    }
    
    public int getNotificationCount() {
        return notificationCount;
    }
    
    public void setNotificationCount(int notificationCount) {
        this.notificationCount = notificationCount;
    }
    
    public int getFriendReqCount() {
        return friendReqCount;
    }
    
    public void setFriendReqCount(int friendReqCount) {
        this.friendReqCount = friendReqCount;
    }
    
    public int getMessageCount() {
        return messageCount;
    }
    
    public void setMessageCount(int messageCount) {
        this.messageCount = messageCount;
    }
    
    public int getLoggedinUserId() {
        return loggedinUserId;
    }
    
    public void setLoggedinUserId(int loggedinUserId) {
        this.loggedinUserId = loggedinUserId;
    }
}