package com.kurume_nct.meshitter.view

import android.graphics.Bitmap
import okhttp3.RequestBody
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query
import rx.Observable
import rx.Single
import java.util.*

/**
 * Created by hanah on 7/25/2017.
 */
interface CognitiveClient {

    @POST("analyze")
    fun search(@Query("visualFeatures") visualFeatures : String,@Query("Tags&subscription-key=") key : String, @Part requestBody: RequestBody) : Observable<Article<Tag>>
}