package edu.regis.soconnor005.starwarsdatabank.data

import java.io.Serializable
import java.util.UUID

data class Entry(
    val id: UUID = UUID.randomUUID(),
    val category: EntryCategory,
    val name: String,
    val description: String,
) : Serializable
