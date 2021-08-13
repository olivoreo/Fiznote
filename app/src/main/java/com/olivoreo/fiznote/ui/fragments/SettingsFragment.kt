package com.olivoreo.fiznote.ui.fragments

import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.olivoreo.fiznote.MainActivity
import com.olivoreo.fiznote.R
import com.olivoreo.fiznote.activities.EnterActivity
import com.olivoreo.fiznote.utilits.AUTH
import com.olivoreo.fiznote.utilits.replaceActivity
import kotlinx.android.synthetic.main.fragment_registration.*
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : BaseFragment(R.layout.fragment_settings) {
    override fun onStart() {
        super.onStart()
        settings_btn_exit.setOnClickListener { exit() }
        AUTH = FirebaseAuth.getInstance()
    }

    private fun exit() {
        AUTH.signOut()
        (activity as MainActivity).replaceActivity(EnterActivity())
        Toast.makeText(activity, "Вы вышли из аккаунта", Toast.LENGTH_SHORT).show()
    }
}