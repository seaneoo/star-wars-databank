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
import com.google.android.material.snackbar.Snackbar
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

        // Add logic to Back button
        binding.buttonBack.setOnClickListener {
            findNavController().navigate(
                EditFragmentDirections.actionEditFragmentToDetailFragment(
                    args.id
                )
            )
        }

        viewModel.entries.observe(viewLifecycleOwner) { entries ->
            val entry = entries.firstOrNull { it.id == args.id }

            if (entry == null) {
                findNavController().navigate(EditFragmentDirections.actionEditFragmentToListFragment())
                return@observe
            }

            binding.textViewEditItem.text = getString(R.string.edit_item, entry.name)
            binding.name.text = Editable.Factory.getInstance().newEditable(entry.name)
            binding.description.text = Editable.Factory.getInstance().newEditable(entry.description)

            // Add logic to Save button
            binding.buttonSaveItem.setOnClickListener {
                showSnackbar {
                    viewModel.updateEntry(
                        args.id, buildNewEntry(entry, binding.name, binding.description)
                    )
                    findNavController().navigate(
                        EditFragmentDirections.actionEditFragmentToDetailFragment(
                            args.id
                        )
                    )
                }
            }
        }
    }

    private fun buildNewEntry(
        oldEntry: Entry,
        nameInput: EditText,
        nameDescription: EditText,
    ): Entry {
        val name = if (nameInput.text.toString().isNotBlank()) {
            nameInput.text.toString().trim()
        } else oldEntry.name

        val description = if (nameDescription.text.toString().isNotBlank()) {
            nameDescription.text.toString().trim()
        } else oldEntry.description

        return oldEntry.copy(name = name, description = description)
    }

    private fun showSnackbar(callback: () -> Unit) {
        val snackbar = Snackbar.make(
            binding.root, getString(R.string.save_these_changes), Snackbar.LENGTH_SHORT
        )
        snackbar.setAction(getString(R.string.yes)) {
            callback()
        }
        snackbar.show()
    }
}
