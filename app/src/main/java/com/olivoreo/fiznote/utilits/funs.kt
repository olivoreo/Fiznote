package com.olivoreo.fiznote.utilits

import android.app.ProgressDialog
import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.olivoreo.fiznote.R
import com.olivoreo.fiznote.activities.EnterActivity
import com.olivoreo.fiznote.ui.fragments.NotesFragment

fun AppCompatActivity.replaceActivity(activity: AppCompatActivity){
    val intent = Intent(this, activity::class.java)
    startActivity(intent)
    this.finish()
}

fun AppCompatActivity.addActivity(activity: AppCompatActivity){
    val intent = Intent(this, activity::class.java)
    startActivity(intent)
}

fun AppCompatActivity.replaceFragment(fragment: Fragment, addStack:Boolean = true){
    if (addStack){
        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.dataContainer, fragment)
            .commit()
    } else{
        supportFragmentManager.beginTransaction()
            .replace(R.id.dataContainer, fragment)
            .commit()
    }

}

fun Fragment.replaceFragment(fragment: Fragment) {
    this.fragmentManager?.beginTransaction()
        ?.addToBackStack(null)
        ?.replace(R.id.dataContainer, fragment)
        ?.commit()
}

fun Fragment.showToastShort(text: String){
    Toast.makeText(activity,text,Toast.LENGTH_SHORT).show()
}

fun Fragment.showToastLong(text: String){
    Toast.makeText(activity,text,Toast.LENGTH_LONG).show()
}

fun AppCompatActivity.showToastShort(text: String){
    Toast.makeText(this,text,Toast.LENGTH_SHORT).show()
}

fun AppCompatActivity.showToastLong(text: String){
    Toast.makeText(this,text,Toast.LENGTH_LONG).show()
}