package com.techmahidra.telstrademo

import android.app.Application
import com.techmahidra.telstrademo.data.network.retrofitInstance
import org.koin.android.ext.android.startKoin

class TelstraApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this,
            listOf(retrofitInstance),
            loadPropertiesFromFile = true)
    }

}