package com.olivoreo.fiznote

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.olivoreo.fiznote.utilits.replaceActivity

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        replaceActivity(MainActivity())
    }
}