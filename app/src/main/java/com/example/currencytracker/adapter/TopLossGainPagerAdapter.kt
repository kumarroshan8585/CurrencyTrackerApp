package com.example.currencytracker.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.currencytracker.fragment.TopLossGainFragment

class TopLossGainPagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        val fragment=TopLossGainFragment()
        val bundle=Bundle()  //A Bundle is created to hold arguments.

        bundle.putInt("position", position) // The position of the fragment (either 0 or 1) is put into the bundle using bundle.putInt("position", position).
        fragment.arguments=bundle
        return fragment
    }

}