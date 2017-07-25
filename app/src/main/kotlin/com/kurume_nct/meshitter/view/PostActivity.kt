package com.kurume_nct.meshitter.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.kurume_nct.meshitter.R
import org.jetbrains.anko.setContentView

class PostActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val ui = PostUI()
        ui.setContentView(this)
    }
}
