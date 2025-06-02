package edu.regis.soconnor005.starwarsdatabank.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.InputMismatchException
import java.util.UUID

class DatabankViewModel : ViewModel() {
    private val _entries = MutableLiveData<MutableList<Entry>>(mutableListOf())

    // Read-only access to live data
    val entries: LiveData<MutableList<Entry>>
        get() {
            val e = _entries
            Log.d(javaClass.simpleName, "Entries: ${e.value?.map { it.id }}")
            return e
        }

    @Suppress("SpellCheckingInspection")
    fun initEntries() {
        _entries.value = mutableListOf(
            Entry(
                category = EntryCategory.Character,
                name = "Anakin Skywalker",
                description = "Discovered as a slave on Tatooine by Qui-Gon Jinn and Obi-Wan Kenobi, Anakin Skywalker had the potential to become one of the most powerful Jedi ever."
            ), Entry(
                category = EntryCategory.Character,
                name = "Padmé Amidala",
                description = "Padmé Amidala was a courageous, hopeful leader, serving as Queen and then Senator of Naboo -- and was also handy with a blaster."
            ), Entry(
                category = EntryCategory.Planet,
                name = "Naboo",
                description = "An idyllic world close to the border of the Outer Rim Territories, Naboo is inhabited by peaceful humans known as the Naboo, and an indigenous species of intelligent amphibians called the Gungans."
            ), Entry(
                category = EntryCategory.Vehicle,
                name = "Naboo N-1 Starfighter",
                description = "Protecting the skies and space around Naboo is the N-1 starfighter. Its sleek design exemplifies the philosophy of art and function witnessed throughout Naboo technology."
            )
        )
    }

    fun addEntry(entry: Entry) {
        val currentEntries = _entries.value ?: mutableListOf()
        currentEntries.add(entry)
        _entries.value = currentEntries
        Log.d(javaClass.simpleName, "Adding entry: ${entry.id}")
    }

    fun updateEntry(previousId: UUID, entry: Entry): Entry {
        if (previousId != entry.id) throw InputMismatchException("Previous entry ID and new entry ID do not match")
        val currentEntries = _entries.value ?: mutableListOf()

        val index = currentEntries.indexOfFirst { it.id == previousId }
        if (index == -1) throw NoSuchElementException("Could not find entry by ID")

        currentEntries[index] = entry
        _entries.value = currentEntries
        Log.d(javaClass.simpleName, "Updating entry: $previousId")

        return entry
    }

    fun removeEntry(id: UUID) {
        val currentEntries = _entries.value ?: mutableListOf()
        currentEntries.removeIf { it.id == id }
        _entries.value = currentEntries
        Log.d(javaClass.simpleName, "Removing entry: $id")
    }
}
