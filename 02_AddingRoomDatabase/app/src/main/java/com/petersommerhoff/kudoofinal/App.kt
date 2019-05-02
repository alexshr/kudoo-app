package com.petersommerhoff.kudoofinal

import android.app.Application

import com.facebook.stetho.Stetho

/**
 * Created by alexshr on 01.05.2019.
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        System.setProperty("kotlinx.coroutines.debug",
                if (BuildConfig.DEBUG) "on" else "off")
    }
}
