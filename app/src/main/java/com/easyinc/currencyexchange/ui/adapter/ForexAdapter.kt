package com.easyinc.currencyexchange.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.easyinc.currencyexchange.R
import com.easyinc.currencyexchange.common.extentions.inflate
import com.easyinc.currencyexchange.ui.model.Currency
import javax.inject.Inject

class ForexAdapter @Inject constructor():  RecyclerView.Adapter<ForexViewHolder>() {

    private var currenciesList: MutableList<Currency> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =ForexViewHolder(
        parent.inflate(R.layout.currency_layout))

    override fun getItemCount() = currenciesList.size

    override fun onBindViewHolder(holder: ForexViewHolder, position: Int) = holder.bind(currenciesList[position])

    fun submitList(list: List<Currency>){
        val initPosition = currenciesList.size - 1
        notifyItemRemoved(initPosition)

        currenciesList.addAll(list)
        notifyItemRangeChanged(initPosition, currenciesList.size + 1)
        notifyDataSetChanged()
    }

}