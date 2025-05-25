package edu.regis.soconnor005.starwarsdatabank

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_register)

        /**
         * Modified the default insets code
         * https://developer.android.com/develop/ui/views/layout/edge-to-edge
         */
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.layout_register)) { v, insets ->
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
        findViewById<ImageButton>(R.id.button_back).setOnClickListener {
            val welcomeActivity = Intent(this, WelcomeActivity::class.java)
            startActivity(welcomeActivity)
        }

        /**
         * For now we just let the user go to the landing screen
         * without registering.
         */
        findViewById<Button>(R.id.button_register).setOnClickListener {
            val landingActivity = Intent(this, LandingActivity::class.java)
            startActivity(landingActivity)
        }
    }
}
