package kr.com.ticketpass.util

import android.content.Context
import android.content.SharedPreferences
import kr.com.ticketpass.TicketPassApplication

object SharedPreferenceManager {
    private lateinit var sharedPreferences: SharedPreferences
    private val pref : SharedPreferences by lazy { init(TicketPassApplication.appContext!!) }
    private val editor = pref.edit()
    private fun init(context: Context): SharedPreferences {
        return context.getSharedPreferences(ConstValue.CONST_PREFERENCE_NAME, Context.MODE_PRIVATE)
    }

    fun setToken(value : String) {
        pref.edit()
            .putString(ConstValue.CONST_ACCESS_TOKEN, value)
            .commit()
    }


    fun getToken() : String {
        return pref.getString(ConstValue.CONST_ACCESS_TOKEN, "") ?: ""
    }

    fun setPref(key : String, value : String) {
        pref.edit()
            .putString(key, value)
            .commit()
    }

    fun getStringPref(key : String) : String {
        return pref.getString(key, "") ?: ""
    }

    fun setPref(key : String, value : Int) {
        pref.edit()
            .putInt(key, value)
            .commit()
    }

    fun getIntPref(key : String) : Int {
        return pref.getInt(key, 0)
    }

    fun setPref(key : String, value : Boolean) {
        pref.edit()
            .putBoolean(key, value)
            .commit()
    }

    fun getBooleanPref(key : String) : Boolean {
        return pref.getBoolean(key, false)
    }

    fun setPref(key : String, value : Long) {
        pref.edit()
            .putLong(key, value)
            .commit()
    }
    fun removePrefsValue(key: String?) {
        if (pref.contains(key)) {
            editor.remove(key).apply()
        }
    }

    fun getLongPref(key : String) : Long {
        return pref.getLong(key, 0)
    }
}

