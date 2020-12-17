package com.example.projectakhir

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
    val splashTimeOut = 4000
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FirebaseApp.initializeApp(this)

        Handler().postDelayed({
            val LoginIntent = Intent(this, LoginActivity::class.java)
            startActivity(LoginIntent)
            finish()
        }, splashTimeOut.toLong())
    }
}