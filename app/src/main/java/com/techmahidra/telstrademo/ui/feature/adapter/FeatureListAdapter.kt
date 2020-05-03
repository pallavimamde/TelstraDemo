package com.techmahidra.telstrademo.ui.feature.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.techmahidra.telstrademo.R
import com.techmahidra.telstrademo.TelstraApplication
import com.techmahidra.telstrademo.data.response.FeatureRow
import com.techmahidra.telstrademo.utilties.loadImage
import kotlinx.android.synthetic.main.adapter_feature_list.view.*

/* *
* FeatureListAdapter - helps to bind data in feature recyclerview
* highlight the selected list item*/
class FeatureListAdapter(private val featureRow: List<FeatureRow>) :
    RecyclerView.Adapter<FeatureListAdapter.ViewHolder>() {

    var rowIndex = -1 // default selected row index

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_feature_list, parent, false)
        return ViewHolder(v)
    }

    //get list item count
    override fun getItemCount() = featureRow.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(featureRow[position], position)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val rowLayout = itemView.const_layout_feature_list
        private val rowTitle = itemView.tv_title
        private val rowDescription = itemView.tv_description
        private val rowPhoto = itemView.iv_photo

        // bind data to view
        fun bind(featureRow: FeatureRow, position: Int) {
            rowTitle.text = featureRow.title
            rowDescription.text = featureRow.description
            rowPhoto.loadImage(featureRow.imageHref)


            if (rowIndex === position) {
                rowLayout.setBackgroundColor(
                    TelstraApplication.applicationContext().resources.getColor(
                        R.color.colorLightGray
                    )
                )
            } else {
                rowLayout.setBackgroundColor(
                    TelstraApplication.applicationContext().resources.getColor(
                        R.color.colorWhite
                    )
                )

            }
            itemView.setOnClickListener {
                rowIndex = position
                notifyDataSetChanged() // notify when data change
            }
        }
    }

}
