package evtomak.iu.edu.notesapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Data class representing a note in the database.
 */
@Entity(tableName = "note_table")
data class Note(
    @PrimaryKey(autoGenerate = true)
    var noteId: Long = 0L,
    @ColumnInfo(name = "note_title")
    var title: String = "",
    @ColumnInfo(name = "note_description")
    var description: String = ""
)
