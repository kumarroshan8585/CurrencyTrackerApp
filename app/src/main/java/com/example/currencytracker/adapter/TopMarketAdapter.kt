package com.example.currencytracker.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.currencytracker.R
import com.example.currencytracker.databinding.TopCurrencyLayoutBinding
import com.example.currencytracker.models.CryptoCurrency


class TopMarketAdapter(var context: Context, val list: List<CryptoCurrency>): RecyclerView.Adapter<TopMarketAdapter.TopMarketViewHolder>() {

    inner class TopMarketViewHolder(view: View): ViewHolder(view){
        var binding = TopCurrencyLayoutBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopMarketViewHolder {
        return TopMarketViewHolder(LayoutInflater.from(context).inflate(R.layout.top_currency_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: TopMarketViewHolder, position: Int) {
        val item=list[position]

        holder.binding.topCurrencyNameTextView.text=item.name

        Glide.with(context).load("https://s2.coinmarketcap.com/static/img/coins/64x64/"+"${item.id}"+".png")
            .thumbnail(Glide.with(context).load(R.drawable.spinner))
            .into(holder.binding.topCurrencyImageView)

        Glide.with(context).load("https://s3.coinmarketcap.com/generated/sparklines/web/7d/usd/"+"${item.id}"+".png")
            .thumbnail(Glide.with(context).load(R.drawable.spinner))
            .into(holder.binding.topCurrencyImageView)

        if(item.quotes!![0].percentChange24h>0){
            holder.binding.topCurrencyChangeTextView.setTextColor(context.resources.getColor(R.color.green))
            holder.binding.topCurrencyChangeTextView.text="+ ${item.quotes[0].percentChange24h}%"
        }
        else{
            holder.binding.topCurrencyChangeTextView.setTextColor(context.resources.getColor(R.color.red))
            holder.binding.topCurrencyChangeTextView.text="${item.quotes[0].percentChange24h}%"
        }
    }
}