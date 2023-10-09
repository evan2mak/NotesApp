package evtomak.iu.edu.notesapp

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Room database for storing notes.
 */
@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {
    abstract val noteDao: NoteDao
    companion object {
        // Singleton instance of the database
        @Volatile
        private var INSTANCE: NoteDatabase? = null

        /**
         * Get the singleton instance of the database.
         */
        fun getInstance(context: Context): NoteDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    Log.d("NoteDatabase", "Creating new database instance")
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        NoteDatabase::class.java,
                        "note_database"
                    ).build()
                    INSTANCE = instance
                    Log.d("NoteDatabase", "Database instance created")
                }
                else {
                    Log.d("NoteDatabase", "Reusing existing database instance")
                }
                return instance
            }
        }
    }
}
