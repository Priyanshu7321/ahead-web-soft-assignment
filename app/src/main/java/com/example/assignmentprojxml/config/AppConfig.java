package com.example.assignmentprojxml.config;

import com.example.assignmentprojxml.BuildConfig;

/**
 * Application configuration manager
 * Centralizes all app configuration and provides easy access to build-time constants
 */
public class AppConfig {
    
    // API Configuration - FROM BUILDCONFIG (BEST PRACTICE)
    public static final String API_BASE_URL = BuildConfig.API_BASE_URL;
    public static final String API_REST_API = BuildConfig.API_REST_API;
    public static final int API_PLATFORM = BuildConfig.API_PLATFORM;
    public static final String API_AUTH_TOKEN = BuildConfig.API_AUTH_TOKEN;
    
    // App Configuration
    public static final boolean IS_DEBUG = BuildConfig.DEBUG;
    public static final String VERSION_NAME = BuildConfig.VERSION_NAME;
    public static final int VERSION_CODE = BuildConfig.VERSION_CODE;
    
    // Network Configuration
    public static final int NETWORK_TIMEOUT_SECONDS = 30;
    public static final int NETWORK_READ_TIMEOUT_SECONDS = 30;
    public static final int NETWORK_WRITE_TIMEOUT_SECONDS = 30;
    
    // UI Configuration
    public static final int APPS_INITIAL_DISPLAY_COUNT = 4;
    
    /**
     * Get environment-specific configuration
     */
    public static String getEnvironmentName() {
        return IS_DEBUG ? "Development" : "Production";
    }
    
    /**
     * Check if logging should be enabled
     */
    public static boolean isLoggingEnabled() {
        return IS_DEBUG;
    }
    
    /**
     * Get full API endpoint URL
     */
    public static String getNavigationApiUrl() {
        return API_BASE_URL + "navigation";
    }
}