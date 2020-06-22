package com.easyinc.currencyexchange.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.easyinc.currencyexchange.R
import com.easyinc.currencyexchange.common.extentions.inflate
import javax.inject.Inject

class LoadingAdapter @Inject constructor(): RecyclerView.Adapter<LoadingAdapter.AdapterTwoViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = AdapterTwoViewHolder(parent.inflate(R.layout.recycler_progress_bar))


    override fun onBindViewHolder(holder: AdapterTwoViewHolder, position: Int) {}


    inner class AdapterTwoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){}

    override fun getItemCount(): Int {
        return 1
    }
}