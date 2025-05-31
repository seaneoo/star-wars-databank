package edu.regis.soconnor005.starwarsdatabank.fragment

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import edu.regis.soconnor005.starwarsdatabank.R
import edu.regis.soconnor005.starwarsdatabank.data.DatabankViewModel
import edu.regis.soconnor005.starwarsdatabank.data.Entry
import edu.regis.soconnor005.starwarsdatabank.databinding.FragmentEditBinding

class EditFragment : Fragment() {
    private var _binding: FragmentEditBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<EditFragmentArgs>()

    private val viewModel by activityViewModels<DatabankViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val entry = args.entry

        // Add logic to Back button
        binding.buttonBack.setOnClickListener {
            findNavController().navigate(
                EditFragmentDirections.actionEditFragmentToDetailFragment(
                    entry
                )
            )
        }

        binding.textViewEditItem.text = getString(R.string.edit_item, entry.name)
        binding.name?.text = Editable.Factory.getInstance().newEditable(entry.name)
        binding.description?.text = Editable.Factory.getInstance().newEditable(entry.description)

        // Add logic to Save button
        binding.buttonSaveItem.setOnClickListener {
            val newEntry = updateEntry(buildNewEntry(entry, binding.name, binding.description))
            findNavController().navigate(
                EditFragmentDirections.actionEditFragmentToDetailFragment(
                    newEntry
                )
            )
        }
    }

    private fun buildNewEntry(oldEntry: Entry, name: EditText?, description: EditText?): Entry {
        val newName = if (name != null && !name.text.toString().isBlank()) {
            name.text.toString().trim()
        } else {
            oldEntry.name
        }
        val newDescription = if (description != null && !description.text.toString().isBlank()) {
            description.text.toString().trim()
        } else {
            oldEntry.description
        }
        val entry = oldEntry.copy(name = newName, description = newDescription)
        return entry
    }

    private fun updateEntry(entry: Entry): Entry {
        return viewModel.updateEntry(entry.id, entry)
    }
}
