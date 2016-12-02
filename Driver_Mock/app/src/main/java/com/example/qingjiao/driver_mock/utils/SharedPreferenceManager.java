package com.example.qingjiao.driver_mock.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.qingjiao.driver_mock.AGrabTaxiApplication;

/**
 * Created by qing.jiao on 2/12/16.
 */

public class SharedPreferenceManager {
    private static final String MAIN_PREFS_NAME = "MyTeksiApplicationPrefs";
    private static final String AUTH_TOKEN_STORAGE_NAME = "authTokenStorage";
    private static final String CONFIG_PREFS_NAME = "GTConfigPrefs";
    private static final String PERFORMANCE_PREFS_NAME = "TaxiTypePrefs";

    private Context mContext;

    public SharedPreferenceManager() {
        mContext = AGrabTaxiApplication.getContext();
    }

    public boolean isInitialized() {
        return this.mContext != null;
    }

    public SharedPreferences getMainSharedPreferences() {
        //use Context.MODE_PRIVATE for below honeycomb
        if (this.mContext == null) {
            return null;
        }
        return this.mContext.getSharedPreferences(SharedPreferenceManager.MAIN_PREFS_NAME, Context.MODE_MULTI_PROCESS);
    }

}
