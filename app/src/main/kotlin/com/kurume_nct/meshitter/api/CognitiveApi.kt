package com.kurume_nct.meshitter.api

import com.kurume_nct.meshitter.view.Tag
import okhttp3.RequestBody
import retrofit2.http.*
import rx.Observable

/**
 * Created by hanah on 7/25/2017.
 */
interface CognitiveApi {
    @POST("analyze")
    fun analyze(@Query("visualFeatures") visualFeatures: String, @Query("subscription-key") key: String, @Body requestBody: RequestBody) : Observable<Tag>
}