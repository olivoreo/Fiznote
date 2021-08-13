package com.olivoreo.fiznote.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.fragment.app.FragmentActivity

class DatabaseManager(context: Context) {
    private val mDatabaseHelper = DatabaseHelper(context)
    private lateinit var db:SQLiteDatabase

    fun openDb(){
        db = mDatabaseHelper.writableDatabase
    }

    fun insertToDb(title:String, content:String, uri:String){
        val values = ContentValues().apply {
            put(SQLTable.COLUMN_NAME_TITLE, title)
            put(SQLTable.COLUMN_NAME_CONTENT, content)
            put(SQLTable.COLUMN_NAME_IMAGE_URI, uri)
        }
        db.insert(SQLTable.TABLE_NAME, null, values)
    }

    fun readDb() : ArrayList<String>{
        val dataList = ArrayList<String>()
        val cursor = db.query(
            SQLTable.TABLE_NAME,null,null,
            null,null,null,null)

            while (cursor.moveToNext()){
                val dataText = cursor.getString(cursor.getColumnIndex(SQLTable.COLUMN_NAME_TITLE))
                dataList.add(dataText.toString())
            }
        cursor.close()

        return dataList
    }

    fun closeDb(){
        mDatabaseHelper.close()
    }
}