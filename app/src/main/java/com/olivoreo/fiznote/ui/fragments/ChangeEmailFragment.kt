package com.olivoreo.fiznote.ui.fragments

import android.util.Patterns
import android.view.View
import com.olivoreo.fiznote.R
import com.olivoreo.fiznote.utilits.*
import kotlinx.android.synthetic.main.fragment_change_email.*

class ChangeEmailFragment : BaseChangeFragment(R.layout.fragment_change_email) {

    private lateinit var mEmail:String

    override fun onResume() {
        super.onResume()
        settings_add_email.setText(USER.email)
        settings_add_email.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                settings_change_email_desc.visibility = View.GONE
                settings_add_email.setText("")
            }
        }
    }

    override fun change() {
        mEmail = settings_add_email.text.toString()
        if(mEmail.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(mEmail).matches()){
            REF_DATABASE_ROOT.child(NODE_USERS).child(UID).child(CHILD_EMAIL)
                .setValue(mEmail)
                .addOnCompleteListener { e ->
                    if (e.isSuccessful){
                        USER.email = mEmail
                        fragmentManager?.popBackStack()
                    }
                }
        } else settings_add_email.error = "Неверный формат адреса электронной почты"
    }
}