package com.kurume_nct.meshitter.viewmodel

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.databinding.BaseObservable
import android.databinding.Bindable
import android.net.Uri
import android.util.Log
import android.view.View
import com.kurume_nct.meshitter.BR
import com.kurume_nct.meshitter.api.CognitiveClient
import com.kurume_nct.meshitter.toMediaPath
import com.kurume_nct.meshitter.twitter.TwitterUtil
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import twitter4j.StatusUpdate
import java.io.File

/**
 * Created by gedorinku on 2017/07/26.
 */
class PostViewModel(private val callback: Callback, private val context: Context) : BaseObservable() {

    val REQUEST_CODE = 334
    var isMeshiTerro = false

    @Bindable
    var tweetBody: String = ""
        get
        set(value) {
            field = value
            notifyPropertyChanged(BR.tweetBody)
        }

    var imageUris: MutableList<Uri> = mutableListOf()

    fun onClickTweetButton(view: View) {
        Single.fromCallable {
            val twitter = TwitterUtil.twitter
            twitter.updateStatus(
                    StatusUpdate(tweetBody).apply {
                        setMediaIds(
                                *imageUris.map {
                                    twitter.uploadMedia(File(it.toMediaPath(context))).mediaId
                                }.toLongArray()
                        )
                    })
        }.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe {
                    _ ->
                    callback.onTweeted()
                }
    }

    fun onClickAddImageButton(view: View) {
        val intent = Intent(Intent.ACTION_GET_CONTENT).apply { type = "image/*" }
        callback.startActivityForResult(intent, REQUEST_CODE)
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode != REQUEST_CODE || resultCode != Activity.RESULT_OK) return

        val uri = data?.data ?: return
        imageUris.add(uri)

        //"I'll select whether to attack....huhhuhuhhu."
        imageUris.map {
            val inputStream = context.contentResolver.openInputStream(it)
            CognitiveClient().isFood(inputStream)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        if (it && !isMeshiTerro) {
                            isMeshiTerro = true
                            addMeshiTerroTargets()
                        }
                    },{
                        Log.d("imageUris", "error")
                    })
        }
    }

    private fun addMeshiTerroTargets() {
        TwitterUtil.nextMeshiTerroTargetsScreenNames()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    targets ->
                    tweetBody = targets.joinToString(separator = " @", prefix = "@")
                }
    }

    interface Callback {

        fun onTweeted()

        fun startActivityForResult(intent: Intent, requestCode: Int)
    }
}