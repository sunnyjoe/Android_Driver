package com.example.qingjiao.driver_mock;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.multidex.MultiDexApplication;

import com.example.qingjiao.driver_mock.service.*;

/**
 * Created by qing.jiao on 15/11/16.
 */

public abstract class AGrabTaxiApplication extends MultiDexApplication implements Application.ActivityLifecycleCallbacks {
    private static final  String TAG = AGrabTaxiApplication.class.getSimpleName();
    private static AGrabTaxiApplication INSTANCE;
    private int mLiveActivityCount = 0;

    protected volatile  boolean mBound;

    private AGrabTaxiService mNewNetWorkService;

    @Override
    public void onCreate() {
        super.onCreate();
        AGrabTaxiApplication.INSTANCE = this;

//        ConnectionManager.getInstance().setup();
    }

    public boolean isAppRunning() {
        return mLiveActivityCount > 0;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {
        mLiveActivityCount++;
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {4

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        mLiveActivityCount--;
    }
}
