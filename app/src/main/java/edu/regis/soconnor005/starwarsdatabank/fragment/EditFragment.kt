package edu.regis.soconnor005.starwarsdatabank.fragment

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import edu.regis.soconnor005.starwarsdatabank.R
import edu.regis.soconnor005.starwarsdatabank.databinding.FragmentEditBinding

class EditFragment : Fragment() {
    private var _binding: FragmentEditBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<EditFragmentArgs>()

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
        // TODO: Actually save the item!
        binding.buttonSaveItem.setOnClickListener {
            findNavController().navigate(
                EditFragmentDirections.actionEditFragmentToDetailFragment(
                    entry
                )
            )
        }
    }
}
