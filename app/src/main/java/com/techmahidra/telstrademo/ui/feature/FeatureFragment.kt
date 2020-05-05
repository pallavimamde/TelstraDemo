package com.techmahidra.telstrademo.ui.feature

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.techmahidra.telstrademo.R
import com.techmahidra.telstrademo.data.response.FeatureResponse
import com.techmahidra.telstrademo.ui.feature.adapter.FeatureListAdapter
import org.koin.android.viewmodel.ext.android.viewModel
import androidx.lifecycle.Observer
import com.techmahidra.telstrademo.TelstraApplication
import com.techmahidra.telstrademo.data.response.ApiResponseFail
import com.techmahidra.telstrademo.data.response.FeatureRow
import com.techmahidra.telstrademo.utilties.NetworkConnectionStatus
import kotlinx.android.synthetic.main.fragment_feature.*
import kotlinx.android.synthetic.main.no_data_layout.*

/**
 * Display as list of features
 * A simple {@link Fragment} subclass.
 * get data from server and add to the @FeatureListAdapter.
 * set actionbar title.
 * check empty list or list contents
 */
class FeatureFragment : Fragment(), UIHandler {

    private val featureViewModel: FeatureViewModel by viewModel()
    private var actionBar: ActionBar? = null
    lateinit var loadingDialog: Dialog
    private var isRefreshing = false


    // Inflate the layout for this fragment
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_feature, container, false)
    }

    // on fragment view create initialize the params
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar?.title = ""
        loadingDialog = Dialog(activity as AppCompatActivity)
        loadingDialog.setCancelable(false)
        loadingDialog.setCanceledOnTouchOutside(false)
        loadData()
        swipe_refresh.setOnRefreshListener {
            isRefreshing = true
            loadData(isRefreshing)
            swipe_refresh.isRefreshing = false
        }

    }


    // get data from server
    @RequiresApi(Build.VERSION_CODES.M)
    private fun loadData(isRefreshing: Boolean = false) {
        // check internet connection
        val hasInternetConnected =
            NetworkConnectionStatus(TelstraApplication.applicationContext()).isOnline()
        if (hasInternetConnected) {
            if (!isRefreshing) { // if default refreshing is visible don't show loading dialog
                showLoading(
                    TelstraApplication.applicationContext().resources.getString(
                        R.string.please_wait
                    )
                )
            }
            // check the observer when api response is success and update list
            featureViewModel.getFeatureInfo()
            featureViewModel.featureResponse.observe(
                this,
                Observer(function = fun(featureResponse: FeatureResponse) {
                    updateUI(featureResponse)
                    hideLoading()
                })
            )
            //check the observer when api response is failed and show the error
            featureViewModel.apiResponseFail.observe(
                this,
                Observer(function = fun(apiResponseFail: ApiResponseFail) {
                    showError(apiResponseFail.error)
                    hideLoading()
                })
            )
        } else {
            showError(TelstraApplication.applicationContext().resources.getString(R.string.network_error))
        }
    }

    // update UI
    @SuppressLint("WrongConstant")
    private fun updateUI(response: FeatureResponse) {
        val modifiedFeatureList: ArrayList<FeatureRow> = ArrayList()

        // set actionbar title
        if (actionBar != null) {
            actionBar?.title = response.title
        }

        if (response.rows.isNotEmpty()) {
            for (item in response.rows) {
                val featureRowTitle = "Empty string"
                val featureRowDescription = "Empty string"
                val featureRowUrl = "Empty string"
                var isAllNull = false

                //check the empty or null string in response object
                if (item.title.isNullOrBlank() && item.description.isNullOrBlank() && item.imageHref.isNullOrBlank()) {
                    isAllNull = true
                } else {
                    if (item.title.isNullOrBlank()) {
                        item.title = featureRowTitle
                    }
                    if (item.description.isNullOrBlank()) {
                        item.description = featureRowDescription
                    }
                    if (item.imageHref.isNullOrBlank()) {
                        item.imageHref = featureRowUrl
                    }

                }
                if (!isAllNull) {
                    modifiedFeatureList.add(item)
                }
            }
            // initialize the @FeatureListAdapter and set list
            val featureListAdapter = FeatureListAdapter(modifiedFeatureList)
            rv_general_info_list.layoutManager =
                LinearLayoutManager(view!!.context, LinearLayout.VERTICAL, false)
            rv_general_info_list.adapter = featureListAdapter
        } else {
            showNoData()
        }
    }

    // if there will be any error occurred while server call
    override fun showError(errorMsg: String) {
        Toast.makeText(TelstraApplication.applicationContext(), errorMsg, Toast.LENGTH_SHORT).show()
    }

    // show dialog while loading data from server
    override fun showLoading(loadingMessage: String) {
        loadingDialog.setContentView(R.layout.progress_bar)
        loadingDialog.show()

    }

    // hide loading
    override fun hideLoading() {
        loadingDialog.dismiss()
    }

    // if there is empty list then so no data layout
    override fun showNoData() {
        rv_general_info_list.visibility = View.GONE
        tv_no_data.visibility = View.VISIBLE
    }

}

/*
* UIHandler - helps to display user needed information while handling the server call
* */
interface UIHandler {
    fun showLoading(loadingMessage: String)
    fun hideLoading()
    fun showNoData()
    fun showError(errorMsg: String)
}
