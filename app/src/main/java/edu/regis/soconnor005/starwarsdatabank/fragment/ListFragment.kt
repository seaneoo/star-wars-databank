package edu.regis.soconnor005.starwarsdatabank.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import edu.regis.soconnor005.starwarsdatabank.R
import edu.regis.soconnor005.starwarsdatabank.data.DatabankViewModel

class ListFragment : Fragment() {
    private val databankViewModel by activityViewModels<DatabankViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        val itemList = view.findViewById<ListView>(R.id.item_list)
        val arrayAdapter = ArrayAdapter(
            requireContext(), R.layout.list_item, databankViewModel.entries.map { it.name })

        val navController = findNavController()

        itemList.adapter = arrayAdapter
        itemList.setOnItemClickListener { _, _, position, _ ->
            val item = databankViewModel.entries[position]
            val action = ListFragmentDirections.actionListFragmentToDetailFragment(item)
            navController.navigate(action)
        }

        view.findViewById<Button>(R.id.button_add_item).setOnClickListener {
            navController.navigate(ListFragmentDirections.actionListFragmentToAddFragment())
        }

        return view
    }
}
