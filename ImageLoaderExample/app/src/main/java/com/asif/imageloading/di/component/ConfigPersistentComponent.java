package com.asif.imageloading.di.component;

import com.asif.imageloading.di.ConfigPersistent;
import com.asif.imageloading.di.module.ActivityModule;

import dagger.Component;

@ConfigPersistent
@Component(dependencies = ApplicationComponent.class)
public interface ConfigPersistentComponent {
    ActivityComponent activityComponent(ActivityModule activityModule);

}