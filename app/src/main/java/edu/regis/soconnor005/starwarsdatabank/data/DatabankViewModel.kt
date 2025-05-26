package edu.regis.soconnor005.starwarsdatabank.data

import androidx.lifecycle.ViewModel

class DatabankViewModel : ViewModel() {
    var entries = emptyList<Entry>()

    init {
        entries = listOf(
            Entry(
                EntryCategory.Character,
                "Anakin Skywalker",
                "Discovered as a slave on Tatooine by Qui-Gon Jinn and Obi-Wan Kenobi, Anakin Skywalker had the potential to become one of the most powerful Jedi ever."
            ), Entry(
                EntryCategory.Character,
                "Padmé Amidala",
                "Padmé Amidala was a courageous, hopeful leader, serving as Queen and then Senator of Naboo -- and was also handy with a blaster."
            ), Entry(
                EntryCategory.Character,
                "Luke Skywalker",
                "Luke Skywalker was a Tatooine farmboy who rose from humble beginnings to become one of the greatest Jedi the galaxy has ever known"
            ), Entry(
                EntryCategory.Character,
                "Leia Organa",
                "Princess Leia Organa was one of the greatest leaders of the Rebel Alliance, fearless on the battlefield and dedicated to ending the Empire’s tyranny."
            ), Entry(
                EntryCategory.Planet,
                "Tatooine",
                "Tatooine is harsh desert world orbiting twin suns in the galaxy’s Outer Rim."
            ), Entry(
                EntryCategory.Planet,
                "Naboo",
                "An idyllic world close to the border of the Outer Rim Territories, Naboo is inhabited by peaceful humans known as the Naboo, and an indigenous species of intelligent amphibians called the Gungans."
            ), Entry(
                EntryCategory.Planet,
                "Alderaan",
                "If ever one needed an example of the irredeemable evil of the Empire, look no further than the shattered remains of Alderaan."
            ), Entry(
                EntryCategory.Planet,
                "Kashyyyk",
                "Kashyyyk is the Wookiee homeworld, covered in dense forest. While Wookiees build their homes in the planet's trees, they are not a primitive species, and Kashyyyk architecture incorporates sophisticated technology."
            )
        )
    }
}
