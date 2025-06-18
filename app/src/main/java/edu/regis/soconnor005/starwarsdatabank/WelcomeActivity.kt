package edu.regis.soconnor005.starwarsdatabank

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnticipateInterpolator
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import edu.regis.soconnor005.starwarsdatabank.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {
    private var _binding: ActivityWelcomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        /**
         * Install splash screen and set up animation(s)
         */
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

        _binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /**
         * Modified the default insets code
         * https://developer.android.com/develop/ui/views/layout/edge-to-edge
         */
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars =
                insets.getInsets(WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.displayCutout())
            v.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                topMargin = systemBars.top
                rightMargin = systemBars.right
                bottomMargin = systemBars.bottom
                leftMargin = systemBars.left
            }
            WindowInsetsCompat.CONSUMED
        }

        binding.textViewVersion.text = getString(R.string.version, BuildConfig.VERSION_NAME)
        binding.textViewAuthor.text = getString(R.string.author, "Sean O'Connor")

        binding.buttonLogin.setOnClickListener {
            val loginActivity = Intent(this, LoginActivity::class.java)
            startActivity(loginActivity)
        }

        binding.buttonRegister.setOnClickListener {
            val registerActivity = Intent(this, RegisterActivity::class.java)
            startActivity(registerActivity)
        }
    }
}
