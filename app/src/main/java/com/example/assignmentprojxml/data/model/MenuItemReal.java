package com.example.assignmentprojxml.data.model;

import com.google.gson.annotations.SerializedName;

public class MenuItemReal {
    @SerializedName("type")
    private int type;
    
    @SerializedName("module")
    private String module;
    
    @SerializedName("label")
    private String label;
    
    @SerializedName("icon")
    private String icon;
    
    @SerializedName("url")
    private String url;
    
    @SerializedName("class")
    private String className;
    
    // Getters and setters
    public int getType() {
        return type;
    }
    
    public void setType(int type) {
        this.type = type;
    }
    
    public String getModule() {
        return module;
    }
    
    public void setModule(String module) {
        this.module = module;
    }
    
    public String getLabel() {
        return label;
    }
    
    public void setLabel(String label) {
        this.label = label;
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
    
    public String getClassName() {
        return className;
    }
    
    public void setClassName(String className) {
        this.className = className;
    }
}