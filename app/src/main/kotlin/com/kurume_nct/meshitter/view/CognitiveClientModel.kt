package com.kurume_nct.meshitter.view

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
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        CognitiveClientViewUI().setContentView(this)
    }
    fun onSearch() : Boolean{
        val resource : Resources = resources
        val bmp : Bitmap = BitmapFactory.decodeResource(resource,R.drawable.bird)
        var contain : Boolean = false
        CognitiveClientModel().run {
            cognitiveClient.search("Description","apikey",bmp)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        it.run { description.run{ tags.map { it.compareTo("food") } } }
                        contain = it.description.tags.isNotEmpty()
                    },{
                        Log.d("Tag" , "error")
                    })
        }
        return contain
    }
}

class CognitiveClientViewUI : AnkoComponent<CognitiveClientView>{
    override fun createView(ui: AnkoContext<CognitiveClientView>) = with(ui){
        linearLayout {
            orientation = LinearLayout.VERTICAL
            button("search Photo"){
                onClick {
                    doAsync {
                        when{
                            CognitiveClientView().onSearch()
                            -> uiThread { toast("食べ物です")}
                            else
                            -> uiThread { toast("食べ物じゃないです") }
                        }
                    }
                }
            }
        }
    }

}