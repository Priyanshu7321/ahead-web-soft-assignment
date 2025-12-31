package com.example.assignmentprojxml.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.assignmentprojxml.data.api.ApiEndpoints;
import com.example.assignmentprojxml.data.api.NavigationApiService;
import com.example.assignmentprojxml.data.model.NavigationResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class NavigationRepository {
    private final NavigationApiService apiService;
    private final MutableLiveData<NavigationResponse> navigationData = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    @Inject
    public NavigationRepository(NavigationApiService apiService) {
        this.apiService = apiService;
    }

    public LiveData<NavigationResponse> getNavigationData() {
        return navigationData;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public void fetchNavigationData() {
        isLoading.setValue(true);
        
        Call<NavigationResponse> call = apiService.getNavigationData(
            ApiEndpoints.REST_API, 
            ApiEndpoints.PLATFORM, 
            ApiEndpoints.AUTH_TOKEN
        );
        
        call.enqueue(new Callback<NavigationResponse>() {
            @Override
            public void onResponse(Call<NavigationResponse> call, Response<NavigationResponse> response) {
                isLoading.setValue(false);
                if (response.isSuccessful() && response.body() != null) {
                    navigationData.setValue(response.body());
                } else {
                    // Use mock data as fallback
                    navigationData.setValue(MockDataProvider.getMockData());
                    errorMessage.setValue("Using mock data - API response not successful. Code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<NavigationResponse> call, Throwable t) {
                isLoading.setValue(false);
                // Use mock data as fallback
                navigationData.setValue(MockDataProvider.getMockData());
                errorMessage.setValue("Network error, using mock data: " + t.getMessage());
            }
        });
    }
}