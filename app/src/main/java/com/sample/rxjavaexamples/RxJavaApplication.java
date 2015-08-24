package com.sample.rxjavaexamples;

import android.app.Application;
import android.util.Log;

import rx.plugins.RxJavaErrorHandler;
import rx.plugins.RxJavaPlugins;

/**
 * Created by phanirajabhandari on 3/19/15.
 */
public class RxJavaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        RxJavaPlugins.getInstance().registerErrorHandler(new RxJavaErrorHandler() {
            @Override
            public void handleError(Throwable e) {
                Log.w("Error", e);
            }
        });
    }
}
