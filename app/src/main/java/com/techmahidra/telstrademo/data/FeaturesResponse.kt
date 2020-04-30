package com.techmahidra.telstrademo.data

import com.google.gson.annotations.SerializedName

data class FeaturesResponse(
    @SerializedName("rows")
    val featuresRows: List<FeaturesRow>,
    val title: String
)