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
import edu.regis.soconnor005.starwarsdatabank.databinding.ActivityRegisterBinding
import edu.regis.soconnor005.starwarsdatabank.fragment.dialog.ErrorDialogFragment
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {
    private var _binding: ActivityRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        _binding = ActivityRegisterBinding.inflate(layoutInflater)
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
         * Add functionality to the register button.
         * Attempts to create an account with Firebase.
         */
        binding.buttonRegister.setOnClickListener {
            lifecycleScope.launch {
                try {
                    AuthService.createAccount(
                        binding.email.text.toString(), binding.password.text.toString()
                    )
                    val landingActivity = Intent(this@RegisterActivity, LandingActivity::class.java)
                    startActivity(landingActivity)
                    finish()
                } catch (e: Exception) {
                    ErrorDialogFragment(e.localizedMessage ?: "Unknown error").show(
                        supportFragmentManager, "REGISTER_ERROR_DIALOG"
                    )
                }
            }
        }
    }
}
