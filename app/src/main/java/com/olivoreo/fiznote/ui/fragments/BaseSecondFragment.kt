package com.olivoreo.fiznote.ui.fragments

import android.app.Activity
import android.content.Context
import androidx.fragment.app.Fragment
import com.olivoreo.fiznote.MainActivity

open class BaseSecondFragment(layout: Int) : Fragment(layout) {
    protected lateinit var mActivity: Activity

    override fun onStart() {
        super.onStart()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = (context as Activity)
    }
}