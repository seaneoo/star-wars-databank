package edu.regis.soconnor005.starwarsdatabank.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import edu.regis.soconnor005.starwarsdatabank.R
import edu.regis.soconnor005.starwarsdatabank.data.Entry

class ListFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        val itemList = view.findViewById<ListView>(R.id.item_list)
        val items = arrayOf(
            Entry(
                "Character",
                "Anakin Skywalker",
                "Discovered as a slave on Tatooine by Qui-Gon Jinn and Obi-Wan Kenobi, Anakin Skywalker had the potential to become one of the most powerful Jedi ever."
            ), Entry(
                "Character",
                "Padmé Amidala",
                "Padmé Amidala was a courageous, hopeful leader, serving as Queen and then Senator of Naboo -- and was also handy with a blaster."
            ), Entry(
                "Character",
                "Luke Skywalker",
                "Luke Skywalker was a Tatooine farmboy who rose from humble beginnings to become one of the greatest Jedi the galaxy has ever known"
            ), Entry(
                "Character",
                "Leia Organa",
                "Princess Leia Organa was one of the greatest leaders of the Rebel Alliance, fearless on the battlefield and dedicated to ending the Empire’s tyranny."
            ), Entry(
                "Planet",
                "Tatooine",
                "Tatooine is harsh desert world orbiting twin suns in the galaxy’s Outer Rim."
            ), Entry(
                "Planet",
                "Naboo",
                "An idyllic world close to the border of the Outer Rim Territories, Naboo is inhabited by peaceful humans known as the Naboo, and an indigenous species of intelligent amphibians called the Gungans."
            ), Entry(
                "Planet",
                "Alderaan",
                "If ever one needed an example of the irredeemable evil of the Empire, look no further than the shattered remains of Alderaan."
            )
        )
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.list_item, items.map { it.name })

        val navController = findNavController()

        itemList.adapter = arrayAdapter
        itemList.setOnItemClickListener { parent, view, position, id ->
            val item = items[position]
            val action = ListFragmentDirections.actionListFragmentToDetailFragment(item)
            navController.navigate(action)
        }

        view.findViewById<Button>(R.id.button_add_item).setOnClickListener {
            navController.navigate(ListFragmentDirections.actionListFragmentToAddFragment())
        }

        return view
    }
}
