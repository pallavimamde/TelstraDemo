package com.techmahidra.telstrademo.ui.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.techmahidra.telstrademo.data.FeatureViewModel
import com.techmahidra.telstrademo.data.Repository

class FeatureViewModelFactory(private val repository: Repository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FeatureViewModel(repository) as T
    }
}