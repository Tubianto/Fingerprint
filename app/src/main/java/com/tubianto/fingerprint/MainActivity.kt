package com.tubianto.fingerprint

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        setupUI()
    }

    private fun init() {
        sessionManager = SessionManager(this)
    }

    private fun setupUI() {
        if (sessionManager.getStatusFingerprint() == "0") {
            val intent = Intent(this, FingerprintActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}