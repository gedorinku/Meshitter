package com.kurume_nct.meshitter.view

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.kurume_nct.meshitter.R
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.widget.LinearLayout
import org.jetbrains.anko.*
import android.content.pm.PackageManager
import android.content.pm.ApplicationInfo
import android.view.Gravity
import android.widget.Button


/**
* Created by hanah on 7/25/2017.
*/
class CognitiveClientModel {
    val gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()
    val retrofit = Retrofit.Builder()
            .baseUrl("https://westus.api.cognitive.microsoft.com/vision/v1.0/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build()
    val cognitiveClient = retrofit.create(CognitiveClient::class.java)!!
}


class CognitiveClientView : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startButton : Button = findViewById(R.id.button) as Button

        startButton.onClick {
            when{
                onSearch() -> toast("食べ物です")
                else -> toast("食べ物じゃないです")
            }
        }
        //CognitiveClientViewUI().setContentView(this)
    }

    fun onSearch() : Boolean {
        Log.d("API","hitting")
        val bmp : Bitmap = BitmapFactory.decodeResource(this.resources,R.drawable.bird)
        var contain : Boolean = false
        CognitiveClientModel().run {
            val info = packageManager.getApplicationInfo(packageName, PackageManager.GET_META_DATA)
            cognitiveClient.search("Description/tags",info.metaData.getString("ApiKey"),bmp)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        it.tags.map { it.compareTo("food") }
                        contain = it.tags.isNotEmpty()
                        Log.d("Tag ", "pass")
                    },{
                        Log.d("Tag" , "error")
                    })
        }
        return contain
    }
}
/*
class CognitiveClientViewUI : AnkoComponent<CognitiveClientView>{
    override fun createView(ui: AnkoContext<CognitiveClientView>) = with(ui){
        linearLayout {
            orientation = LinearLayout.VERTICAL
            textView("Hello Kotlin1")
            button("search Photo"){
                onClick {
                    Log.d("いいいいいいいい","いいいいいいい")
                    //toast("Hello")
                    //doAsync {
                        when{
                            CognitiveClientView().onSearch()
                            -> //uiThread {
                                toast("食べ物です")
                            //}
                            else
                            -> //uiThread {
                                toast("食べ物じゃないです")
                            //}                        }
                    }
                }
            }
        }
    }
}*/