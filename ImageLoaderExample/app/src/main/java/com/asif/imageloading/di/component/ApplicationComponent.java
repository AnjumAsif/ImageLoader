package com.asif.imageloading.di.component;

import android.app.Application;
import android.content.Context;


import com.asif.imageloading.di.ApplicationContext;
import com.asif.imageloading.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    @ApplicationContext
    Context context();
    Application application();

}
