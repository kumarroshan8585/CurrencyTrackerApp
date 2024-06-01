package com.example.currencytracker.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.currencytracker.adapter.MarketAdapter
import com.example.currencytracker.api.ApiInterface
import com.example.currencytracker.api.ApiUtilities
import com.example.currencytracker.databinding.FragmentTopLossGainBinding
import com.example.currencytracker.models.CryptoCurrency
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Collections


class TopLossGainFragment : Fragment() {

    lateinit var binding: FragmentTopLossGainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentTopLossGainBinding.inflate(layoutInflater)

        getMarketData()
        return binding.root
    }

    private fun getMarketData() {

        val position=requireArguments().getInt("position")
        lifecycleScope.launch(Dispatchers.IO){
            val res=ApiUtilities.getInstance().create(ApiInterface::class.java).getMarketData() //Again calling api

            if(res.body()!=null){
                withContext(Dispatchers.Main){
                    val dataItem=res.body()!!.data.cryptoCurrencyList

                    Collections.sort(dataItem){ //So that items are shown in sorted manner in list
                        o1, o2->(o2.quotes[0].percentChange24h.toInt())
                        .compareTo(o1.quotes[0].percentChange24h.toInt())
                    }
                    binding.spinKitView.visibility=GONE
                    val list=ArrayList<CryptoCurrency>()

                    if(position==0){
                        list.clear()

                        for(i in 0..9){
                            list.add(dataItem[i])
                        }

                        binding.topGainLoseRecyclerView.adapter=MarketAdapter(
                            requireContext(),
                            list,
                            "home"
                        )
                    }else{
                        list.clear()

                        for(i in 0..9){
                            list.add(dataItem[dataItem.size-1-i])
                        }

                        binding.topGainLoseRecyclerView.adapter=MarketAdapter(
                            requireContext(),
                            list,
                            "home"
                        )
                    }

                }
            }
        }
    }


}