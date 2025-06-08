package edu.regis.soconnor005.starwarsdatabank.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import edu.regis.soconnor005.starwarsdatabank.R
import edu.regis.soconnor005.starwarsdatabank.data.DatabankViewModel
import edu.regis.soconnor005.starwarsdatabank.data.Entry
import edu.regis.soconnor005.starwarsdatabank.data.EntryCategory
import edu.regis.soconnor005.starwarsdatabank.databinding.FragmentAddBinding

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
        Log.d(javaClass.simpleName, "Categories: $categories")
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.simple_list_item, categories)
        Log.d(javaClass.simpleName, "ArrayAdapter: $arrayAdapter")
        binding.category.setText(categories[0])
        binding.category.setAdapter(arrayAdapter)

        // Add logic to Add button
        binding.buttonAddItem.setOnClickListener {
            if (addEntry(buildNewEntry(binding.category, binding.name, binding.description))) {
                findNavController().navigate(
                    AddFragmentDirections.actionAddFragmentToListFragment()
                )
            }
        }
    }

    private fun buildNewEntry(
        categoryInput: AutoCompleteTextView?,
        nameInput: EditText?,
        descriptionInput: EditText?,
    ): Entry? {
        var error = false

        val category = if (categoryInput != null && !categoryInput.text.toString().isBlank()) {
            EntryCategory.valueOf(categoryInput.text.toString())
        } else {
            error = true
            displayError(getString(R.string.category_required))
            null
        }
        val name = if (nameInput != null && !nameInput.text.toString().isBlank()) {
            nameInput.text.toString().trim()
        } else {
            error = true
            displayError(getString(R.string.name_required))
            null
        }
        val description =
            if (descriptionInput != null && !descriptionInput.text.toString().isBlank()) {
                descriptionInput.text.toString().trim()
            } else {
                error = true
                displayError(getString(R.string.description_required))
                null
            }

        return if (!error) {
            Entry(category = category!!, name = name!!, description = description!!)
        } else null
    }

    private fun displayError(message: String) {
        binding.errorMessage?.let {
            it.visibility = View.VISIBLE
            it.text = getString(R.string.error, message)
        }
    }

    private fun addEntry(entry: Entry?): Boolean {
        return if (entry != null) {
            viewModel.addEntry(entry)
            true
        } else false
    }
}
