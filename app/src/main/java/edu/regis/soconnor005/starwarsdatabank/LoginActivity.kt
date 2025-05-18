package edu.regis.soconnor005.starwarsdatabank

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_login)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.login)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val buttonBack = findViewById<ImageView>(R.id.button_back)
        buttonBack.setOnClickListener {
            val welcomeActivity = Intent(this, WelcomeActivity::class.java)
            startActivity(welcomeActivity)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }

        val buttonForgotPassword = findViewById<Button>(R.id.button_forgot_password)
        buttonForgotPassword.setOnClickListener { /* TODO */ }

        val buttonContinue = findViewById<Button>(R.id.button_continue)
        buttonContinue.setOnClickListener { /* TODO */ }
    }
}
