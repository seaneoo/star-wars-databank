package edu.regis.soconnor005.starwarsdatabank.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import edu.regis.soconnor005.starwarsdatabank.R

class AddFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        view.findViewById<ImageButton>(R.id.button_back).setOnClickListener {
            findNavController().navigate(AddFragmentDirections.actionAddFragmentToListFragment())
        }

        view.findViewById<Button>(R.id.button_add_item).setOnClickListener {
            findNavController().navigate(AddFragmentDirections.actionAddFragmentToListFragment())
        }

        return view
    }
}
