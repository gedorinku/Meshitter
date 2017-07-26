package com.kurume_nct.meshitter.view

import android.graphics.Bitmap
import retrofit2.http.*
import rx.Observable

/**
 * Created by hanah on 7/25/2017.
 */
interface CognitiveClient {
    @Headers("Content-Type: multipart/from-data")
    @POST("analyze")
    @Multipart
    fun search(@Query("visualFeatures") visualFeatures: String, @Query("subscription-key") key: String, @Part("image") requestBody: Bitmap) : Observable<Tag>
}