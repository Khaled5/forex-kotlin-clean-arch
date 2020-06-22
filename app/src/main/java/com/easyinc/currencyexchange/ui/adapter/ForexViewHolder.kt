package com.easyinc.currencyexchange.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.easyinc.currencyexchange.ui.model.Currency
import kotlinx.android.synthetic.main.currency_layout.view.*
import java.text.SimpleDateFormat
import java.util.*

class ForexViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun bind(currency: Currency){
        itemView.pair.text = currency.pair
        itemView.cmd.text = currency.cmd.toString()
        itemView.date.text = convertToDate(currency.actualTime.toString())
        itemView.sl.text = currency.sl.toFloat().toString()
        itemView.tp.text = currency.tp.toFloat().toString()
        itemView.price.text = currency.price.toFloat().toString()
    }

    private fun minimizeToFloat(number: String): Float{
        return number.toFloat()
    }

    private fun convertToDate(date: String): String{
        val toLong = date.toLong()

        val mDate = Date(toLong * 1000L)

        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm")

        return dateFormat.format(mDate)
    }

}