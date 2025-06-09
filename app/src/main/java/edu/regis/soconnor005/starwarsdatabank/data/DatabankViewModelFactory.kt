package edu.regis.soconnor005.starwarsdatabank.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import edu.regis.soconnor005.starwarsdatabank.database.EntryDao

class DatabankViewModelFactory(private val entryDao: EntryDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DatabankViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST") return DatabankViewModel(entryDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
