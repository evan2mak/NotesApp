package evtomak.iu.edu.notesapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Factory for creating an instance of NotesViewModel with a specific NoteDao.
 */
class NotesViewModelFactory(private val dao: NoteDao)
    : ViewModelProvider.Factory {

    /**
     * Creates a new instance of the given ViewModel class.
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NotesViewModel::class.java)) {
            return NotesViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}
