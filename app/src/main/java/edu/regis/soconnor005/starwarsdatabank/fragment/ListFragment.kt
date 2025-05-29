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
        val entriesArrayAdapter = object : ArrayAdapter<Entry>(
            requireContext(), R.layout.list_item, R.id.text1, viewModel.getEntries().toList()
        ) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val adapterView = super.getView(position, convertView, parent)
                val item = getItem(position)
                if (item != null) {
                    adapterView.findViewById<TextView>(R.id.text1).text = item.name
                    adapterView.findViewById<TextView>(R.id.text2).text = item.description
                }
                return adapterView
            }
        }

        binding.itemList.adapter = entriesArrayAdapter
        binding.itemList.setOnItemClickListener { _, _, position, _ ->
            val item = viewModel.getEntries()[position]
            val action = ListFragmentDirections.actionListFragmentToDetailFragment(item)
            navController.navigate(action)
        }

        binding.buttonAddItem.setOnClickListener {
            navController.navigate(ListFragmentDirections.actionListFragmentToAddFragment())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
