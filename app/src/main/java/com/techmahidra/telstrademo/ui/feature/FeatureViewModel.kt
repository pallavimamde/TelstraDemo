package com.techmahidra.telstrademo.ui.feature

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.techmahidra.telstrademo.R
import com.techmahidra.telstrademo.TelstraApplication
import com.techmahidra.telstrademo.data.repository.FeatureRepository
import com.techmahidra.telstrademo.data.response.ApiResponseFail
import com.techmahidra.telstrademo.data.response.FeatureResponse
import com.techmahidra.telstrademo.utilties.NetworkConnectionStatus

/* *
* FeatureViewModel - exposes streams of server response data relevant to the View
* check internet connection with help of @NetworkConnectionStatus class
* */
@RequiresApi(Build.VERSION_CODES.M)
class FeatureViewModel(private val featureRepository: FeatureRepository) : ViewModel() {

    var featureResponse = MutableLiveData<FeatureResponse>()
    var apiResponseFail = MutableLiveData<ApiResponseFail>()

    init {
        getFeatureInfo()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun getFeatureInfo() {

        featureRepository.getFeatureInfoReq(object : FeatureRepository.OnFeatureInfo {
            override fun onSuccess(data: FeatureResponse) {
                featureResponse.value = data // set response to livedata object
            }

            override fun onFailure(error: String) {
                apiResponseFail.value?.error = error
            }
        })

    }

}
