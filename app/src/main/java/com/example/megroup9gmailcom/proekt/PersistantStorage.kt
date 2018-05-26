package com.example.megroup9gmailcom.proekt

import android.content.Context
import android.content.SharedPreferences

object PersistantStorage {
    val STORAGE_NAME = "StorageName"

    private var settings: SharedPreferences? = null
    private var editor: SharedPreferences.Editor? = null
    private var context: Context? = null

    fun init(cntxt: Context) {
        context = cntxt
    }

    private fun init() {
        settings = context!!.getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE)
        editor = settings!!.edit()
    }

    fun addProperty(name: String, value: String) {
        if (settings == null) {
            init()
        }
        editor!!.putString(name, value)
        editor!!.commit()
    }

    fun getProperty(name: String): String? {
        if (settings == null) {
            init()
        }
        return settings!!.getString(name, null)
    }
}