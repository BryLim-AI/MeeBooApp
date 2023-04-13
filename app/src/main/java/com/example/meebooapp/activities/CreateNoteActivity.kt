package com.example.meebooapp.activities

import android.app.Activity
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.meebooapp.R
import com.example.meebooapp.database.NotesDatabase
import com.example.meebooapp.databinding.ActivityCreateNoteBinding
import com.example.meebooapp.entities.Note
import java.text.SimpleDateFormat
import java.util.*

class CreateNoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.imgBack.setOnClickListener {
            onBackPressed()
        }
        binding.txtDateNow.text =
            SimpleDateFormat("EEEE, dd MMMM yyyy HH:mm:a", Locale.getDefault())
                .format(Date())

        binding.imageSave.setOnClickListener {
            saveNote()
        }
    }

    private fun saveNote() {
        if (binding.editNoteTitle.text.toString().trim().isEmpty()) {
            Toast.makeText(
                this, "Title cannot be Empty!",
                Toast.LENGTH_SHORT
            ).show()
            return
        } else if (binding.editSubtitle.text.toString().trim().isEmpty()
            &&
            binding.inputNote.text.toString().isEmpty()
        ) {
            Toast.makeText(
                this, "Note cannot be empty!",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        val note = Note(
            1, // title
            binding.editNoteTitle.text.toString(),
            binding.editSubtitle.text.toString(),
            binding.inputNote.text.toString(),
            binding.txtDateNow.text.toString()
        )

        class SaveNotesTask : AsyncTask<Void, Void, Void>() {

            override fun doInBackground(vararg params: Void?): Void? {
                NotesDatabase.getDatabase(applicationContext)?.notesDao()?.insertNotes(note)
                return null
            }

            override fun onPostExecute(result: Void?) {
                super.onPostExecute(result)
                val intent = Intent()
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }
        SaveNotesTask().execute()
    }
}