package com.droidyu.viviyes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.droidyu.viviyes.databinding.FragmentSearchBinding
import com.google.android.material.tabs.TabLayoutMediator

class SearchFragment : Fragment() {

    val titles = arrayListOf("TYPE", "PAIRING", "STYLE")


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return DataBindingUtil.inflate<FragmentSearchBinding>(
            inflater,
            R.layout.fragment_search,
            container,
            false
        ).apply {
            repeat(titles.size) {
                tabLayout.addTab(tabLayout.newTab())
            }


            val fragments = arrayListOf(
                WineFragment(),
                WineFragment(),
                WineFragment()
            )
            viewPager.apply {
                adapter = MyPageAdapter(parentFragmentManager, lifecycle, fragments)
            }
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = titles[position]
            }.attach()


        }.root
    }


}