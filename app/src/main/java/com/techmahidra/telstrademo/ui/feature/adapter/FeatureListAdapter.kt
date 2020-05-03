package com.techmahidra.telstrademo.ui.feature.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.techmahidra.telstrademo.R
import com.techmahidra.telstrademo.data.response.FeatureRow
import com.techmahidra.telstrademo.utilties.loadImage
import kotlinx.android.synthetic.main.adapter_feature_list.view.*

class FeatureListAdapter(private val featureRow: List<FeatureRow>) :
    RecyclerView.Adapter<FeatureListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_feature_list, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount() = featureRow.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(featureRow[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val rowTitle = itemView.tv_title
        private val rowDescription = itemView.tv_description
        private val rowPhoto = itemView.iv_photo
        fun bind(featureRow: FeatureRow) {
            rowTitle.text = featureRow.title
            rowDescription.text = featureRow.description
            rowPhoto.loadImage(featureRow.imageHref)
        }
    }

}