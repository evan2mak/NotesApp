package evtomak.iu.edu.notesapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

/**
 * Adapter for displaying notes in a RecyclerView.
 */
class NotesAdapter(private val noteClickListener: NoteClickListener) : ListAdapter<Note, NotesAdapter.NoteViewHolder>(NoteDiffCallback()) {

    // Interface for note click actions
    interface NoteClickListener {
        fun onNoteClick(note: Note)
        fun onDeleteClick(note: Note)
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_note, parent, false)
        return NoteViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = getItem(position)
        Log.d("NotesAdapter", "Binding note: $note at position: $position")
        holder.noteTitle.text = note.title

        holder.noteTitle.setOnClickListener {
            noteClickListener.onNoteClick(note)
        }

        holder.deleteButton.setOnClickListener {
            noteClickListener.onDeleteClick(note)
        }
    }

    // ViewHolder pattern to represent items in the RecyclerView
    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val noteTitle: TextView = itemView.findViewById(R.id.note_title)
        val deleteButton: ImageButton = itemView.findViewById(R.id.delete_note_button)
    }

    // Helps calculate the difference between two lists
    class NoteDiffCallback : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.noteId == newItem.noteId
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }
    }
}
