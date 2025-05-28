package edu.regis.soconnor005.starwarsdatabank.data

import androidx.lifecycle.ViewModel

// Is a function to initialize and return the list necessary?
// In Kotlin getters are inferred by accessing the property, in this case "entries".
// And the "entries" list is initialized in the class itself, which is declared once.
class DatabankViewModel : ViewModel() {
    val entries: MutableList<Entry> = mutableListOf(
        Entry(
            0,
            EntryCategory.Character,
            "Anakin Skywalker",
            "Discovered as a slave on Tatooine by Qui-Gon Jinn and Obi-Wan Kenobi, Anakin Skywalker had the potential to become one of the most powerful Jedi ever."
        ), Entry(
            1,
            EntryCategory.Character,
            "Padmé Amidala",
            "Padmé Amidala was a courageous, hopeful leader, serving as Queen and then Senator of Naboo -- and was also handy with a blaster."
        ), Entry(
            2,
            EntryCategory.Character,
            "Luke Skywalker",
            "Luke Skywalker was a Tatooine farmboy who rose from humble beginnings to become one of the greatest Jedi the galaxy has ever known"
        ), Entry(
            3,
            EntryCategory.Character,
            "Leia Organa",
            "Princess Leia Organa was one of the greatest leaders of the Rebel Alliance, fearless on the battlefield and dedicated to ending the Empire’s tyranny."
        ), Entry(
            4,
            EntryCategory.Planet,
            "Tatooine",
            "Tatooine is harsh desert world orbiting twin suns in the galaxy’s Outer Rim."
        ), Entry(
            5,
            EntryCategory.Planet,
            "Naboo",
            "An idyllic world close to the border of the Outer Rim Territories, Naboo is inhabited by peaceful humans known as the Naboo, and an indigenous species of intelligent amphibians called the Gungans."
        ), Entry(
            6,
            EntryCategory.Planet,
            "Alderaan",
            "If ever one needed an example of the irredeemable evil of the Empire, look no further than the shattered remains of Alderaan."
        ), Entry(
            7,
            EntryCategory.Planet,
            "Kashyyyk",
            "Kashyyyk is the Wookiee homeworld, covered in dense forest. While Wookiees build their homes in the planet's trees, they are not a primitive species, and Kashyyyk architecture incorporates sophisticated technology."
        )
    )

    /**
     * Return the index of an item in the [entries] list.
     *
     * @throws NoSuchElementException If the item could not be found.
     */
    private fun entryById(id: Int): Int {
        val index = entries.indexOfFirst { it.id == id }
        if (index == -1) throw NoSuchElementException()
        return index
    }

    fun addEntry(entry: Entry) {
        entries.add(entry)
    }

    fun alterEntry(id: Int, newEntry: Entry) {
        val index = entryById(id)
        entries[index] = newEntry
    }

    fun deleteEntry(id: Int) {
        val index = entryById(id)
        entries.removeAt(index)
    }
}
