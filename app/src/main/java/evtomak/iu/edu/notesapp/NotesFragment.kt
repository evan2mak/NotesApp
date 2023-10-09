package evtomak.iu.edu.notesapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import evtomak.iu.edu.notesapp.databinding.FragmentNotesBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Fragment to display and edit a single note.
 */
class NotesFragment : Fragment() {

    private var _binding: FragmentNotesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNotesBinding.inflate(inflater, container, false)

        // Access the shared ViewModel from MainActivity
        val viewModel = (activity as MainActivity).getSharedViewModel()
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        // Load existing note if ID is provided
        val noteId = arguments?.getLong("noteId", 0L)
        if (noteId != null && noteId != 0L) {
            viewModel.getNoteById(noteId).observe(viewLifecycleOwner) { note ->
                binding.editTextTitle.setText(note.title)
                binding.editTextDescription.setText(note.description)
            }
        }

        // Handle save button
        binding.saveButton.setOnClickListener {
            val title = binding.editTextTitle.text.toString()
            val description = binding.editTextDescription.text.toString()

            if (title.isNotEmpty()) {
                lifecycleScope.launch {

                    // For updating a note
                    if (noteId != null && noteId != 0L) {
                        Log.d("NotesFragment", "Updating note")

                        val existingNote = withContext(Dispatchers.IO) { viewModel.dao.getNoteDirectly(noteId) }
                        if (existingNote != null) {
                            existingNote.title = title
                            existingNote.description = description
                            withContext(Dispatchers.IO) { viewModel.updateNote(existingNote) }
                            Log.d("NotesFragment", "Success updating note")
                        }
                        else {
                            Log.e("NotesFragment", "Error: Note with ID $noteId not found!")
                        }
                    }
                    // For adding a new note
                    else {
                        Log.d("NotesFragment", "New note")
                        // Create new note
                        viewModel.newNoteTitle = title
                        viewModel.newNoteDescription = description
                        withContext(Dispatchers.IO) { viewModel.addNote() }
                    }
                    // Pop the back stack once the operation is complete
                    parentFragmentManager.popBackStack()
                }
            }
            else {
                Toast.makeText(context, "Title cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

    // Clean up the binding when the view is destroyed
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
