package edu.regis.soconnor005.starwarsdatabank.data

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import edu.regis.soconnor005.starwarsdatabank.R

enum class EntryCategory(val drawableResource: Int) {
    Character(R.drawable.character), Planet(R.drawable.planet), Vehicle(R.drawable.vehicle);

    fun getDrawable(context: Context): Drawable? =
        ContextCompat.getDrawable(context, drawableResource)
}
