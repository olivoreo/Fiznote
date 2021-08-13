package com.olivoreo.fiznote.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context:Context) : SQLiteOpenHelper(context, SQLTable.DATABASE_NAME,
    null, SQLTable.DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {

        db?.execSQL(SQLTable.CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            db?.execSQL(SQLTable.DELETE_TABLE)
            onCreate(db)
    }
}