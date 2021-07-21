package com.everis.data.preference.manager

import android.content.Context
import android.content.SharedPreferences
import com.everis.data.preference.utils.NAME_SHARE_PREFERENCES

/**
 * Created by WilderCs on 2019-10-22.
 * Copyright (c) 2019 Everis. All rights reserved.
 **/

class PreferencesManager(context: Context) {

    private var sharedPreferences: SharedPreferences = context.getSharedPreferences(
        NAME_SHARE_PREFERENCES, 0
    )

    fun setValue(key: String, value: String) {
        sharedPreferences
            .edit()
            .putString(key, value)
            .apply()
    }

    fun setValue(key: String, value: Int) {
        sharedPreferences
            .edit()
            .putInt(key, value)
            .apply()
    }

    fun setValue(key: String, value: Boolean) {
        sharedPreferences
            .edit()
            .putBoolean(key, value)
            .apply()
    }

    fun setValue(key: String, value: Float) {
        sharedPreferences
            .edit()
            .putFloat(key, value)
            .apply()
    }

    fun setValue(key: String, value: Long) {
        sharedPreferences
            .edit()
            .putLong(key, value)
            .apply()
    }

    fun getString(key: String): String {
        return sharedPreferences.getString(key, String()) ?: String()
    }

    fun getInt(key: String): Int {
        return sharedPreferences.getInt(key, 0)
    }

    fun getBoolean(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    fun getFloat(key: String): Float {
        return sharedPreferences.getFloat(key, 0f)
    }

    fun getLong(key: String): Long {
        return sharedPreferences.getLong(key, 0)
    }
}