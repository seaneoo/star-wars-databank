package edu.regis.soconnor005.starwarsdatabank.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import edu.regis.soconnor005.starwarsdatabank.R
import edu.regis.soconnor005.starwarsdatabank.data.DatabankViewModel
import edu.regis.soconnor005.starwarsdatabank.data.Entry
import edu.regis.soconnor005.starwarsdatabank.data.getCategoryDrawable
import edu.regis.soconnor005.starwarsdatabank.databinding.FragmentListBinding

class ListFragment : Fragment() {
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private val viewModel by activityViewModels<DatabankViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()

        binding.buttonAddItem.setOnClickListener {
            navController.navigate(ListFragmentDirections.actionListFragmentToAddFragment())
        }

        viewModel.entries.observe(viewLifecycleOwner) { entries ->
            binding.itemList.adapter = object : ArrayAdapter<Entry>(
                requireContext(), R.layout.list_item, R.id.title, entries.toList()
            ) {
                override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                    val adapterView = super.getView(position, convertView, parent)
                    val item = getItem(position)
                    if (item != null) {
                        val title = adapterView.findViewById<TextView>(R.id.title)
                        title.setCompoundDrawablesWithIntrinsicBounds(
                            item.getCategoryDrawable(
                                context
                            ), null, null, null
                        )
                        title.text = item.name
                    }
                    return adapterView
                }
            }

            binding.itemList.setOnItemClickListener { _, _, position, _ ->
                val item = entries[position]
                val action = ListFragmentDirections.actionListFragmentToDetailFragment(item)
                navController.navigate(action)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
