package com.kurume_nct.meshitter.view

import android.view.View
import android.widget.LinearLayout
import com.kurume_nct.meshitter.R
import org.jetbrains.anko.*
import java.util.*

/**
 * Created by gedorinku on 2017/07/25.
 */
class PostUI : AnkoComponent<PostActivity> {

    override fun createView(ui: AnkoContext<PostActivity>): View = with(ui) {
        linearLayout {
            orientation = LinearLayout.VERTICAL
            fitsSystemWindows = true

            editText {
                id = UUID.randomUUID().hashCode()
                gravity = top
                hint = resources.getString(R.string.whats_happening)
            }.lparams {
                width = matchParent
                height = 1530
            }

            button(resources.getText(R.string.tweet)) {
            }.lparams {
                width = wrapContent
                height = wrapContent
            }
        }
    }
}