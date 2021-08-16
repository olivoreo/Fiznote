package com.olivoreo.fiznote

import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.olivoreo.fiznote.activities.EnterActivity
import com.olivoreo.fiznote.databinding.ActivityMainBinding
import com.olivoreo.fiznote.models.User
import com.olivoreo.fiznote.ui.fragments.NotesFragment
import com.olivoreo.fiznote.ui.objects.AppDrawer
import com.olivoreo.fiznote.utilits.*


class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var typefacer: Typeface
    private lateinit var typefaceb: Typeface
    private lateinit var mToolbar: Toolbar
    lateinit var mAppDrawer: AppDrawer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    override fun onStart() {
        super.onStart()
        initFields()
        initFunc()
    }

    private fun initFunc() {
        if (AUTH.currentUser != null) {
            setSupportActionBar(mToolbar)
            mAppDrawer.create()
            replaceFragment(NotesFragment(),false)
        } else {
            replaceActivity(EnterActivity())
        }
    }

    private fun initFields() {
        mToolbar = mBinding.mainToolbar
        mAppDrawer = AppDrawer(this,mToolbar)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            typefacer = resources.getFont(R.font.century_gothic_regular)
            typefaceb = resources.getFont(R.font.century_gothic_bold)
        }
        initFirebase()
        initUser()
    }

    private fun initUser() {
        REF_DATABASE_ROOT.child(NODE_USERS).child(UID)
            .addListenerForSingleValueEvent(AppValueEventListener{
                USER = it.getValue(User::class.java) ?:User()
            })
    }

    override fun onBackPressed() {
        if (mAppDrawer.isDrawerOpen) {
            mAppDrawer.closeDrawer()
        } else super.onBackPressed()
    }

}
