package com.kurume_nct.meshitter.api

import com.kurume_nct.meshitter.R
import org.junit.Test

import org.junit.Assert.*
import java.io.File

/**
 * Created by hanah on 7/26/2017.
 */
class CognitiveClientTest {
    @Test
    fun isFood() {
        CognitiveClient().isFood(File("ScreenShoto.png")).subscribe {
            println(it)
        }
    }
}