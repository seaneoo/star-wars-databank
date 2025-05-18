package edu.regis.soconnor005.starwarsdatabank

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AnticipateInterpolator
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        splashScreen.setOnExitAnimationListener { view ->
            val slideUp = ObjectAnimator.ofFloat(view, View.SCALE_X, 1f, 0f)
            slideUp.interpolator = AnticipateInterpolator()
            slideUp.duration = 200L
            slideUp.doOnEnd { view.remove() }
            slideUp.start()
        }

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_welcome)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.welcome)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val versionText = getString(R.string.version, "1.0.0")
        findViewById<TextView>(R.id.text_version).text = versionText

        val buttonLogin = findViewById<Button>(R.id.button_login)
        buttonLogin.setOnClickListener {
            val loginActivity = Intent(this, LoginActivity::class.java)
            startActivity(loginActivity)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }

        val buttonRegister = findViewById<Button>(R.id.button_register)
        buttonRegister.setOnClickListener { /* TODO */ }
    }
}
