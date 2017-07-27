package com.kurume_nct.meshitter.api

import android.support.test.runner.AndroidJUnit4
import android.text.BoringLayout
import io.reactivex.observers.TestObserver
import io.reactivex.subscribers.TestSubscriber
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import java.io.File

/**
 * Created by hanah on 7/26/2017.
 */
@RunWith(AndroidJUnit4::class)
class CognitiveClientTest {
    @Test
    fun isFood() {
        val inputStream = javaClass.classLoader.getResourceAsStream("hunachi.jpg")
        val observer = TestObserver<Boolean>()
        CognitiveClient().isFood(inputStream).toObservable().subscribe(observer)
        observer.await()
        observer.assertNoErrors()
    }
}