package com.olivoreo.fiznote.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.olivoreo.fiznote.R
import com.olivoreo.fiznote.database.DatabaseManager
import com.olivoreo.fiznote.databinding.ActivityEditBinding
import com.olivoreo.fiznote.utilits.showToastShort
import kotlinx.android.synthetic.main.activity_edit.*

class EditActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityEditBinding
    private lateinit var mToolbar: Toolbar
    private var imageRequestCode = 10

    private var tempImageUri = "empty"

    private val mDatabaseManager = DatabaseManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    override fun onStart() {
        super.onStart()
        initFields()

        add_photo.setOnClickListener { addPhoto() }
        cancel_galery_btn.setOnClickListener { cancelBtn() }
        background_bottom_black.setOnClickListener { cancelBtn() }
        photo_btn_galery.setOnClickListener { galeryAddPhoto() }
    }

    override fun onResume() {
        super.onResume()
        mDatabaseManager.openDb()
    }

    override fun onDestroy() {
        super.onDestroy()
        mDatabaseManager.closeDb()
    }

    private fun initFields() {
        mToolbar = mBinding.toolbarEditNotes
        mToolbar.setNavigationIcon(R.drawable.ic_baseline_check_24)
        mToolbar.setNavigationOnClickListener {
            val title = edit_title_notes.text.toString()
            val desc = edit_description_notes.text.toString()
            if (title.isNotEmpty() && desc.isNotEmpty()) {
                mDatabaseManager.insertToDb(title, desc, tempImageUri)
                this.finish()
            } else showToastShort("Заголовок и описание не должны быть пустыми")
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK && requestCode == imageRequestCode) {
            //content_img_1.setImageURI(data?.data)
            tempImageUri = data?.data.toString()
        }
    }

    private fun galeryAddPhoto() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, imageRequestCode)
    }

    private fun cancelBtn() {
        background_bottom_black.visibility = View.GONE
        photo_add_note.visibility = View.GONE
    }

    private fun addPhoto() {
        background_bottom_black.visibility = View.VISIBLE
        photo_add_note.visibility = View.VISIBLE
    }
}