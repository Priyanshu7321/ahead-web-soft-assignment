package com.example.assignmentprojxml.data.api;

import com.example.assignmentprojxml.config.AppConfig;

public class ApiEndpoints {
    // Use AppConfig values (which now use BuildConfig - BEST PRACTICE)
    public static final String BASE_URL = AppConfig.API_BASE_URL;
    
    // Navigation endpoints
    public static final String NAVIGATION = "navigation";
    
    // API parameters (from BuildConfig via AppConfig)
    public static final String REST_API = AppConfig.API_REST_API;
    public static final int PLATFORM = AppConfig.API_PLATFORM;
    public static final String AUTH_TOKEN = AppConfig.API_AUTH_TOKEN;
}