package com.techmahidra.telstrademo

import android.app.Application
import android.content.Context
import com.techmahidra.telstrademo.data.network.retrofitInstance
import org.koin.android.ext.android.startKoin

/* *
* TelstraApplication - Base application, create singleton instance of class
* */
class TelstraApplication : Application() {
    init {
        instance = this
    }

    companion object {
        private var instance: TelstraApplication? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin( // koin injection framework
            this,
            listOf(retrofitInstance),
            loadPropertiesFromFile = true
        )
    }

}