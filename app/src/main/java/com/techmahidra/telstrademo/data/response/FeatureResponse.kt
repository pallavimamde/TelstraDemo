package com.techmahidra.telstrademo.data.response

import com.google.gson.annotations.SerializedName

/* *
* FeatureResponse - API response object
* */
data class FeatureResponse(
    @SerializedName("rows") //response actual param
    val featureRows: List<FeatureRow>,
    val title: String
)