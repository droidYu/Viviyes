package com.droidyu.viviyes

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class MyPageAdapter(
    manager: FragmentManager,
    mLifecycle: Lifecycle,
    private val fragments: List<Fragment>
) :
    FragmentStateAdapter(manager, mLifecycle) {
    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}