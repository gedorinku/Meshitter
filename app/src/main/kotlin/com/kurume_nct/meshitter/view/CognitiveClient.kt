package com.kurume_nct.meshitter.view

import android.graphics.Bitmap
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable
import rx.Single
import java.util.*

/**
 * Created by hanah on 7/25/2017.
 */
interface CognitiveClient {
    @GET("analyze?visualFeatures=Description,Tags&subscription-key=${"ApiKey"}")
    fun search(@Query("query") query: Bitmap) : Observable<Article<Tag>>
}