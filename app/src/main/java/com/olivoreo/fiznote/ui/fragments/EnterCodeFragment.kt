package com.olivoreo.fiznote.ui.fragments

import android.widget.Toast
import androidx.fragment.app.Fragment
import com.olivoreo.fiznote.R
import com.olivoreo.fiznote.utilits.AppTextWatcher
import kotlinx.android.synthetic.main.fragment_enter_code.*

class EnterCodeFragment : Fragment(R.layout.fragment_enter_code) {
    override fun onStart() {
        super.onStart()
        registered_edtxt_enter_code.addTextChangedListener(AppTextWatcher {
                val string = registered_edtxt_enter_code.text.toString()
                if (string.length==6){
                    verificationCode()
                }

        })
    }

    private fun verificationCode() {
        Toast.makeText(activity, "OK!", Toast.LENGTH_SHORT).show()
    }
}