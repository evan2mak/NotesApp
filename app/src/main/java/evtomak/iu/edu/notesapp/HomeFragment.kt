package evtomak.iu.edu.notesapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import evtomak.iu.edu.notesapp.databinding.FragmentHomeBinding

/**
 * Fragment displaying a list of notes and provides UI for interactions.
 */
class HomeFragment : Fragment(), NotesAdapter.NoteClickListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val notesAdapter by lazy {
        Log.d("HomeFragment", "Adapter initialized")
        NotesAdapter(this)
    }

    /**
     * Inflate the fragment layout and set up the RecyclerView and ViewModel.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
            _binding = FragmentHomeBinding.inflate(inflater, container, false)
            binding.notesRecyclerView.layoutManager = LinearLayoutManager(context)
            binding.notesRecyclerView.adapter = notesAdapter

            // Access the shared ViewModel from MainActivity
            val viewModel = (activity as MainActivity).getSharedViewModel()
            binding.viewModel = viewModel
            binding.lifecycleOwner = viewLifecycleOwner

            viewModel.notes.observe(viewLifecycleOwner) { notes ->
                Log.d("HomeFragment", "Observed notes change: $notes")
                notesAdapter.submitList(notes)
            }

            binding.addNoteButton.setOnClickListener {
                val transaction = parentFragmentManager.beginTransaction()
                transaction.replace(R.id.fragment_container_view, NotesFragment())
                transaction.addToBackStack(null)
                transaction.commit()
            }

            return binding.root
    }

    /**
     * Clean up the binding when the view is destroyed.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /**
     * Handle note click events to display the note's details.
     */
    override fun onNoteClick(note: Note) {
        val transaction = parentFragmentManager.beginTransaction()
        val notesFragment = NotesFragment()
        val bundle = Bundle()
        bundle.putLong("noteId", note.noteId) // pass note ID
        notesFragment.arguments = bundle
        transaction.replace(R.id.fragment_container_view, notesFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }


    /**
     * Handle note deletion via a confirmation dialog.
     */
    override fun onDeleteClick(note: Note) {
        val deleteDialog = DeleteDialogFragment {
            val viewModel = (activity as MainActivity).getSharedViewModel()
            viewModel.deleteNote(note)
        }
        deleteDialog.show(parentFragmentManager, "deleteConfirmation")
    }
}
