package edu.regis.soconnor005.starwarsdatabank.data

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import edu.regis.soconnor005.starwarsdatabank.R

enum class EntryCategory(val stringResource: Int, val drawableResource: Int) {
    Character(
        R.string.character, R.drawable.character
    ),
    Planet(
        R.string.planet, R.drawable.planet
    ),
    Vehicle(
        R.string.vehicle, R.drawable.vehicle
    );

    fun getName(context: Context): String = context.getString(stringResource)

    fun getDrawable(context: Context): Drawable? =
        ContextCompat.getDrawable(context, drawableResource)

    companion object {
        fun fromName(context: Context, name: String): EntryCategory? =
            EntryCategory.entries.firstOrNull { it.getName(context) == name }
    }
}
