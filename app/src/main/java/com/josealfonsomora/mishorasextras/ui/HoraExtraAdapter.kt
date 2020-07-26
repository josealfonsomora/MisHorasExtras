package com.josealfonsomora.mishorasextras.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.josealfonsomora.mishorasextras.R
import java.math.BigDecimal

class HoraExtraAdapter : RecyclerView.Adapter<HoraExtraAdapter.Holder>(){

    private var list: List<HoraExtraItem> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.hora_extra_adapter_item, parent, false)
        return Holder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = list[position]
        holder.date.text = item.date
        holder.hours.text = item.hours.toString()
        holder.price.text = "${item.price}€"
        holder.total.text = "${item.price.times(BigDecimal(item.hours))}€"
    }

    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val date = view.findViewById<TextView>(R.id.date)
        val hours = view.findViewById<TextView>(R.id.hours)
        val price = view.findViewById<TextView>(R.id.price)
        val total = view.findViewById<TextView>(R.id.total)
    }

    fun updateHorasExtras(list: List<HoraExtraItem> ){
        this.list = list
        notifyDataSetChanged()
    }
}

data class HoraExtraItem(
    val date: String,
    val hours: Int,
    val price: BigDecimal
)
