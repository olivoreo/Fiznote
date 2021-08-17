package com.olivoreo.fiznote.ui.fragments

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.olivoreo.fiznote.R
import com.olivoreo.fiznote.activities.EnterActivity
import com.olivoreo.fiznote.utilits.*
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : BaseFragment(R.layout.fragment_settings) {

    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(true)
        initFields()
        settings_btn_change_email.setOnClickListener { changeEmail() }
    }

    private fun changeEmail() {
        replaceFragment(ChangeEmailFragment())
    }

    private fun initFields() {
        settings_name_card.text = USER.name
        settings_text_change_email.text = USER.email
        settings_profile_image.dowmloadAndSetImage(USER.photoUrl)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.settings_acrion_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings_menu_exit -> {
                AUTH.signOut()
                APP_ACTIVITY.replaceActivity(EnterActivity())
                showToastShort("Вы вышли из аккаунта")
            }
            R.id.settings_menu_change_name -> replaceFragment(ChangeNameFragment())
            R.id.settings_menu_add_profile_photo -> {
                CropImage.activity()
                    .setAspectRatio(1, 1)
                    .setRequestedSize(512, 512)
                    .setCropShape(CropImageView.CropShape.OVAL)
                    .start(APP_ACTIVITY, this)
            }
        }
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE
            && resultCode == RESULT_OK && data != null
        ) {
            val uri = CropImage.getActivityResult(data).uri
            val path = REF_STORAGE_ROOT.child(FOLDER_PROFILE_IMAGE)
                .child(CURRENT_UID)

            putImageToStorage(uri, path) {
                getUrlFromStorage(path) {
                    putUrlToDatabase(it) {
                        settings_profile_image.dowmloadAndSetImage(it)
                        showToastShort("Данные обновлены")
                        USER.photoUrl = it
                    }
                }
            }
        }
    }
}