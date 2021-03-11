package com.monfort.sharedpreferencessecureapp;

import android.app.Application;

import com.monfort.sharedpreferencessecureapp.utils.MSP;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        MSP.initHelper(this);
        //MSP.initHelper(this, true);
    }
}
