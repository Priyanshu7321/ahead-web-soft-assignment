package com.example.assignmentprojxml.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.example.assignmentprojxml.data.model.NavigationResponse;
import com.example.assignmentprojxml.data.repository.NavigationRepository;
import javax.inject.Inject;

public class NavigationViewModel extends ViewModel {
    private final NavigationRepository repository;

    @Inject
    public NavigationViewModel(NavigationRepository repository) {
        this.repository = repository;
    }

    public LiveData<NavigationResponse> getNavigationData() {
        return repository.getNavigationData();
    }

    public LiveData<String> getErrorMessage() {
        return repository.getErrorMessage();
    }

    public LiveData<Boolean> getIsLoading() {
        return repository.getIsLoading();
    }

    public void loadNavigationData() {
        repository.fetchNavigationData();
    }
}