package com.tubianto.fingerprint

import android.content.Context
import android.content.SharedPreferences

class SessionManager(val context: Context?) {

    // Shared pref mode
    val PRIVATE_MODE = 0

    // Sharedpref file name
    private val PREF_NAME = "Fingerprint"

    var pref: SharedPreferences? = context?.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
    var editor: SharedPreferences.Editor? = pref?.edit()

    private val EXTRAS_STATUS_FINGERPRINT = "status_fingerprint"

    fun setStatusFingerprint(statusView: String) {
        editor?.putString(EXTRAS_STATUS_FINGERPRINT, statusView)
        editor?.commit()
    }

    fun getStatusFingerprint(): String? {
        return pref?.getString(EXTRAS_STATUS_FINGERPRINT, "0")
    }

    fun removeData() {
        editor?.clear()
        editor?.commit()
    }
}