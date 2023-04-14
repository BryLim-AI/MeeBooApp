package com.example.meebooapp.activities

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import com.example.meebooapp.R
import com.example.meebooapp.database.NotesDatabase
import com.example.meebooapp.databinding.ActivityCreateNoteBinding
import com.example.meebooapp.entities.Note
import java.text.SimpleDateFormat
import java.util.*

class CreateNoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateNoteBinding
    private lateinit var inputTitle : EditText
    private lateinit var inputSubtitle: EditText
    private lateinit var  inputNote: EditText
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

        inputTitle = binding.editNoteTitle
        inputSubtitle = binding.editSubtitle
        inputNote = binding.inputNote

    } // ONCREATE METHOD.
    private fun saveNote(){
        if(inputTitle.text.trim().toString().isEmpty()){
            Toast.makeText(this,"Note Title cannot be blank.",Toast.LENGTH_SHORT).show()
            return
        }
        else if (
            inputSubtitle.text.trim().toString().isEmpty() &&
                    inputNote.text.trim().toString().isEmpty()
        ){
            Toast.makeText(this,"Notes cannot be blank.",Toast.LENGTH_SHORT).show()
            return
        }

        val notes = Note()
        notes.title = inputTitle.text.toString()
        notes.subTitle = inputSubtitle.text.toString()
        notes.note_text = inputNote.text.toString()

        class SaveNotesTask : AsyncTask<Void, Void, Void>() {

            override fun doInBackground(vararg params: Void?): Void? {
                NotesDatabase.getDatabase(applicationContext)?.notesDao()?.insertNotes(notes)
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

    }// SAVENOTE
} // CREATENOTEACTIVITY CLASS