package com.example.assignmentprojxml.data.api;

import com.example.assignmentprojxml.data.model.NavigationResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * API Service interface for all network calls
 * Uses Retrofit annotations to define endpoints
 */
public interface ApiService {
    
    /**
     * Fetch navigation data from the server
     * @param restApi API identifier
     * @param platform Platform identifier (1 for mobile)
     * @param authToken Authentication token
     * @return Call object containing NavigationResponse
     */
    @GET(ApiEndpoints.NAVIGATION)
    Call<NavigationResponse> getNavigationData(
        @Query("restApi") String restApi,
        @Query("sesapi_platform") int platform,
        @Query("auth_token") String authToken
    );
}