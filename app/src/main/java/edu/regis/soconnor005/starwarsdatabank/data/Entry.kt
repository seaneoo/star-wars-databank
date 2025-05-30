package edu.regis.soconnor005.starwarsdatabank.data

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import edu.regis.soconnor005.starwarsdatabank.R
import java.io.Serializable
import java.util.UUID

data class Entry(
    val id: UUID = UUID.randomUUID(),
    val category: EntryCategory,
    val name: String,
    val description: String,
) : Serializable

fun Entry.getCategoryDrawableId(): Int {
    return when (category) {
        EntryCategory.Character -> R.drawable.character
        EntryCategory.Planet -> R.drawable.planet
        EntryCategory.Vehicle -> R.drawable.vehicle
    }
}

fun Entry.getCategoryDrawable(context: Context): Drawable? {
    return ContextCompat.getDrawable(context, getCategoryDrawableId())
}
