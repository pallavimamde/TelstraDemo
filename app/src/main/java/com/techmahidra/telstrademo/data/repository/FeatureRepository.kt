package com.techmahidra.telstrademo.data.repository

import com.techmahidra.telstrademo.data.network.APIService
import com.techmahidra.telstrademo.data.response.FeatureResponse
import retrofit2.Call
import retrofit2.Response
import java.lang.NullPointerException

/* *
* FeatureRepository - Retrofit callbacks events handle to check response is succeed or failed
* */
class FeatureRepository(private val apiService: APIService) {

    fun getFeatureInfoReq(onFeatureInfo: OnFeatureInfo) {
        try {
            apiService.getFeatureInfo().enqueue(object : retrofit2.Callback<FeatureResponse> {
                override fun onResponse(
                    call: Call<FeatureResponse>,
                    response: Response<FeatureResponse>
                ) {
                    onFeatureInfo.onSuccess((response.body() as FeatureResponse))
                }

                override fun onFailure(call: Call<FeatureResponse>, t: Throwable) {
                    t.message?.let { onFeatureInfo.onFailure(it) }
                }
            })
        }catch (e : NullPointerException){
            e.printStackTrace()
        }
    }

    interface OnFeatureInfo {
        fun onSuccess(data: FeatureResponse)
        fun onFailure(error: String)
    }
}

