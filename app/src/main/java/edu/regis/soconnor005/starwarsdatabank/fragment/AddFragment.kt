package edu.regis.soconnor005.starwarsdatabank.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import edu.regis.soconnor005.starwarsdatabank.data.DatabankViewModel
import edu.regis.soconnor005.starwarsdatabank.data.Entry
import edu.regis.soconnor005.starwarsdatabank.data.EntryCategory
import edu.regis.soconnor005.starwarsdatabank.databinding.FragmentAddBinding
import java.util.InputMismatchException

class AddFragment : Fragment() {
    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    private val viewModel by activityViewModels<DatabankViewModel>()

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

        // Add logic to Back button
        binding.buttonBack.setOnClickListener {
            findNavController().navigate(AddFragmentDirections.actionAddFragmentToListFragment())
        }

        // Populate the dropdown with our categories
        val categories = EntryCategory.entries.map { it.name }
        val arrayAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, categories)
        binding.category?.setText(categories[0])
        binding.category?.setAdapter(arrayAdapter)

        // Add logic to Add button
        binding.buttonAddItem.setOnClickListener {
            addEntry(buildNewEntry(binding.category, binding.name, binding.description))
            findNavController().navigate(
                AddFragmentDirections.actionAddFragmentToListFragment()
            )
        }
    }

    private fun buildNewEntry(
        categoryInput: AutoCompleteTextView?,
        nameInput: EditText?,
        descriptionInput: EditText?,
    ): Entry {
        val category = if (categoryInput != null && !categoryInput.text.toString().isBlank()) {
            EntryCategory.valueOf(categoryInput.text.toString())
        } else {
            throw InputMismatchException("category is required")
        }
        val name = if (nameInput != null && !nameInput.text.toString().isBlank()) {
            nameInput.text.toString().trim()
        } else {
            throw InputMismatchException("name is required")
        }
        val description =
            if (descriptionInput != null && !descriptionInput.text.toString().isBlank()) {
                descriptionInput.text.toString().trim()
            } else {
                throw InputMismatchException("description is required")
            }
        val entry = Entry(category = category, name = name, description = description)
        return entry
    }

    private fun addEntry(entry: Entry) {
        viewModel.addEntry(entry)
    }
}
