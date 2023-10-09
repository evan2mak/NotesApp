# C323 Project 6 - Notes App: Evan Tomak

This project is a simple notes app that allows the user to create notes containing a title and a description. The user also has the ability to delete and edit the notes.

The functionality is described in more detail below:

# Main Screen (Add Note)

[X] The user opens the application and sees a UI interface with various components.

[X] The user will initially see an empty screen without any notes.

[X] The user can click the "Add Note" button to create a new note.

[X] The user will be navigated to the note creation screen.

# Note Creation Screen

[X] The user can now create a note by inputting a title and a description.

[X] When the user is done, they can click the "Save" button, and navigate back to the main screen.

# Main Screen (Update/Delete Note)

[X] After creating a note, the user will now see the title of the note along with a delete image icon next to the note.

[X] The notes will appear in a list, with the most recent note at the top.

[X] The user can update the note by clicking on the title. They will then be transported to the Note Creation screen to update the note.

[X] The user can also click the delete button which will remove the button from the list.

[X] The user will be prompted with a Delete Dialog that confirms or denies the user's intention of deleting a note.

[X] If the user confirms, the note is deleted. If the user cancels, nothing happens; the note remains in the list.

#

The following functions/extensions are implemented:

# HomeFragment

The home fragment is responsible for displaying a list of notes and provides the UI for interactions.

onCreateView:

This function inflates the fragment layout and sets up the RecyclerView and ViewModel.

onDestroyView:

This function cleans up the binding when the view is destroyed.

onNoteClick:

This function handles note click events to display the note's details.

onDeleteClick:

This function handles the note deletion via a confirmation dialog (Delete Dialog Fragment).

# DeleteDialogFragment

The delete dialog fragment class is responsible for showing a confirmation prompt before deleting a note.

onCreateDialog:

This function provides the functionality for prompting a message when the delete button is clicked.

# MainActivity

The main activity class helps set up the application and initializes the view model for use in other areas.

# Note

The note class is a data class representing a note in the database.

# NoteDao

The note dao class (DAO = Database Access Object) is an interface for CRUD operations on the note entity. The operations include insert, update, delete, and getters for note manipulation.

# NoteDatabase

This is a room database for storing notes. It either creates the database or reuses the databases depending on existing data within the database.

# NotesAdapter

The notes adapter is an adapter for displaying notes in a recycler view.

interface NoteClickListener:

This is an interface for note click actions onNoteClick and onDeleteClick.

onCreateViewholder:

This function creates new views.

onBindViewHolder:

This function replaces the contents of a view.

NoteViewHolder (class):

This class is responsible for the viewholder pattern to represent items in the recycler view.

NoteDiffCallback (class):

This class is helps calculate the difference between two lists.

# NotesFragment

The notes fragment class is a fragment to display and edit a single note.

onCreateView:

This function inflates the fragment's view, accesses the shared view model from the main activity, checks for existing notes and loads note data, and sets an onClickListener for the save button to either update an existing note or add a new one.

onDestroyView:

This function cleans the binding when the view is destroyed.

# NotesViewModel

The notes view model is a view model for managing notes using the note dao.

addNote:

This function adds a new note to the database.

deleteNote:

This function deletes a note from the database.

updateNote:

This function updates an existing note in the database.

getNoteByID:

This function retrieves a specific note by its ID.

# NotesViewModelFactory

The notes view model factory creates a new instance of a notes view model.

## Video Walkthrough

Here's a walkthrough of implemented user stories:

![studio64_fAxTRjqXzD](https://github.com/evan2mak/NotesApp/assets/128643914/f39a4441-e4cf-42d4-9492-bacc5e3cd1c1)




## Notes

By far the biggest challenges I faced in this app were handling add, update, and delete functionality. The UI components were not too bad, but getting an understanding of how the view model, dao, DB, fragments, and adapter worked together was not easy.
I also had challenges with displaying the UI of the notes in a list using recycler view. It took me so long to realize that one note was taking the entire screen. After scrolling, I realized this, and I simply had to change the height attribute to wrap content. Sometimes the biggest challenges are the smallest things.


## License

    Copyright 2023 Evan Tomak.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express implied.

    See the License for the specific language governing permissions and
    limitations under the License.
