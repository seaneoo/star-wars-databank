package edu.regis.soconnor005.starwarsdatabank.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import edu.regis.soconnor005.starwarsdatabank.database.Entry
import edu.regis.soconnor005.starwarsdatabank.database.EntryDao
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DatabankViewModel(
    private val entryDao: EntryDao,
    private val tag: String = "DatabankViewModel",
) : ViewModel() {
    val entries: StateFlow<List<Entry>> =
        entryDao.getAll().stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
    
    private val _currentEntryId = MutableLiveData<Int?>(null)

    val currentEntry: LiveData<Entry?> = _currentEntryId.switchMap { id ->
        if (id == null) {
            MutableLiveData(null)
        } else {
            entryDao.getById(id)
        }
    }

    init {
        initEntries()
    }

    @Suppress("SpellCheckingInspection")
    private fun initEntries() {
        viewModelScope.launch {
            entryDao.deleteAll() // TODO: Temporary, database should be persisted
            entryDao.insertAll(
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
    }

    fun setCurrentItem(id: Int) {
        _currentEntryId.value = id
        Log.d(tag, "Set current item to: $id")
    }

    fun addEntry(entry: Entry) {
        viewModelScope.launch {
            try {
                entryDao.insert(entry)
                Log.d(tag, "Adding entry: ${entry.id}")
            } catch (e: Exception) {
                Log.e(tag, "Could not add entry: ${entry.id}", e)
            }
        }
    }

    fun updateEntry(entry: Entry) {
        viewModelScope.launch {
            try {
                entryDao.update(entry)
                Log.d(tag, "Updating entry: ${entry.id}")
            } catch (e: Exception) {
                Log.e(tag, "Could not update entry: ${entry.id}", e)
            }
        }
    }

    fun removeEntry(entry: Entry) {
        viewModelScope.launch {
            try {
                entryDao.delete(entry)
                Log.d(tag, "Deleting entry: ${entry.id}")
            } catch (e: Exception) {
                Log.e(tag, "Could not delete entry: ${entry.id}", e)
            }
        }
    }
}
