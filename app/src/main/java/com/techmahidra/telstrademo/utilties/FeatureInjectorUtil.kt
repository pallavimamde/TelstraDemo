package com.techmahidra.telstrademo.utilties

import com.techmahidra.telstrademo.data.FeatureDB
import com.techmahidra.telstrademo.data.Repository
import com.techmahidra.telstrademo.ui.category.FeatureViewModelFactory

object FeatureInjectorUtil {
    fun provideFeatureViewModelFactory() : FeatureViewModelFactory{
        val repository = Repository.getInstance(FeatureDB.getInstance().featureDao)
        return FeatureViewModelFactory(repository)
    }
}