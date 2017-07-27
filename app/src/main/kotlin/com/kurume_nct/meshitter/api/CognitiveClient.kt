package com.kurume_nct.meshitter.api


import android.os.Environment
import android.util.Log
import com.kurume_nct.meshitter.twitter.Secrets
import com.microsoft.projectoxford.vision.VisionServiceClient
import com.microsoft.projectoxford.vision.VisionServiceRestClient
import io.reactivex.Observable
import io.reactivex.Single
import java.io.*
import java.net.URI


/**
 * Created by hanah on 7/25/2017.
 */

class CognitiveClient {
    fun isFood(inputStream: InputStream): Single<Boolean> =
            Single.fromCallable {
                Log.d("Run","isFood ! ")
                val client: VisionServiceClient = VisionServiceRestClient(
                        Secrets.apiKey,
                        "https://southeastasia.api.cognitive.microsoft.com/vision/v1.0/"
                )
                //val inputStream: InputStream = FileInputStream(path)
                val feature = arrayOf("description")
                val detail = arrayOf("")
                // 非同期にする
                val result = client.analyzeImage(inputStream, feature, detail)
                result.description.tags
                        .any{
                            Log.d("Result ",it)
                            it.contains("food")
                        }
            }
}
