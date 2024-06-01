package com.example.currencytracker.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.example.currencytracker.R
import com.example.currencytracker.adapter.TopLossGainPagerAdapter
import com.example.currencytracker.adapter.TopMarketAdapter
import com.example.currencytracker.api.ApiInterface
import com.example.currencytracker.api.ApiUtilities
import com.example.currencytracker.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.ParsePosition


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentHomeBinding.inflate(layoutInflater)
        getTopCurrencyList()

        setTabLayout()
        return binding.root
    }

    private fun setTabLayout() {
        val adapter=TopLossGainPagerAdapter(this)
        binding.contentViewPager.adapter=adapter


        //registerOnPageChangeCallback: Registers a callback to listen to page changes in the ViewPager2.
        // Depending on the selected page (position), it updates the visibility of indicators for "Top Gainers" and "Top Losers".
        binding.contentViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if(position==0){
                    binding.topGainIndicator.visibility=VISIBLE
                    binding.topLoseIndicator.visibility=GONE
                }
                else{
                    binding.topGainIndicator.visibility=GONE
                    binding.topLoseIndicator.visibility= VISIBLE
                }
            }
        })


        TabLayoutMediator(binding.tabLayout, binding.contentViewPager){  //TabLayoutMediator: Connects the TabLayout with the ViewPager2, setting the tab titles based on the position.
            tab, position->
            var title=if(position==0){
                "Top Gainers"
            }else{
                "Top Losers"
            }

            tab.text=title
        }.attach()
    }

    private fun getTopCurrencyList() {
        lifecycleScope.launch(Dispatchers.IO){//Api se maal nikalne ka kaam background mai hoga
            val res=ApiUtilities.getInstance().create(ApiInterface::class.java).getMarketData()  //creates an instance of ApiInterface using a singleton Retrofit instance provided by ApiUtilities
            //getMarketData() is called to make the network request.

            withContext(Dispatchers.Main){
                binding.topCurrencyRecyclerView.adapter=TopMarketAdapter(requireContext(), res.body()!!.data.cryptoCurrencyList)
            }//UI ke saath jo bhi kaam hoga main thread pe hoga

            Log.d("ROSHAN", "topCurrencyList: ${res.body()!!.data.cryptoCurrencyList}")
        }
    }


}