package edu.regis.soconnor005.starwarsdatabank

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import edu.regis.soconnor005.starwarsdatabank.databinding.ActivityRegisterBinding

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
         * For now we just let the user go to the landing screen
         * without registering.
         */
        binding.buttonRegister.setOnClickListener {
            val landingActivity = Intent(this, LandingActivity::class.java)
            startActivity(landingActivity)
            finish()
        }
    }
}
