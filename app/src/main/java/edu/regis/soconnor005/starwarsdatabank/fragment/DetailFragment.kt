package edu.regis.soconnor005.starwarsdatabank.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import edu.regis.soconnor005.starwarsdatabank.R
import edu.regis.soconnor005.starwarsdatabank.data.DatabankViewModel
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
                findNavController().navigate(
                    DetailFragmentDirections.actionDetailFragmentToEditFragment(
                        args.id
                    )
                )
            }

            // Add logic to Delete button
            binding.buttonDelete.contentDescription = getString(R.string.delete_item, entry)
            binding.buttonDelete.setOnClickListener {
                viewModel.removeEntry(entry.id)
            }
        }
    }
}
