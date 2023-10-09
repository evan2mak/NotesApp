package evtomak.iu.edu.notesapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private lateinit var sharedViewModel: NotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize shared ViewModel here
        val dao = NoteDatabase.getInstance(this).noteDao
        val viewModelFactory = NotesViewModelFactory(dao)
        sharedViewModel = ViewModelProvider(this, viewModelFactory).get(NotesViewModel::class.java)
    }

    // Provide a method to access the shared ViewModel
    fun getSharedViewModel() = sharedViewModel
}

