package com.techmahidra.telstrademo.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class DummyFeatureDao {
    private val featureslist = mutableListOf<FeaturesRow>()
    private val features = MutableLiveData<List<FeaturesRow>>()

    init {
        features.value = featureslist
    }

    fun setFeatureList(feature : FeaturesRow){
        featureslist.add(feature)
        features.value = featureslist
    }
    fun getFeatureList() = features as LiveData<List<FeaturesRow>>
}