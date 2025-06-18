package edu.regis.soconnor005.starwarsdatabank

import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import androidx.lifecycle.ViewModelProvider
import edu.regis.soconnor005.starwarsdatabank.data.DatabankViewModel
import edu.regis.soconnor005.starwarsdatabank.data.DatabankViewModelFactory
import edu.regis.soconnor005.starwarsdatabank.data.PREFS_NAME
import edu.regis.soconnor005.starwarsdatabank.database.DatabankDatabase
import edu.regis.soconnor005.starwarsdatabank.databinding.ActivityLandingBinding

class LandingActivity : AppCompatActivity() {
    private var _binding: ActivityLandingBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        _binding = ActivityLandingBinding.inflate(layoutInflater)
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

        val preferences = applicationContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val entryDao = DatabankDatabase.getInstance(application).entryDao
        val viewModelFactory = DatabankViewModelFactory(preferences, entryDao)
        ViewModelProvider(this, viewModelFactory)[DatabankViewModel::class]
    }
}
