package com.tubianto.fingerprint

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.hardware.fingerprint.FingerprintManager
import android.os.Build
import android.os.CancellationSignal
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

/**
 * Created by Tubianto on 05/04/2022.
 */
@RequiresApi(Build.VERSION_CODES.M)
class FingerprintHandler(mContext: Context) : FingerprintManager.AuthenticationCallback() {
    private val context: Context
    fun startAuth(manager: FingerprintManager, cryptoObject: FingerprintManager.CryptoObject?) {
        val cancellationSignal = CancellationSignal()
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.USE_FINGERPRINT
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        manager.authenticate(cryptoObject, cancellationSignal, 0, this, null)
    }

    override fun onAuthenticationError(errMsgId: Int, errString: CharSequence) {
        update("Fingerprint Authentication error\n$errString", false)
    }

    override fun onAuthenticationHelp(helpMsgId: Int, helpString: CharSequence) {
        update("Fingerprint Authentication help\n$helpString", false)
    }

    override fun onAuthenticationFailed() {
        update("Fingerprint Authentication failed.", false)
    }

    override fun onAuthenticationSucceeded(result: FingerprintManager.AuthenticationResult?) {
        update("Fingerprint Authentication succeeded.", true)
    }

    fun update(e: String?, success: Boolean) {
        val textView = (context as Activity).findViewById<View>(R.id.errorText) as TextView
        textView.text = e
        if (success) {
            val sessionManager = SessionManager(context)
            sessionManager.setStatusFingerprint("1")

            textView.setTextColor(Color.GREEN)
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
            context.finish()
        }
    }

    // Constructor
    init {
        context = mContext
    }
}