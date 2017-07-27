package com.kurume_nct.meshitter.viewmodel

import twitter4j.Status

/**
 * Created by gedorinku on 2017/07/27.
 */
class StatusViewModel(private val callback: Callback, private val status: Status) {

    val screenName: String
        get() = "@${status.user.screenName}"

    val content: String
        get() = status.text

    interface Callback {
    }
}