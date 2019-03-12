package com.asif.imageloading.utils;

import android.app.Application;
import android.content.Context;

import com.asif.imageloading.di.component.ApplicationComponent;
import com.asif.imageloading.di.component.DaggerApplicationComponent;
import com.asif.imageloading.di.module.ApplicationModule;

public class MyApplication extends Application {
    ApplicationComponent mApplicationComponent;
    @Override
    public void onCreate() {
        super.onCreate();
    }
    public static MyApplication get(Context context) {
        return (MyApplication) context.getApplicationContext();
    }
    public ApplicationComponent getComponent() {
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
        return mApplicationComponent;
    }
    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}
