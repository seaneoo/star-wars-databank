package edu.regis.soconnor005.starwarsdatabank.fragment

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.core.view.isGone
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import edu.regis.soconnor005.starwarsdatabank.R
import edu.regis.soconnor005.starwarsdatabank.data.DatabankViewModel
import edu.regis.soconnor005.starwarsdatabank.data.Entry
import edu.regis.soconnor005.starwarsdatabank.data.EntryCategory
import edu.regis.soconnor005.starwarsdatabank.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

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

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        // Add logic to Back button
        binding.buttonBack.setOnClickListener {
            findNavController().navigate(
                DetailFragmentDirections.actionDetailFragmentToListFragment()
            )
        }

        // Add logic to Edit button
        binding.buttonEdit.setOnClickListener {
            populateEditFields(viewModel.currentEntry.value!!)
            toggleEditPanel()
        }

        // Add logic to Delete button
        binding.buttonDelete.setOnClickListener {
            viewModel.removeEntry(viewModel.currentEntry.value!!.id)
            findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToListFragment())
        }

        // Add logic to edit close button
        binding.editButtonClose.setOnClickListener {
            toggleEditPanel()
        }

        // Add logic to edit save button
        binding.editButtonSave.setOnClickListener {
            showSnackbar {
                viewModel.updateEntry(
                    buildNewEntry(
                        viewModel.currentEntry.value!!, binding.editName, binding.editDescription
                    )
                )
                toggleEditPanel()
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

@Suppress("unused")
@BindingAdapter("categoryIcon")
fun setCategoryIcon(view: ImageView, category: EntryCategory) {
    view.setImageResource(category.drawableResource)
}
