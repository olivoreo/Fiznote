package com.olivoreo.fiznote.ui.fragments

import android.app.ProgressDialog
import android.util.Patterns
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.olivoreo.fiznote.MainActivity
import com.olivoreo.fiznote.R
import com.olivoreo.fiznote.activities.EnterActivity
import com.olivoreo.fiznote.utilits.*
import kotlinx.android.synthetic.main.fragment_enter.*

class EnterFragment : Fragment(R.layout.fragment_enter) {

    private lateinit var mEmail: String
    private lateinit var mPassword: String
    private lateinit var mProgressDialog: ProgressDialog

    override fun onStart() {
        super.onStart()
        next_enter.setOnClickListener { login() }
        next_register.setOnClickListener { registration() }

        mProgressDialog = ProgressDialog(activity)
        mProgressDialog.setTitle(getString(R.string.please_wait))
        mProgressDialog.setMessage(getString(R.string.enter_ex))
        mProgressDialog.setCanceledOnTouchOutside(false)

        AUTH = FirebaseAuth.getInstance()
    }

    private fun registration() {
        replaceFragment(RegistrationFragment())
    }

    private fun login() {
        mEmail = registered_edtxt1.text.toString()
        mPassword = registered_edtxt2.text.toString()

        if (Patterns.EMAIL_ADDRESS.matcher(mEmail).matches() && mPassword.isNotEmpty()) {
            firebaseLogin()
        } else {
            if (mEmail.isEmpty()) {
                registered_edtxt1.error = "Введите адрес эл. почты"
            }
            if (mPassword.isEmpty()) {
                registered_edtxt2.error = "Введите пароль"
            }
            else if (!Patterns.EMAIL_ADDRESS.matcher(mEmail).matches()){
                registered_edtxt1.error = "Неверный адрес электронной почты"
            }
        }
    }

    private fun firebaseLogin() {
        mProgressDialog.show()
        AUTH.signInWithEmailAndPassword(mEmail, mPassword)
            .addOnCompleteListener { e ->
                if (e.isSuccessful) {
                    mProgressDialog.dismiss()
                    showToastShort("Добро пожаловать!")
                    (activity as EnterActivity).replaceActivity(MainActivity())
                } else {
                    mProgressDialog.dismiss()
                    showToastLong("Произошла ошибка входа:\n${e.exception?.message.toString()}")
                }
            }
    }
}