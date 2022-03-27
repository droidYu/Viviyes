package com.droidyu.viviyes

import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.droidyu.viviyes.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val titles = arrayListOf("排行榜", "搜索", "酒逢知己", "我的资料")
        val unSelectedIcons = arrayListOf(
            R.drawable.tab_icon_toplists,
            R.drawable.tab_icon_search,
            R.drawable.tab_icon_friends,
            R.drawable.tab_icon_mine
        )
        val selectedIcons = arrayListOf(
            R.drawable.tab_icon_toplists_active,
            R.drawable.tab_icon_search_active,
            R.drawable.tab_icon_friends_active,
            R.drawable.tab_icon_mine_active
        )
        val fragments = arrayListOf(
            SortFragment(),
            SearchFragment(),
            GroupFragment(),
            MineFragment()
        )

        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
            .apply {

                repeat(titles.size) {
                    tabLayout.addTab(tabLayout.newTab())
                }
                viewPager.apply {
                    adapter = MyPageAdapter(supportFragmentManager, lifecycle, fragments)

                    registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                        override fun onPageSelected(position: Int) {
                            myTitle.text = titles[position]
                        }
                    })

                }

                tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                    override fun onTabSelected(tab: TabLayout.Tab) {
                        tab.icon = resources.getDrawable(selectedIcons[tab.position], null)
                    }

                    override fun onTabUnselected(tab: TabLayout.Tab) {
                        tab.icon = resources.getDrawable(unSelectedIcons[tab.position], null)
                    }

                    override fun onTabReselected(tab: TabLayout.Tab) {
                    }
                })

                TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                    tab.icon = resources.getDrawable(unSelectedIcons[position], null)
                }.attach()

                registerForContextMenu(ivMenu)
                ivMenu.setOnClickListener {
                    ivMenu.performLongClick(ivMenu.x, ivMenu.y)
                }


            }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        menuInflater.inflate(R.menu.menu_main, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return true
    }

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

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        when (event.keyCode) {
            KeyEvent.KEYCODE_BACK -> finish()
        }
        return true
    }
}