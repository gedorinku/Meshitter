package com.kurume_nct.meshitter.api

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
import okhttp3.MediaType
import okhttp3.RequestBody
import rx.Observable
import java.io.*


/**
 * Created by hanah on 7/25/2017.
 */

class CognitiveClient {

    val cognitiveApi: CognitiveApi

    init {
        val gson = GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
        val retrofit = Retrofit.Builder()
                .baseUrl("https://southeastasia.api.cognitive.microsoft.com/vision/v1.0/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
        cognitiveApi = retrofit.create(CognitiveApi::class.java)
    }

    fun isFood(file: File): Boolean {

        val SubscriptionKey: String = "0123456789abcdef0123456789abcdef";
        val client: VisionServiceClient = VisionServiceRestClient("key")
        val inputStream: InputStream = FileInputStream(file)
        val feature = arrayOf("food")
        val detail = arrayOf("")

        //val fbody = RequestBody.create(MediaType.parse("multipart/form-data"), file)

        //非同期にする
        val result = client.analyzeImage(inputStream, feature, detail).tags
                .map {
                    it.name.compareTo("food")
                }

        return result.isNotEmpty()

        /*return cognitiveApi.analyze(Secrets.apiKey, fbody)
                .map {
                    Log.d("Test", it.tags[0])
                    it.tags.map { it.compareTo("food") }
                    Log.d("Tag ", "pass")
                    it.tags.isNotEmpty()
                }*/
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

/*fun onSearch() : Boolean {
    Log.d("API","hitting")
    val bmp : Bitmap = BitmapFactory.decodeResource(this.resources,R.drawable.bird)
    var contain : Boolean = false
    return contain
}*/
/*val fbody = RequestBody.create(MediaType.parse("image/*"), bmp)
CognitiveClient().run {
    val info = packageManager.getApplicationInfo(packageName, PackageManager.GET_META_DATA)
    cognitiveClient.analyze("Description/tags",info.metaData.getString("ApiKey"), fbody)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("Test",it.tags[0])
                it.tags.map { it.compareTo("food") }
                contain = it.tags.isNotEmpty()
                Log.d("Tag ", "pass")
            },{
                Log.d("Tag" , "error")
            })
}
*/
Log.d(t.toString(),"あああ")
return contain
}
}
/*
class CognitiveClientViewUI : AnkoComponent<CognitiveClientView>{
override fun createView(ui: AnkoContext<CognitiveClientView>) = with(ui){
linearLayout {
    orientation = LinearLayout.VERTICAL
    textView("Hello Kotlin1")
    button("analyze Photo"){
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