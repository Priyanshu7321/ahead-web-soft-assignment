package com.example.assignmentprojxml.di;

import com.example.assignmentprojxml.MainActivity;
import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = {NetworkModule.class, ViewModelModule.class})
public interface AppComponent {
    void inject(MainActivity mainActivity);
}