package com.techmahidra.telstrademo.data.network

import android.os.Build
import androidx.annotation.RequiresApi
import com.techmahidra.telstrademo.data.repository.FeatureRepository
import com.techmahidra.telstrademo.ui.feature.FeatureViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

/* *
* RetrofitInstance - Created retrofit object for server request
* */

const val BASE_URL = "https://dl.dropboxusercontent.com/"

@RequiresApi(Build.VERSION_CODES.M)
val retrofitInstance = module {

    single { FeatureRepository(get()) }

    single { createWebService() }

    viewModel { FeatureViewModel(get()) }

}

fun createWebService(): APIService {
    val retrofit = Retrofit.Builder() // create retrofit instance
        .baseUrl(BASE_URL) // base url
        .addConverterFactory(GsonConverterFactory.create()) // convert to and from from json
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // to add adapter
        .build()

    return retrofit.create(APIService::class.java)
}