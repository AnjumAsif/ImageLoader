package com.asif.imageloading.ui.base;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.asif.imageloading.di.component.ActivityComponent;
import com.asif.imageloading.di.component.ConfigPersistentComponent;
import com.asif.imageloading.di.component.DaggerConfigPersistentComponent;
import com.asif.imageloading.di.module.ActivityModule;
import com.asif.imageloading.utils.MyApplication;
import com.asif.imageloading.utils.Utils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import butterknife.Unbinder;

/**
 * Abstract activity that every other Activity in this application must implement. It handles
 * creation of Dagger components and makes sure that instances of ConfigPersistentComponent survive
 * across configuration changes.
 **/
@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity implements FragmentMvpView, BaseFragment.Callback {

    private static final String KEY_ACTIVITY_ID = "KEY_ACTIVITY_ID";
    private static final AtomicLong NEXT_ID = new AtomicLong(0);
    private static final Map<Long, ConfigPersistentComponent> sComponentsMap = new HashMap<>();

    private ActivityComponent mActivityComponent;
    private long mActivityId;
    private Unbinder mUnBinder;

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create the ActivityComponent and reuses cached ConfigPersistentComponent if this is
        // being called after a configuration change.
        mActivityId = savedInstanceState != null ?
                savedInstanceState.getLong(KEY_ACTIVITY_ID) : NEXT_ID.getAndIncrement();
        ConfigPersistentComponent configPersistentComponent;
        if (!sComponentsMap.containsKey(mActivityId)) {
            configPersistentComponent = DaggerConfigPersistentComponent.builder()
                    .applicationComponent(MyApplication.get(this).getComponent())
                    .build();
            sComponentsMap.put(mActivityId, configPersistentComponent);
        } else {
            configPersistentComponent = sComponentsMap.get(mActivityId);
        }
        mActivityComponent = configPersistentComponent.activityComponent(new ActivityModule(this));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(KEY_ACTIVITY_ID, mActivityId);
    }

    public void setUnBinder(Unbinder unBinder) {
        mUnBinder = unBinder;
    }


    public ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }


    @Override
    protected void onDestroy() {
        if (!isChangingConfigurations()) {
            sComponentsMap.remove(mActivityId);
        }

        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        super.onDestroy();
    }

    public ActivityComponent activityComponent() {
        return mActivityComponent;
    }

    @Override
    public void showLoading() {
        hideLoading();
        mProgressDialog = Utils.showLoadingDialog(this);
    }

    @Override
    public void hideLoading() {

        Log.e("mProgressDialog ", " " + mProgressDialog + " ");


        if (mProgressDialog != null && mProgressDialog.isShowing()) {

            Log.e("mProgressDialog 212121 ", " " + mProgressDialog + " ");

            mProgressDialog.dismiss();
            mProgressDialog.cancel();
        }
    }


    @Override
    public void onError(String message) {
        hideLoading();
    }

    @Override
    public void onSuccess() {
        hideLoading();
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public boolean isNetworkConnected() {
        return false;
    }

    @Override
    public void hideKeyboard() {

    }

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }
}
