package evtomak.iu.edu.notesapp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

/**
 * DAO (Data Access Object) interface for CRUD operations on the Note entity.
 */
@Dao
interface NoteDao {
    @Insert
    suspend fun insert(note: Note)
    @Update
    suspend fun update(note: Note)
    @Delete
    suspend fun delete(note: Note)
    @Query("SELECT * FROM note_table WHERE noteId = :key")
    fun get(key: Long): LiveData<Note>
    @Query("SELECT * FROM note_table ORDER BY noteId DESC")
    fun getAll(): LiveData<List<Note>>
    @Query("SELECT * FROM note_table WHERE noteId = :key")
    fun getNoteDirectly(key: Long): Note?
}
