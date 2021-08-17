package com.olivoreo.fiznote.ui.fragments

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.olivoreo.fiznote.MainActivity
import com.olivoreo.fiznote.R
import com.olivoreo.fiznote.activities.EditActivity
import com.olivoreo.fiznote.models.Adapter
import com.olivoreo.fiznote.utilits.addActivity
import kotlinx.android.synthetic.main.fragment_notes.*


class NotesFragment : Fragment(R.layout.fragment_notes) {

    //private lateinit var mDatabaseManager:DatabaseManager
    private val mAdapter = Adapter(ArrayList())

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        //mDatabaseManager = DatabaseManager(activity)
    }

    override fun onStart() {
        super.onStart()
        initFields()
        create_note.setOnClickListener { createNote() }
    }

    override fun onResume() {
        super.onResume()
        //mDatabaseManager.openDb()
        fillAdapter()
    }

    override fun onDestroy() {
        super.onDestroy()
        //mDatabaseManager.closeDb()
    }


    private fun createNote() {
        (activity as MainActivity).addActivity(EditActivity())
    }

    private fun initFields() {
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = mAdapter
    }

    private fun fillAdapter() {
        //mAdapter.updateAdapter(mDatabaseManager.readDb())
    }

}