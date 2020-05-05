package com.techmahidra.telstrademo.data.response

import com.google.gson.annotations.SerializedName

/* *
* FeatureResponse - API response object
* */
data class FeatureResponse(
    val rows: List<FeatureRow>,
    val title: String
)

