package com.kurume_nct.meshitter.viewmodel

import android.os.Bundle
import com.kurume_nct.meshitter.twitter.TwitterUtil
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import twitter4j.Paging
import twitter4j.Status

/**
 * Created by gedorinku on 2017/07/27.
 */
class StatusesViewModel(private val callback: Callback) {

    private val statuses: MutableList<Status> = mutableListOf()

    val statusList: List<Status> = statuses

    fun onCreateView(arguments: Bundle?) {
        Single.fromCallable {
            val twitter = TwitterUtil.twitter
            val paging = Paging(1, 10)
            twitter.getHomeTimeline(paging)
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
            it ->
            statuses.addAll(0, it)
            callback.onItemRangeInserted(0, it.size)
        }
    }

    interface Callback {

        fun onItemRangeInserted(positionStart: Int, itemCount: Int)
    }
}