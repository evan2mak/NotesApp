package evtomak.iu.edu.notesapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

/**
 * ViewModel for managing notes using the NoteDao.
 */
class NotesViewModel(val dao: NoteDao) : ViewModel() {
    var newNoteTitle = ""
    var newNoteDescription = ""
    var notes = dao.getAll()

    /**
     * Add a new note to the database.
     */
    fun addNote() {
        viewModelScope.launch {
            try {
                val note = Note(title = newNoteTitle, description = newNoteDescription)
                Log.d("NotesViewModel", "Attempting to add note: $note")
                dao.insert(note)
                Log.d("NotesViewModel", "Note added: $note")
            }
            catch (e: Exception) {
                Log.e("NotesViewModel", "Error adding note: ", e)
            }
        }
    }

    /**
     * Delete a note from the database.
     */
    fun deleteNote(note: Note) {
        viewModelScope.launch {
            dao.delete(note)
        }
    }

    /**
     * Update an existing note in the database.
     */
    fun updateNote(note: Note) {
        Log.d("NotesViewModel", "Updating note: $note")
        viewModelScope.launch {
            dao.update(note)
        }
    }

    /**
     * Retrieve a specific note using its ID.
     */
    fun getNoteById(id: Long): LiveData<Note> {
        return dao.get(id)
    }
}
