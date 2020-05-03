package com.techmahidra.telstrademo.utilties

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import androidx.annotation.RequiresApi
import com.techmahidra.telstrademo.TelstraApplication

/* *
* NetworkConnectionStatus - Check network connection
* */
class NetworkConnectionStatus(private val applicationContext: Context) : NetworkStatus {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun isOnline(): Boolean {
        val service = Context.CONNECTIVITY_SERVICE
        val manager = applicationContext.getSystemService(service) as ConnectivityManager?
        val network = manager?.activeNetworkInfo
        return network != null && network.isConnectedOrConnecting
    }
}