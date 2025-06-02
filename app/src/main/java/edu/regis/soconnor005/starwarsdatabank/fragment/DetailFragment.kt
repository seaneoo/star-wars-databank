package edu.regis.soconnor005.starwarsdatabank.fragment

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import edu.regis.soconnor005.starwarsdatabank.R
import edu.regis.soconnor005.starwarsdatabank.data.DatabankViewModel
import edu.regis.soconnor005.starwarsdatabank.data.Entry
import edu.regis.soconnor005.starwarsdatabank.data.getCategoryDrawableId
import edu.regis.soconnor005.starwarsdatabank.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<DetailFragmentArgs>()
    private val viewModel by activityViewModels<DatabankViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Add logic to Back button
        binding.buttonBack.setOnClickListener {
            findNavController().navigate(
                DetailFragmentDirections.actionDetailFragmentToListFragment()
            )
        }

        viewModel.entries.observe(viewLifecycleOwner) { entries ->
            val entry = entries.firstOrNull { it.id == args.id }

            if (entry == null) {
                findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToListFragment())
                return@observe
            }

            binding.itemCategoryIcon.setImageResource(entry.getCategoryDrawableId())
            binding.itemCategory.text = entry.category.name
            binding.itemName.text = entry.name
            binding.itemDescription.text = entry.description

            // Add logic to Edit button
            binding.buttonEdit.contentDescription = getString(R.string.edit_item)
            binding.buttonEdit.setOnClickListener {
                populateEditFields(entry)
                toggleEditPanel()
            }

            // Add logic to Delete button
            binding.buttonDelete.contentDescription = getString(R.string.delete_item, entry)
            binding.buttonDelete.setOnClickListener {
                viewModel.removeEntry(entry.id)
            }

            // Add logic to edit close button
            binding.editButtonClose.setOnClickListener {
                toggleEditPanel()
            }

            // Add logic to edit save button
            binding.editButtonSave.setOnClickListener {
                showSnackbar {
                    viewModel.updateEntry(
                        args.id, buildNewEntry(entry, binding.editName, binding.editDescription)
                    )
                    toggleEditPanel()
                }
            }
        }
    }

    private fun populateEditFields(entry: Entry) {
        binding.editName.text = Editable.Factory.getInstance().newEditable(entry.name)
        binding.editDescription.text = Editable.Factory.getInstance().newEditable(entry.description)
    }

    private fun toggleEditPanel() {
        binding.editLayout.visibility = if (binding.editLayout.isGone) VISIBLE else GONE
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
}
