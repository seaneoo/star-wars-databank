package edu.regis.soconnor005.starwarsdatabank.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import edu.regis.soconnor005.starwarsdatabank.data.EntryCategory
import edu.regis.soconnor005.starwarsdatabank.databinding.FragmentAddBinding

class AddFragment : Fragment() {
    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categories = EntryCategory.entries.map { it.name }
        val arrayAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, categories)
        binding.category?.setText(categories[0])
        binding.category?.setAdapter(arrayAdapter)

        // Add logic to Back button
        binding.buttonBack.setOnClickListener {
            findNavController().navigate(AddFragmentDirections.actionAddFragmentToListFragment())
        }

        // Add logic to Add button
        // TODO: Actually add the item!
        binding.buttonAddItem.setOnClickListener {
//            val entryCategory = EntryCategory.valueOf(binding.dropdownCategory?.text.toString())
        }
    }
}
