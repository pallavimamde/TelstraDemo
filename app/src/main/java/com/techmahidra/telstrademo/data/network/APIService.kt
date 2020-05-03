package com.techmahidra.telstrademo.data.network

import com.techmahidra.telstrademo.data.response.FeatureResponse
import retrofit2.Call
import retrofit2.http.GET

/* *
* APIService - encode the param and request method for specific purpose
 */
const val API_FEATURE_INFO = "s/2iodh4vg0eortkl/facts.json" // extended url

interface APIService {
    @GET(API_FEATURE_INFO) // request method is get
    fun getFeatureInfo(): Call<FeatureResponse> // get response in the form of @FeatureResponse
}