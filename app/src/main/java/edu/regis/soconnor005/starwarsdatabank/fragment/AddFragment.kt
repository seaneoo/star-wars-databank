package edu.regis.soconnor005.starwarsdatabank.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import edu.regis.soconnor005.starwarsdatabank.R
import edu.regis.soconnor005.starwarsdatabank.data.EntryCategory

class AddFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        view.findViewById<ImageButton>(R.id.button_back).setOnClickListener {
            findNavController().navigate(AddFragmentDirections.actionAddFragmentToListFragment())
        }

        val autoComplete = view.findViewById<AutoCompleteTextView>(R.id.dropdown_category)
        val categories = EntryCategory.entries.map { it.name }
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.list_item, categories)
        autoComplete.setText(categories[0])
        autoComplete.setAdapter(arrayAdapter)

        view.findViewById<Button>(R.id.button_add_item).setOnClickListener {
//            findNavController().navigate(AddFragmentDirections.actionAddFragmentToListFragment())
            val entryCategory = EntryCategory.valueOf(autoComplete.text.toString())
            // TODO actually add the item
        }

        return view
    }
}
