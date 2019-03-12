package com.asif.imageloading.ui.base;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

/**
 * Every presenter in the app must either implement this interface or extend BasePresenter
 * indicating the MvpView type that wants to be attached with.
 **/
public interface Presenter<V extends MvpView> {

    void attachView(V mvpView);

    void attachView(V mvpView, Activity mActivity);

    void attachView(V mvpView, AppCompatActivity mActivity);

    void detachView();

    void finishActivity(Activity activity);

    void finishActivityRightAnim(Activity activity);

    void startActivityAnim(Activity activity, boolean isFinishActivity);

}
