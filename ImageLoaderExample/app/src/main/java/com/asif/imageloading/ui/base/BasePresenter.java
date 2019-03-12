package com.asif.imageloading.ui.base;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

import com.asif.imageloading.R;


/**
 * Base class that implements the Presenter interface and provides a base implementation for
 * attachView() and detachView(). It also handles keeping a reference to the mvpView that
 * can be accessed from the children classes by calling getMvpView().
 **/
public class BasePresenter<T extends MvpView> implements Presenter<T> {

    private T mMvpView;
    //private Activity mActivity;


    @Override
    public void attachView(T mvpView) {
        mMvpView = mvpView;
    }

    @Override
    public void attachView(T mvpView, Activity activity) {

        mMvpView = mvpView;
       // mActivity = activity;
    }


    @Override
    public void attachView(T mvpView, AppCompatActivity activity) {

        mMvpView = mvpView;
        //mActivity = activity;
    }


    @Override
    public void detachView() {
        mMvpView = null;
    }

    @Override
    public void startActivityAnim(Activity activity, boolean isFinishActivity) {

        if (isFinishActivity) {
            activity.finish();
        }
        activity.overridePendingTransition(R.anim.right_to_left_enter, R.anim.right_to_left_exit);
    }

    @Override
    public void finishActivity(Activity activity) {
        activity.finish();
        activity.overridePendingTransition(R.anim.left_to_right_enter, R.anim.left_to_right_exit);
    }


    @Override
    public void finishActivityRightAnim(Activity activity) {
        activity.finish();
        activity.overridePendingTransition(R.anim.right_to_left_enter, R.anim.right_to_left_exit);
    }


    private boolean isViewAttached() {
        return mMvpView != null;
    }

    public T getMvpView() {
        return mMvpView;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    private static class MvpViewNotAttachedException extends RuntimeException {
        private MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }


}

