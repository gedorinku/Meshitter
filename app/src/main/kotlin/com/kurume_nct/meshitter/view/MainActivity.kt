package com.kurume_nct.meshitter.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.kurume_nct.meshitter.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startActivity(Intent(this,PostActivity::class.java))
    }
}
