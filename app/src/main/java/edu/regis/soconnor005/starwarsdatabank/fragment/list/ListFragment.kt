package edu.regis.soconnor005.starwarsdatabank.fragment.list

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import edu.regis.soconnor005.starwarsdatabank.WelcomeActivity
import edu.regis.soconnor005.starwarsdatabank.auth.AuthService
import edu.regis.soconnor005.starwarsdatabank.data.DatabankViewModel
import edu.regis.soconnor005.starwarsdatabank.database.Entry
import edu.regis.soconnor005.starwarsdatabank.databinding.FragmentListBinding
import edu.regis.soconnor005.starwarsdatabank.fragment.dialog.ErrorDialogFragment
import kotlinx.coroutines.launch

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

        val adapter =
            EntryListAdapter(onClickListener = { entry -> adapterOnClick(navController, entry) })
        binding.itemList.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.entries.collect { entries ->
                    adapter.submitList(entries)
                }
            }
        }

        binding.buttonAddItem.setOnClickListener {
            navController.navigate(ListFragmentDirections.actionListFragmentToAddFragment())
        }

        binding.buttonSignOut.setOnClickListener {
            lifecycleScope.launch {
                try {
                    AuthService.signOut()
                    val welcomeActivity = Intent(context, WelcomeActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    }
                    startActivity(welcomeActivity)
                    requireActivity().finish()
                } catch (e: Exception) {
                    ErrorDialogFragment(e.localizedMessage ?: "Unknown error").show(
                        parentFragmentManager, "SIGN_OUT_ERROR_DIALOG"
                    )
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun adapterOnClick(navController: NavController, entry: Entry) {
        viewModel.setCurrentItem(entry.id)
        navController.navigate(ListFragmentDirections.actionListFragmentToDetailFragment())
    }
}
