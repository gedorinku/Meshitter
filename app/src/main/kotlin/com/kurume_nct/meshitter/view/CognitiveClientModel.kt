package com.kurume_nct.meshitter.view

import android.graphics.Bitmap
import android.util.Log
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.kurume_nct.meshitter.R
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.widget.LinearLayout
import okhttp3.MediaType
import org.jetbrains.anko.*
import okhttp3.RequestBody
import java.io.File
import java.util.function.BinaryOperator
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
            .build()!!
    val cognitiveClient = retrofit.create(CognitiveClient::class.java)!!
}

class CognitiveClientModelView{
    lateinit var responce : List<String> //怖いnullPointaExeption待ったなし。
    fun onSearch() : Boolean{
        val partFile : File = R.drawable.bird as File //絶対怪しい
        val fbody = RequestBody.create(MediaType.parse("analysisResult"), partFile)
        CognitiveClientModel().run {
            cognitiveClient.search("Description","apikey",fbody)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        it.run { description.let { responce.map { it.compareTo("food") } } }
                    },{
                        Log.d("Tag" , "error")
                    })
        }
        return responce.isNotEmpty()
    }
}

class CognitiveClientView : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        CognitiveClientViewUI().setContentView(this)
    }
}

class CognitiveClientViewUI : AnkoComponent<CognitiveClientView>{
    override fun createView(ui: AnkoContext<CognitiveClientView>) = with(ui){
        linearLayout {
            orientation = LinearLayout.VERTICAL
            button("search Photo"){
                onClick {
                    doAsync {
                        val contain = CognitiveClientModelView().onSearch()
                        uiThread {
                            toast(contain.toString())//True or False
                        }
                    }
                }
            }
        }
    }

}