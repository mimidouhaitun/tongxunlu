package com.example.tarena.bmobdemo.app;

import android.app.Application;

import cn.bmob.v3.Bmob;

/**
 * Created by tarena on 2017/6/29.
 */

public class MyApp extends Application{

    public static MyApp CONTEXT;

    @Override
    public void onCreate() {
        super.onCreate();
        CONTEXT = this;
        Bmob.initialize(this, "6896fbca839d1e049cbe0887572e3843");
    }
}
