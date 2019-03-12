package com.asif.imageloading.di.module;

import android.app.Application;
import android.content.Context;


import com.asif.imageloading.di.ApplicationContext;

import dagger.Module;
import dagger.Provides;

/**
 * Provide application-level dependencies.
 */
@Module
public class ApplicationModule {
    private final Application mApplication;
    public ApplicationModule(Application application) {
        mApplication = application;
    }
    @Provides
    Application provideApplication() {
        return mApplication;
    }
    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }


}
