package com.example.projectakhir

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        var login = findViewById<Button>(R.id.loginButton)
        login.setOnClickListener {
            loginUser()
        }
    }

    fun loginUser() {
        var emailText = findViewById<EditText>(R.id.emailLogin)
        var passText = findViewById<EditText>(R.id.passLogin)
        var email = emailText.text.toString().trim()
        var password = passText.text.toString().trim()

        if (email.isEmpty()) {
            emailText.setError("Email tidak boleh kosong")
            emailText.requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailText.setError("E-Mail tidak valid")
            emailText.requestFocus()
            return
        }
        if (password.isEmpty()) {
            passText.setError("Password tidak boleh kosong")
            passText.requestFocus()
            return
        }
        val homeIntent = Intent(this, HomeActivity::class.java)
        startActivity(homeIntent)
        finish()
    }
}