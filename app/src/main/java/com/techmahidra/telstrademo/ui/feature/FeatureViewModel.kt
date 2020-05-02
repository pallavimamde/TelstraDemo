package com.techmahidra.telstrademo.ui.feature

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.techmahidra.telstrademo.data.repository.FeatureRepository
import com.techmahidra.telstrademo.data.response.FeatureResponse
import org.koin.standalone.KoinComponent

class FeatureViewModel (private val featureRepository: FeatureRepository) : ViewModel(),
    KoinComponent {

    var featureResponse = MutableLiveData<FeatureResponse>()

    /*init {
        featureResponse.value = FeatureResponse
    }*/

    fun getProducts() {
        featureRepository.getProducts(object : FeatureRepository.OnFeatureInfo {
            override fun onSuccess(data: FeatureResponse) {
                featureResponse.value = data
            }

            override fun onFailure() {
                //REQUEST FAILED
            }
        })
    }
}