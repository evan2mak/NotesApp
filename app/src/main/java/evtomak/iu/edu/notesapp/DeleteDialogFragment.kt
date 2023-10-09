package evtomak.iu.edu.notesapp

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

/**
 * A DialogFragment to show a confirmation prompt before deleting a note.
 */

class DeleteDialogFragment(private val positiveCallback: () -> Unit) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setMessage("Are you sure you want to delete this note?")
                .setPositiveButton("Delete") { _, _ ->
                    positiveCallback.invoke()
                }
                .setNegativeButton("Cancel") { dialog, _ ->
                    dialog.dismiss()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}


