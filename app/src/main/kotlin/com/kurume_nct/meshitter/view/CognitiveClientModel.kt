package com.kurume_nct.meshitter.view

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
import org.jetbrains.anko.*


/**
* Created by hanah on 7/25/2017.
*/
class CognitiveClientModel {
    val gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()!!
    val retrofit = Retrofit.Builder()
            .baseUrl("https://westus.api.cognitive.microsoft.com/vision/v1.0/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build()!!
    val cognitiveClient = retrofit.create(CognitiveClient::class.java)!!
}

class CognitiveClientModelView{
    lateinit var responce : List<String>
    var contain : Boolean = false
    init{
        CognitiveClientModel().run {
            cognitiveClient.search((R.drawable.bird as BitmapDrawable).bitmap)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        it.run { description.let { responce.map { it.compareTo("food") } } }
                        Log.d(responce[0],"a!")
                    },{
                        Log.d("error" , "a!")
                    })
        }
        if(responce.isNotEmpty()){
            contain = true
        }
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
                        val contain = CognitiveClientModelView().contain
                        uiThread {
                            toast(contain.toString())
                        }
                    }
                }
            }
        }
    }

}