package com.olivoreo.fiznote.ui.fragments

import android.app.ProgressDialog
import android.util.Patterns
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.olivoreo.fiznote.MainActivity
import com.olivoreo.fiznote.R
import com.olivoreo.fiznote.activities.EnterActivity
import com.olivoreo.fiznote.utilits.*
import kotlinx.android.synthetic.main.fragment_registration.*

class RegistrationFragment : Fragment(R.layout.fragment_registration) {

    private lateinit var mProgresDialog: ProgressDialog
    private lateinit var mEmail: String
    private lateinit var mPassword: String
    private lateinit var mConfirmPassword: String
    private lateinit var mName: String

    override fun onStart() {
        super.onStart()
        registered_btn_next.setOnClickListener { sign_up() }

        mProgresDialog = ProgressDialog(activity)
        mProgresDialog.setTitle(getString(R.string.please_wait))
        mProgresDialog.setMessage("Создание аккаунта...")
        mProgresDialog.setCanceledOnTouchOutside(false)

        AUTH = FirebaseAuth.getInstance()
    }

    private fun sign_up() {
        mEmail = registered_edtxt_email.text.toString()
        mPassword = registered_edtxt_password.text.toString()
        mConfirmPassword = registered_edtxt_confirm_password.text.toString()
        mName = registered_edtxt_name.text.toString()

        if (mName.isEmpty()) {
            registered_edtxt_name.error = "Введите имя"
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(mEmail).matches()) {
            registered_edtxt_email.error = "Неверный формат адреса электронной почты"
        } else if (mPassword.isEmpty()) {
            registered_edtxt_password.error = "Введите пароль"
        } else if (mPassword.length < 6) {
            registered_edtxt_password.error = "Пароль должен содержать более 6 символов"
        } else if (mPassword != mConfirmPassword) {
            registered_edtxt_confirm_password.error = "Пароли должны совпадать"
        } else {
            firebaseSignUp()
        }
    }

    private fun firebaseSignUp() {
        mProgresDialog.show()
        AUTH.createUserWithEmailAndPassword(mEmail, mPassword)
            .addOnCompleteListener { e ->
                if (e.isSuccessful) {
                    val uid: String = AUTH.currentUser?.uid.toString()

                    val dateMap: MutableMap<String, Any> = mutableMapOf<String, Any>()
                    dateMap[CHILD_ID] = uid
                    dateMap[CHILD_EMAIL] = mEmail
                    dateMap[CHILD_NAME] = mName

                    REF_DATABASE_ROOT.child(NODE_USERS).child(uid).updateChildren(dateMap)
                        .addOnCompleteListener { e2 ->
                            if (e2.isSuccessful) {
                                mProgresDialog.dismiss()
                                showToastShort("Добро пожаловать!")
                                (activity as EnterActivity).replaceActivity(MainActivity())
                            } else {
                                mProgresDialog.dismiss()
                                showToastLong("Произошла ошибка добавления данных:\n${e2.exception?.message.toString()}")
                            }
                        }
                } else {
                    mProgresDialog.dismiss()
                    showToastLong("Произошла ошибка регистрации:\n${e.exception?.message.toString()}")
                }
            }
    }
}