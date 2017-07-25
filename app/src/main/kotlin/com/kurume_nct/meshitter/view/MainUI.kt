package com.kurume_nct.meshitter.view

import android.view.View
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.relativeLayout
import org.jetbrains.anko.textView

/**
 * Created by gedorinku on 2017/07/25.
 */
class MainUI(): AnkoComponent<MainActivity> {

    override fun createView(ui: AnkoContext<MainActivity>): View = with(ui) {
        relativeLayout {
            textView("Hello World")
        }
    }
}