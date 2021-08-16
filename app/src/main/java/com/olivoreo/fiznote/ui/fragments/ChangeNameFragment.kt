package com.olivoreo.fiznote.ui.fragments

import android.view.View
import android.view.WindowManager
import com.olivoreo.fiznote.MainActivity
import com.olivoreo.fiznote.R
import com.olivoreo.fiznote.utilits.*
import kotlinx.android.synthetic.main.fragment_change_name.*

class ChangeNameFragment : BaseChangeFragment(R.layout.fragment_change_name) {

    private lateinit var mName:String

    override fun onResume() {
        super.onResume()
        settings_add_name.setText(USER.name)
        settings_add_name.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus){
                settings_change_name_desc.visibility = View.GONE
                settings_add_name.setText("")
                //activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
            }
        }
    }

    override fun change() {
        mName = settings_add_name.text.toString()
        if(mName.isNotEmpty()){
            REF_DATABASE_ROOT.child(NODE_USERS).child(UID).child(CHILD_NAME)
                .setValue(mName)
                .addOnCompleteListener { e ->
                    if (e.isSuccessful){
                        USER.name = mName
                        fragmentManager?.popBackStack()
                    }
                }
        }
    }
}