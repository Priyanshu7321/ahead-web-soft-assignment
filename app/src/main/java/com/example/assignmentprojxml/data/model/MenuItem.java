package com.example.assignmentprojxml.data.model;

import com.google.gson.annotations.SerializedName;

public class MenuItem {
    @SerializedName("title")
    private String title;
    
    @SerializedName("icon")
    private String icon;
    
    @SerializedName("url")
    private String url;
    
    @SerializedName("color")
    private String color;
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getIcon() {
        return icon;
    }
    
    public void setIcon(String icon) {
        this.icon = icon;
    }
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getColor() {
        return color;
    }
    
    public void setColor(String color) {
        this.color = color;
    }
}