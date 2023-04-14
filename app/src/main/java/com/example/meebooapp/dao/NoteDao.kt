package com.example.meebooapp.dao

import androidx.room.*
import com.example.meebooapp.entities.Note


@Dao
interface NoteDao {
    @get:Query("SELECT * FROM notes ORDER BY id DESC")
    val allNotes: List<Note>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotes(note: Note)

    @Delete
    fun deleteNotes(note: Note)

    @Update
    fun update(note: Note)

}