package com.olivoreo.fiznote.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.olivoreo.fiznote.databinding.ActivityEnter2Binding
import com.olivoreo.fiznote.ui.fragments.EnterFragment
import com.olivoreo.fiznote.utilits.initFirebase
import com.olivoreo.fiznote.utilits.replaceFragment

class EnterActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityEnter2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityEnter2Binding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initFirebase()
    }

    override fun onStart() {
        super.onStart()
        replaceFragment(EnterFragment(),false)
    }
}