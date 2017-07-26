package com.kurume_nct.meshitter.viewmodel

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.view.View
import com.kurume_nct.meshitter.BR
import com.kurume_nct.meshitter.twitter.TwitterUtil
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by gedorinku on 2017/07/26.
 */
class PostViewModel(private val callback: Callback) : BaseObservable() {

    @Bindable
    var tweetBody: String = ""
        get
        set(value) {
            field = value
            notifyPropertyChanged(BR.tweetBody)
        }

    fun onClickTweetButton(view: View) {
        Single.fromCallable {
            val twitter = TwitterUtil.twitter
            twitter.updateStatus(tweetBody)
        }.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe {
                    _ ->
                    callback.onTweeted()
                }
    }

    interface Callback {

        fun onTweeted()
    }
}