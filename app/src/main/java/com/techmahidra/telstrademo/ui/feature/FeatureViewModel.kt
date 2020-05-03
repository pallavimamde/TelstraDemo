package com.techmahidra.telstrademo.ui.feature

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.techmahidra.telstrademo.R
import com.techmahidra.telstrademo.TelstraApplication
import com.techmahidra.telstrademo.data.repository.FeatureRepository
import com.techmahidra.telstrademo.data.response.FeatureResponse
import com.techmahidra.telstrademo.utilties.NetworkConnectionStatus

/* *
* FeatureViewModel - exposes streams of server response data relevant to the View
* check internet connection with help of @NetworkConnectionStatus class
* */
class FeatureViewModel(private val featureRepository: FeatureRepository) : ViewModel() {

    var featureResponse = MutableLiveData<FeatureResponse>()
    lateinit var result: LiveData<FeatureResponse>
    private var fragmentView: FeatureFragment? = null

    fun onViewActive(view: UIHandler) {
        fragmentView = view as FeatureFragment // initialize the view as a #FeatureFragment
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun getFeatureInfo(isRefreshing: Boolean) {
        // check internet connection
        val hasInternetConnected =
            NetworkConnectionStatus(TelstraApplication.applicationContext()).isOnline()
        if (hasInternetConnected) {
            if (!isRefreshing) { // if default refreshing is visible don't show loading dialog
                fragmentView?.showLoading(
                    TelstraApplication.applicationContext().resources.getString(
                        R.string.please_wait
                    )
                )
            }
            featureRepository.getFeatureInfoReq(object : FeatureRepository.OnFeatureInfo {
                override fun onSuccess(data: FeatureResponse) {
                    featureResponse.value = data // set response to livedata object
                    fragmentView?.hideLoading()
                }

                override fun onFailure(error: String) {
                    fragmentView?.showError(error)
                    if (!isRefreshing) {
                        fragmentView?.hideLoading()
                    }
                }
            })
        } else {
            fragmentView?.showError(TelstraApplication.applicationContext().resources.getString(R.string.network_error))
        }
    }

}

/*
* UIHandler - helps to display user needed information while handling the server call
* */
interface UIHandler {
    fun showLoading(loadingMessage: String)
    fun hideLoading()
    fun showNoData()
    fun showError(errorMsg: String)
}