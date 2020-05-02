package com.techmahidra.telstrademo.data.network

import com.techmahidra.telstrademo.data.response.FeatureResponse
import retrofit2.Call
import retrofit2.http.GET

const val API_FEATURE_INFO = "s/2iodh4vg0eortkl/facts.json"

interface APIService {
    @GET(API_FEATURE_INFO)
    fun getFeatureInfo(): Call<FeatureResponse>
}