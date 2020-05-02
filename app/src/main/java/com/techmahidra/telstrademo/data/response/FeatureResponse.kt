package com.techmahidra.telstrademo.data.response

import com.google.gson.annotations.SerializedName

data class FeatureResponse(
    @SerializedName("rows")
    val featureRows: List<FeatureRow>,
    val title: String
)