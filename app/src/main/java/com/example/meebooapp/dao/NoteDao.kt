package com.example.meebooapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.OnConflictStrategy
import com.example.meebooapp.entities.Note


@Dao
interface NoteDao {
    @Query("SELECT * FROM notes ORDER BY id DESC") // table name set.
    fun getAllNotes(): List<Note> // entity file name

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotes(note: Note)

    @Delete
    fun deleteNotes(note: Note)

}