package edu.regis.soconnor005.starwarsdatabank

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import androidx.lifecycle.lifecycleScope
import edu.regis.soconnor005.starwarsdatabank.auth.AuthService
import edu.regis.soconnor005.starwarsdatabank.databinding.ActivityLoginBinding
import edu.regis.soconnor005.starwarsdatabank.fragment.dialog.ErrorDialogFragment
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        _binding = ActivityLoginBinding.inflate(layoutInflater)
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

        /**
         * Add functionality to back button.
         */
        binding.buttonBack.setOnClickListener {
            val welcomeActivity = Intent(this, WelcomeActivity::class.java)
            startActivity(welcomeActivity)
        }

        /**
         * Add functionality to the login button.
         * Attempts to sign into the Firebase account.
         */
        binding.buttonLogin.setOnClickListener {
            lifecycleScope.launch {
                try {
                    AuthService.signIn(
                        binding.email.text.toString(), binding.password.text.toString()
                    )
                    val landingActivity = Intent(this@LoginActivity, LandingActivity::class.java)
                    startActivity(landingActivity)
                    finish()
                } catch (e: Exception) {
                    ErrorDialogFragment(e.localizedMessage ?: "Unknown error").show(
                        supportFragmentManager, "LOGIN_ERROR_DIALOG"
                    )
                }
            }
        }

        /**
         * Add functionality to the Forgot Password button.
         */
        binding.buttonForgotPassword.setOnClickListener {
            lifecycleScope.launch {
                try {
                    AuthService.forgotPassword(binding.email.text.toString())
                    ErrorDialogFragment("Password reset email sent").show(
                        supportFragmentManager, "PASSWORD_RESET_DIALOG"
                    )
                } catch (e: Exception) {
                    ErrorDialogFragment(e.localizedMessage ?: "Unknown error").show(
                        supportFragmentManager, "LOGIN_ERROR_DIALOG"
                    )
                }
            }
        }
    }
}
