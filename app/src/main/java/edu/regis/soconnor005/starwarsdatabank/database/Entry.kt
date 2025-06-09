package edu.regis.soconnor005.starwarsdatabank.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import edu.regis.soconnor005.starwarsdatabank.data.EntryCategory

@Entity
data class Entry(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val category: EntryCategory,
    val name: String,
    val description: String,
)
