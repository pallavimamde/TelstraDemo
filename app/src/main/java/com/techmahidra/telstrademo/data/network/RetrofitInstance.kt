package com.techmahidra.telstrademo.data.network

import com.techmahidra.telstrademo.data.repository.FeatureRepository
import com.techmahidra.telstrademo.ui.feature.FeatureViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

const val BASE_URL = "https://dl.dropboxusercontent.com/"

val retrofitInstance = module {

    single { FeatureRepository(get()) }

    single { createWebService() }

    viewModel { FeatureViewModel(get()) }

}

fun createWebService(): APIService {
    val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    return retrofit.create(APIService::class.java)
}