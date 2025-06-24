package edu.regis.soconnor005.starwarsdatabank.fragment.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import edu.regis.soconnor005.starwarsdatabank.R

class SignOutDialogFragment(val signOut: () -> Unit) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setMessage(
                context?.getString(
                    R.string.sign_out_prompt, Firebase.auth.currentUser?.email ?: "N/A"
                )
            ).setPositiveButton(context?.getString(R.string.yes)) { _, _ -> signOut() }
                .setNegativeButton(context?.getString(R.string.no)) { _, _ -> }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}
