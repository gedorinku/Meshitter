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
    fun analyze( @Header("Ocp-Apim-Subscription-Key") apiKey: String, @Body image: RequestBody ) : Observable<Tag>
}