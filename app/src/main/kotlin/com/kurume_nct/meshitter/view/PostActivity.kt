package com.kurume_nct.meshitter.view

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kurume_nct.meshitter.R
import com.kurume_nct.meshitter.databinding.ActivityPostBinding
import com.kurume_nct.meshitter.viewmodel.PostViewModel

class PostActivity : AppCompatActivity(), PostViewModel.Callback {

    private lateinit var binding: ActivityPostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_post)

        binding.viewModel = PostViewModel(this, this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        binding.viewModel.onActivityResult(requestCode, resultCode, data)
    }

    override fun onTweeted() {
        finish()
    }
}
