package com.example.meebooapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.meebooapp.entities.Note
import com.example.meebooapp.dao.NoteDao


@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NotesDatabase : RoomDatabase() {



    companion object {

        var notesDatabase: NotesDatabase?= null
        @Synchronized
        fun getDatabase(context: Context):NotesDatabase{
            if(notesDatabase != null){
                notesDatabase = Room.databaseBuilder(
                    context,
                    NotesDatabase::class.java,
                    "notes.db"
                ).build()
            }
            return notesDatabase!!
        }
    }
    abstract fun notesDao(): NoteDao
}