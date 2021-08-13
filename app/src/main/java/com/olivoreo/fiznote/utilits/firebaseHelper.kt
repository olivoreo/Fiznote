package com.olivoreo.fiznote.utilits

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

lateinit var AUTH:FirebaseAuth
lateinit var REF_DATABASE_ROOT:DatabaseReference

const val NODE_USERS = "users"
const val NODE_NOTES = "notes"
const val CHILD_ID = "id"
const val CHILD_EMAIL = "email"
const val CHILD_NAME = "username"
const val TITLE = "title"
const val CONTENT = "content"

fun initFirebase() {
    AUTH = FirebaseAuth.getInstance()
    REF_DATABASE_ROOT = FirebaseDatabase.getInstance().reference
}