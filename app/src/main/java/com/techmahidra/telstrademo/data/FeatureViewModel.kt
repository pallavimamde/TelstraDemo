package com.techmahidra.telstrademo.data

import androidx.lifecycle.ViewModel

class FeatureViewModel(private val repository: Repository) : ViewModel() {
    fun getFeatureList() = repository.getFeatureList()
    fun setFeatureList (featuresRow: FeaturesRow) = repository.setFeatureList(featuresRow)
}