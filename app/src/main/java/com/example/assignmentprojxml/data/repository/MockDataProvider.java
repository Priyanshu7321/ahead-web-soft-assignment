package com.example.assignmentprojxml.data.repository;

import com.example.assignmentprojxml.data.model.MenuItem;
import com.example.assignmentprojxml.data.model.NavigationData;
import com.example.assignmentprojxml.data.model.NavigationItem;
import com.example.assignmentprojxml.data.model.NavigationResponse;
import com.example.assignmentprojxml.data.model.UserProfile;
import java.util.ArrayList;
import java.util.List;

public class MockDataProvider {
    
    public static NavigationResponse getMockData() {
        NavigationResponse response = new NavigationResponse();
        response.setStatus("success");
        response.setMessage("Data loaded successfully");
        
        NavigationData data = new NavigationData();
        
        // Mock user profile
        UserProfile userProfile = new UserProfile();
        userProfile.setName("Louise Gonzales");
        userProfile.setEmail("louise@example.com");
        userProfile.setProfilePicture("https://via.placeholder.com/150");
        data.setUserProfile(userProfile);
        
        // Mock navigation items
        List<NavigationItem> navigationItems = new ArrayList<>();
        
        // Apps section
        NavigationItem appsSection = new NavigationItem();
        appsSection.setTitle("Apps");
        appsSection.setType("apps");
        
        List<MenuItem> appsItems = new ArrayList<>();
        
        MenuItem albums = new MenuItem();
        albums.setTitle("Albums");
        albums.setIcon("https://via.placeholder.com/40");
        albums.setColor("#E8F5E8");
        appsItems.add(albums);
        
        MenuItem jobs = new MenuItem();
        jobs.setTitle("Jobs");
        jobs.setIcon("https://via.placeholder.com/40");
        jobs.setColor("#FFE8F5");
        appsItems.add(jobs);
        
        MenuItem crowdfunding = new MenuItem();
        crowdfunding.setTitle("Crowdfunding");
        crowdfunding.setIcon("https://via.placeholder.com/40");
        crowdfunding.setColor("#FFF0E8");
        appsItems.add(crowdfunding);
        
        MenuItem group = new MenuItem();
        group.setTitle("Group");
        group.setIcon("https://via.placeholder.com/40");
        group.setColor("#E8F0FF");
        appsItems.add(group);
        
        MenuItem events = new MenuItem();
        events.setTitle("Events");
        events.setIcon("https://via.placeholder.com/40");
        events.setColor("#F0E8FF");
        appsItems.add(events);
        
        MenuItem marketplace = new MenuItem();
        marketplace.setTitle("Marketplace");
        marketplace.setIcon("https://via.placeholder.com/40");
        marketplace.setColor("#E8FFF0");
        appsItems.add(marketplace);
        
        appsSection.setItems(appsItems);
        navigationItems.add(appsSection);
        
        // Help section
        NavigationItem helpSection = new NavigationItem();
        helpSection.setTitle("Help More");
        helpSection.setType("help");
        
        List<MenuItem> helpItems = new ArrayList<>();
        
        MenuItem settings = new MenuItem();
        settings.setTitle("Settings");
        settings.setIcon("https://via.placeholder.com/40");
        settings.setColor("#F5F5F5");
        helpItems.add(settings);
        
        MenuItem privacy = new MenuItem();
        privacy.setTitle("Privacy");
        privacy.setIcon("https://via.placeholder.com/40");
        privacy.setColor("#F5F5F5");
        helpItems.add(privacy);
        
        MenuItem terms = new MenuItem();
        terms.setTitle("Terms of Service");
        terms.setIcon("https://via.placeholder.com/40");
        terms.setColor("#F5F5F5");
        helpItems.add(terms);
        
        MenuItem contact = new MenuItem();
        contact.setTitle("Contact Us");
        contact.setIcon("https://via.placeholder.com/40");
        contact.setColor("#F5F5F5");
        helpItems.add(contact);
        
        helpSection.setItems(helpItems);
        navigationItems.add(helpSection);
        
        data.setNavigationItems(navigationItems);
        response.setData(data);
        
        return response;
    }
}