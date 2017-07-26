package com.kurume_nct.meshitter.view

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.kurume_nct.meshitter.R
import com.kurume_nct.meshitter.databinding.ActivityPostBinding
import com.kurume_nct.meshitter.viewmodel.PostViewModel

class PostActivity : AppCompatActivity(), PostViewModel.Callback {

    private lateinit var binding: ActivityPostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_post)

        binding.viewModel = PostViewModel(this)
    }

    override fun onTweeted() {
        finish()
    }
}
