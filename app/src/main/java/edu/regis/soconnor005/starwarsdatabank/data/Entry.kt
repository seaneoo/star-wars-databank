package edu.regis.soconnor005.starwarsdatabank.data

import java.io.Serializable

/**
 * An entry in the databank.
 *
 * @param id The unique identifier for the entry. Use this over the item's index.
 * @param category See [EntryCategory]
 * @param name
 * @param description
 */
data class Entry(
    val id: Int,
    val category: EntryCategory,
    val name: String,
    val description: String,
) : Serializable
