package com.kurume_nct.meshitter.api

import android.content.res.AssetManager
import android.os.AsyncTask
import android.util.Log
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.kurume_nct.meshitter.R
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.*
import android.widget.Button
import com.kurume_nct.meshitter.view.Secrets
import com.microsoft.projectoxford.vision.VisionServiceClient
import com.microsoft.projectoxford.vision.VisionServiceRestClient
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.MediaType
import okhttp3.RequestBody
import java.io.*


/**
 * Created by hanah on 7/25/2017.
 */

class CognitiveClient {

    var result: Boolean
    //val cognitiveApi: CognitiveApi

    init {
        result = false
        /*val gson = GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
        val retrofit = Retrofit.Builder()
                .baseUrl("https://southeastasia.api.cognitive.microsoft.com/vision/v1.0/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
        cognitiveApi = retrofit.create(CognitiveApi::class.java)
        */
    }


    var observable: Observable<Object> = Observable.empty()

    fun isFood(file: String): Single<Boolean> =
            Single.fromCallable {
                val client: VisionServiceClient = VisionServiceRestClient(
                        Secrets.apiKey,
                        "https://southeastasia.api.cognitive.microsoft.com/vision/v1.0/"
                )
                val inputStream: InputStream = FileInputStream(file)
                val feature = arrayOf("Description")
                val detail = arrayOf("")
                // 非同期にする
                client.analyzeImage(inputStream, feature, detail).description.tags
                        .any{
                            it.contains("food")
                        }
            }
}


class CognitiveClientView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startButton: Button = findViewById(R.id.button) as Button

        startButton.onClick {
            /*when {
            //onSearch()
                CognitiveClient().ok -> toast("食べ物です")
                else -> toast("食べ物じゃないです")
            }*/
        }
        //CognitiveClientViewUI().setContentView(this)
    }
}