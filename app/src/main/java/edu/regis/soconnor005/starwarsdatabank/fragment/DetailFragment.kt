package edu.regis.soconnor005.starwarsdatabank.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import edu.regis.soconnor005.starwarsdatabank.R

class DetailFragment : Fragment() {
    private val args by navArgs<DetailFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)

        val entry = args.entry
        view.findViewById<TextView>(R.id.item_category).text = entry.category
        view.findViewById<TextView>(R.id.item_name).text = entry.name
        view.findViewById<TextView>(R.id.item_description).text = entry.description

        view.findViewById<ImageButton>(R.id.button_back).setOnClickListener {
            findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToListFragment())
        }

        val buttonEdit = view.findViewById<ImageButton>(R.id.button_edit)
        buttonEdit.contentDescription = getString(R.string.edit_item, entry)
        buttonEdit.setOnClickListener {
            findNavController().navigate(
                DetailFragmentDirections.actionDetailFragmentToEditFragment(
                    entry
                )
            )
        }

        return view
    }
}
