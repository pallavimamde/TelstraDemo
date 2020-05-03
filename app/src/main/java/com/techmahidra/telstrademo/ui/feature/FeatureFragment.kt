package com.techmahidra.telstrademo.ui.feature

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.techmahidra.telstrademo.R
import com.techmahidra.telstrademo.data.response.FeatureResponse
import com.techmahidra.telstrademo.ui.feature.adapter.FeatureListAdapter
import org.koin.android.viewmodel.ext.android.viewModel
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_feature.*


class FeatureFragment : Fragment() {

    private val featureViewModel: FeatureViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_feature, container, false)
    }

    @SuppressLint("WrongConstant")
    override fun onStart() {
        super.onStart()
        rv_general_info_list.layoutManager =
            LinearLayoutManager(view!!.context, LinearLayout.VERTICAL, false)


        featureViewModel.getProducts()
        featureViewModel.featureResponse.observe(
            this,
            Observer(function = fun(featureResponse: FeatureResponse) {

                val featureListAdapter = FeatureListAdapter(featureResponse.featureRows)
                rv_general_info_list.adapter = featureListAdapter


            })
        )
    }


}
