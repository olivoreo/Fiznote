package com.olivoreo.fiznote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import com.olivoreo.fiznote.MainActivity
import com.olivoreo.fiznote.utilits.replaceActivity

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        replaceActivity(MainActivity())
    }
}