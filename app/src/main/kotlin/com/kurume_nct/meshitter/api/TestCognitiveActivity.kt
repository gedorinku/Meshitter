package com.kurume_nct.meshitter.api

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.kurume_nct.meshitter.R
import com.kurume_nct.meshitter.view.MainActivity
import com.kurume_nct.meshitter.view.PostActivity
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.Schedulers
import junit.framework.Test
import org.apache.commons.io.FileUtils.openInputStream


class TestCognitiveActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_cognitive)
        val uri = Uri.parse("android.resource://" + packageName + "/" + R.drawable.screenchoto)
        val inputStream = this.contentResolver.openInputStream(uri)
        val observer = TestObserver<Boolean>()
        CognitiveClient().isFood(inputStream).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer)
        observer.await()
        observer.assertNoErrors()
    }

    override fun onPause() {
        super.onPause()
        val intent : Intent = Intent(this, PostActivity::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
}
