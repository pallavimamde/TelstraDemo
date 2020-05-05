package com.techmahidra.telstrademo.ui.feature

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.techmahidra.telstrademo.data.network.APIService
import com.techmahidra.telstrademo.data.repository.FeatureRepository
import com.techmahidra.telstrademo.data.response.FeatureResponse
import io.reactivex.Maybe
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.*
import org.mockito.Mockito.*
import java.net.SocketException

@RunWith(JUnit4::class)
internal class FeatureViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Spy
    lateinit var apiService: APIService

    lateinit var featureViewModel: FeatureViewModel

    lateinit var featureRepository: FeatureRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        this.featureRepository = FeatureRepository(this.apiService)
        this.featureViewModel = FeatureViewModel(this.featureRepository)
    }

    @Test
    fun fetchRepositories_positiveResponse() {
        `when`(this.apiService.getFeatureInfo()).thenAnswer {
            return@thenAnswer Maybe.just(ArgumentMatchers.any<FeatureRepository>())
        }

        val observer = mock(Observer::class.java) as Observer<FeatureResponse>
        this.featureViewModel.featureResponse.observeForever(observer)

        this.featureViewModel.getFeatureInfo()

        assertNotNull(this.featureViewModel.featureResponse.value)
    }

    @Test
    fun fetchRepositories_error() {
        `when`(this.apiService.getFeatureInfo()).thenAnswer {
            return@thenAnswer Maybe.error<SocketException>(SocketException("No network here"))
        }

        val observer = mock(Observer::class.java) as Observer<FeatureResponse>
        this.featureViewModel.featureResponse.observeForever(observer)

        this.featureViewModel.getFeatureInfo()

        assertNotNull(this.featureViewModel.apiResponseFail.value)
        assert(this.featureViewModel.apiResponseFail.value?.error is String)
    }


}