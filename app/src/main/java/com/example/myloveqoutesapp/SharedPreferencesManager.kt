package com.example.myloveqoutesapp

import android.content.Context
import android.content.SharedPreferences
import java.lang.Boolean.FALSE

object SharedPreferencesManager {
    private const val PREFS_NAME = "MyPrefsFile" // Change this to your preference file name
    private const val KEY_VALUE = "storedValue"

    private lateinit var sharedPreferences: SharedPreferences

    fun initialize(context: Context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun setValue(value: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean(KEY_VALUE, value)
        editor.apply()
    }

    fun getValue(): Boolean {
        return sharedPreferences.getBoolean(KEY_VALUE, FALSE)
    }
}