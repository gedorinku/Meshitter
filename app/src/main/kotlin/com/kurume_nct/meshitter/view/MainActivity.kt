package com.kurume_nct.meshitter.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import com.kurume_nct.meshitter.R
import com.kurume_nct.meshitter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //startActivity(Intent(this, PostActivity::class.java))
        binding.tabLayout.setupWithViewPager(
                binding.pager.apply {
                    adapter = object : FragmentPagerAdapter(supportFragmentManager) {

                        private val tabs = arrayOf(Pair("Home", { StatusesFragment.newInstance() }))

                        override fun getItem(position: Int): Fragment = tabs[position].second()

                        override fun getPageTitle(position: Int): CharSequence
                                = tabs[position].first

                        override fun getCount(): Int = tabs.size
                    }
                }
        )

    }
}
