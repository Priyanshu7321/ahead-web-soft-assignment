package com.example.assignmentprojxml.data.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class NavigationItem {
    @SerializedName("title")
    private String title;
    
    @SerializedName("icon")
    private String icon;
    
    @SerializedName("type")
    private String type;
    
    @SerializedName("items")
    private List<MenuItem> items;
    
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
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public List<MenuItem> getItems() {
        return items;
    }
    
    public void setItems(List<MenuItem> items) {
        this.items = items;
    }
}