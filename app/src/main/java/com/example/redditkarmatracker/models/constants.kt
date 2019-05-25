package com.example.redditkarmatracker.models

import android.app.Activity
import android.content.Context
import com.example.redditkarmatracker.App

const val PREFS = "login_prefs"
const val LAST_LOGGED_IN = "lastLoggedInUser"

fun Activity.saveLastLoggedInUser() {
    this.getSharedPreferences(PREFS, 0)!!.edit().putString(
        LAST_LOGGED_IN,
        App().getAccountHelper().reddit.me().username
    ).apply()
}

fun Context.getLastLoggedInUser(): String =
    this.getSharedPreferences(PREFS, 0)!!.getString(LAST_LOGGED_IN, "") ?: ""
