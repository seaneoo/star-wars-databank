package edu.regis.soconnor005.starwarsdatabank

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_splash_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val logoImageView = findViewById<ImageView>(R.id.splash_screen_logo_imageView)
        val logoAnimationDuration = 3000L

        val logoObjectAnimator =
            ObjectAnimator.ofFloat(logoImageView, View.ROTATION, 0f, 360f).apply {
                duration = logoAnimationDuration
                interpolator = AccelerateDecelerateInterpolator()
            }
        logoObjectAnimator.start()
        logoObjectAnimator.doOnEnd {
            val loginActivity = Intent(this, LoginActivity::class.java)
            startActivity(loginActivity)
            @Suppress("DEPRECATION") overridePendingTransition(
                android.R.anim.fade_in, android.R.anim.fade_out
            )
            finish()
        }
    }
}
