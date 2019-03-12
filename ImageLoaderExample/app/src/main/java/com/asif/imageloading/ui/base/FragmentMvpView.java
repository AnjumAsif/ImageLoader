package com.asif.imageloading.ui.base;

import android.support.annotation.StringRes;

/**
 * Created by appzoro on 03/05/18
 **/

public interface FragmentMvpView {

    void showLoading();

    void hideLoading();

    void onError(String message);

    void onSuccess();

    void showMessage(String message);

     boolean isNetworkConnected();

    void hideKeyboard();
}
