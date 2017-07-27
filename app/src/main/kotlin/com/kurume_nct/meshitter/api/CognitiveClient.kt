package com.kurume_nct.meshitter.api

import com.kurume_nct.meshitter.twitter.Secrets
import com.microsoft.projectoxford.vision.VisionServiceClient
import com.microsoft.projectoxford.vision.VisionServiceRestClient
import io.reactivex.Observable
import io.reactivex.Single
import java.io.*


/**
 * Created by hanah on 7/25/2017.
 */

class CognitiveClient {

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
