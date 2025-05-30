package edu.regis.soconnor005.starwarsdatabank.data

import android.util.Log
import androidx.lifecycle.ViewModel
import java.util.InputMismatchException
import java.util.UUID

class DatabankViewModel : ViewModel() {
    private var entries = mutableListOf<Entry>()

    @Suppress("SpellCheckingInspection")
    fun initData() {
        entries = mutableListOf(
            Entry(
                category = EntryCategory.Character,
                name = "Anakin Skywalker",
                description = "Discovered as a slave on Tatooine by Qui-Gon Jinn and Obi-Wan Kenobi, Anakin Skywalker had the potential to become one of the most powerful Jedi ever."
            ), Entry(
                category = EntryCategory.Character,
                name = "Padmé Amidala",
                description = "Padmé Amidala was a courageous, hopeful leader, serving as Queen and then Senator of Naboo -- and was also handy with a blaster."
            ), Entry(
                category = EntryCategory.Character,
                name = "Luke Skywalker",
                description = "Luke Skywalker was a Tatooine farmboy who rose from humble beginnings to become one of the greatest Jedi the galaxy has ever known"
            ), Entry(
                category = EntryCategory.Character,
                name = "Leia Organa",
                description = "Princess Leia Organa was one of the greatest leaders of the Rebel Alliance, fearless on the battlefield and dedicated to ending the Empire’s tyranny."
            ), Entry(
                category = EntryCategory.Planet,
                name = "Tatooine",
                description = "Tatooine is harsh desert world orbiting twin suns in the galaxy’s Outer Rim."
            ), Entry(
                category = EntryCategory.Planet,
                name = "Naboo",
                description = "An idyllic world close to the border of the Outer Rim Territories, Naboo is inhabited by peaceful humans known as the Naboo, and an indigenous species of intelligent amphibians called the Gungans."
            ), Entry(
                category = EntryCategory.Planet,
                name = "Alderaan",
                description = "If ever one needed an example of the irredeemable evil of the Empire, look no further than the shattered remains of Alderaan."
            ), Entry(
                category = EntryCategory.Vehicle,
                name = "Naboo N-1 Starfighter",
                description = "Protecting the skies and space around Naboo is the N-1 starfighter. Its sleek design exemplifies the philosophy of art and function witnessed throughout Naboo technology."
            ), Entry(
                category = EntryCategory.Vehicle,
                name = "Speeder Bike",
                description = "Speeder bikes are common sights throughout the galaxy, with manufacturers turning out both civilian and military models."
            ), Entry(
                category = EntryCategory.Character,
                name = "Klaud",
                description = "Klaud might have joined the Resistance as a result of mistaken identity, but he is welcomed as an ally by the freedom fighters."
            ), Entry(
                category = EntryCategory.Character,
                name = "Captain Panaka",
                description = "Queen Amidala's loyal protector during the Trade Federation invasion crisis was Captain Panaka. Panaka was often referred to as \"the quickest eyes on Naboo\" for his attention to detail and selfless dedication to the safety of the Queen."
            )
        )
    }

    fun getEntries(): MutableList<Entry> {
        Log.d(javaClass.simpleName, "in entries: ${entries.map { it.id }}")
        return entries
    }

    fun getEntryById(id: UUID): Int {
        val index = entries.indexOfFirst { it.id == id }
        if (index == -1) throw NoSuchElementException("could not find entry $id")
        Log.d(javaClass.simpleName, "located entry $id at index $index")
        return index
    }

    fun addEntry(entry: Entry) {
        entries.add(entry)
        Log.d(javaClass.simpleName, "adding entry ${entry.id}")
    }

    fun updateEntry(previousEntryId: UUID, newEntry: Entry) {
        if (previousEntryId == newEntry.id) throw InputMismatchException("previous entry id and new entry id do not match")
        val index = getEntryById(previousEntryId)
        entries[index] = newEntry
        Log.d(javaClass.simpleName, "updating entry $previousEntryId")
    }

    fun deleteEntry(id: UUID) {
        val index = getEntryById(id)
        entries.removeAt(index)
        Log.d(javaClass.simpleName, "deleting entry $id")
    }
}
