package com.asif.imageloading.di.component;

import com.asif.imageloading.di.module.ActivityModule;
import com.asif.imageloading.ui.musiclist.MainActivity;

import dagger.Subcomponent;

@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(MainActivity activity);
}
