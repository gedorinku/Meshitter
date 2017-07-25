package com.kurume_nct.meshitter.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.kurume_nct.meshitter.R
import org.jetbrains.anko.setContentView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val ui = MainUI()
        ui.setContentView(this)

        startActivity(Intent(this, PostActivity::class.java))
    }
}
