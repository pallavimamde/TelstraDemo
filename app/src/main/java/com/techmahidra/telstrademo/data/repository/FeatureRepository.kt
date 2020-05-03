package com.techmahidra.telstrademo.data.repository

import com.techmahidra.telstrademo.data.network.APIService
import com.techmahidra.telstrademo.data.response.FeatureResponse
import retrofit2.Call
import retrofit2.Response

class FeatureRepository(private val aPIService: APIService) {

    fun getProducts(onFeatureInfo: OnFeatureInfo) {
        aPIService.getFeatureInfo().enqueue(object : retrofit2.Callback<FeatureResponse> {
            override fun onResponse(
                call: Call<FeatureResponse>,
                response: Response<FeatureResponse>
            ) {
                onFeatureInfo.onSuccess((response.body() as FeatureResponse))
            }

            override fun onFailure(call: Call<FeatureResponse>, t: Throwable) {
                onFeatureInfo.onFailure()
            }
        })
    }

    interface OnFeatureInfo {
        fun onSuccess(data: FeatureResponse)
        fun onFailure()
    }
}

