package com.olivoreo.fiznote.ui.objects

import android.graphics.Typeface
import android.os.Build
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.mikepenz.materialdrawer.AccountHeader
import com.mikepenz.materialdrawer.AccountHeaderBuilder
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.DividerDrawerItem
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.ProfileDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem
import com.olivoreo.fiznote.R
import com.olivoreo.fiznote.ui.fragments.ClockFragment
import com.olivoreo.fiznote.ui.fragments.NotesFragment
import com.olivoreo.fiznote.ui.fragments.SettingsFragment
import com.olivoreo.fiznote.utilits.USER
import com.olivoreo.fiznote.utilits.replaceFragment

class AppDrawer(val mainActivity: AppCompatActivity, val toolbar: Toolbar) {

    private lateinit var mDrawer: Drawer
    private lateinit var mHeader: AccountHeader
    private lateinit var typefacer: Typeface
    private lateinit var typefaceb: Typeface
    private lateinit var mDrawerLayout: DrawerLayout

    var isDrawerOpen = true

    fun create() {
        createFont()
        createHeader()
        createDrawer()
        mDrawerLayout = mDrawer.drawerLayout
    }

    fun disableDrawer() {
        mDrawer.actionBarDrawerToggle?.isDrawerIndicatorEnabled = false
        mainActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        toolbar.setNavigationOnClickListener {
            mainActivity.supportFragmentManager.popBackStack()
        }
    }

    fun enableDrawer() {
        mainActivity.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        mDrawer.actionBarDrawerToggle?.isDrawerIndicatorEnabled = true
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
        toolbar.setNavigationOnClickListener {
            mDrawer.openDrawer()
        }
    }

    fun closeDrawer() {
        if (mDrawer.isDrawerOpen) {
            mDrawer.closeDrawer()
            isDrawerOpen = true
        } else isDrawerOpen = false
    }

    private fun createDrawer() {
        mDrawer = DrawerBuilder()
            .withActivity(mainActivity)
            .withToolbar(toolbar)
            .withActionBarDrawerToggle(true)
            .withSelectedItem(-1)
            .withSliderBackgroundDrawableRes(R.drawable.slider_background)
            .withAccountHeader(mHeader)
            .addDrawerItems(
                PrimaryDrawerItem().withIdentifier(100)
                    .withIconTintingEnabled(true)
                    .withName("Заметки")
                    .withTypeface(typefacer)
                    .withSelectable(true)
                    .withIcon(R.drawable.note),

                PrimaryDrawerItem().withIdentifier(101)
                    .withIconTintingEnabled(true)
                    .withName("Напоминания")
                    .withTypeface(typefacer)
                    .withSelectable(true)
                    .withIcon(R.drawable.clock),

                PrimaryDrawerItem().withIdentifier(102)
                    .withIconTintingEnabled(true)
                    .withName("Корзина")
                    .withTypeface(typefacer)
                    .withSelectable(true)
                    .withIcon(R.drawable.trash),

                PrimaryDrawerItem().withIdentifier(103)
                    .withIconTintingEnabled(true)
                    .withName("Списки")
                    .withTypeface(typefacer)
                    .withSelectable(true)
                    .withIcon(R.drawable.list),

                DividerDrawerItem(),

                PrimaryDrawerItem().withIdentifier(104)
                    .withIconTintingEnabled(true)
                    .withName("Тема")
                    .withTypeface(typefacer)
                    .withSelectable(false)
                    .withIcon(R.drawable.theme),

                PrimaryDrawerItem().withIdentifier(105)
                    .withIconTintingEnabled(true)
                    .withName("Поделиться приложением")
                    .withTypeface(typefacer)
                    .withSelectable(false)
                    .withIcon(R.drawable.share),

                PrimaryDrawerItem().withIdentifier(106)
                    .withIconTintingEnabled(true)
                    .withName("Настройки")
                    .withTypeface(typefacer)
                    .withSelectable(false)
                    .withIcon(R.drawable.settings),
            ).withOnDrawerItemClickListener(object : Drawer.OnDrawerItemClickListener {
                override fun onItemClick(
                    view: View?,
                    position: Int,
                    drawerItem: IDrawerItem<*>
                ): Boolean {
                    when (position) {
                        1 -> mainActivity.replaceFragment(NotesFragment())

                        2 -> mainActivity.replaceFragment(ClockFragment())

                        8 -> mainActivity.replaceFragment(SettingsFragment())
                    }
                    return false
                }
            }).build()

    }

    private fun createHeader() {
        mHeader = AccountHeaderBuilder()
            .withActivity(mainActivity)
            .withHeaderBackground(R.drawable.header)
            .withTypeface(typefaceb)
            .withDividerBelowHeader(false)
            .withSelectionListEnabledForSingleProfile(false)
            .addProfiles(
                ProfileDrawerItem().withName(USER.name)
                    .withEmail(USER.email)
                    .withIcon(R.drawable.default_user)
            ).build()
    }

    private fun createFont() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            typefacer = mainActivity.resources.getFont(R.font.century_gothic_regular)
            typefaceb = mainActivity.resources.getFont(R.font.century_gothic_bold)
        }
    }
}