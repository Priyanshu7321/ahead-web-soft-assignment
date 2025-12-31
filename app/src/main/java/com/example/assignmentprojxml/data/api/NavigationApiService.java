package com.example.assignmentprojxml.data.api;

import com.example.assignmentprojxml.data.model.NavigationResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NavigationApiService {
    @GET("navigation")
    Call<NavigationResponse> getNavigationData(
        @Query("restApi") String restApi,
        @Query("sesapi_platform") int platform,
        @Query("auth_token") String authToken
    );
}