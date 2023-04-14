package com.example.meebooapp.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.meebooapp.R
import com.example.meebooapp.SignInActivity
import com.example.meebooapp.database.NotesDatabase
import com.example.meebooapp.databinding.ActivityMainBinding
import com.example.meebooapp.entities.Note
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private val REQUEST_CODE_NOTE:Int = 1
    private lateinit var noteRecycleViewer: RecyclerView
    private lateinit var noteList: MutableList<Note>

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addImgBtn.setOnClickListener {
            val intent = Intent(this, CreateNoteActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_NOTE)
        }
        getNotes()
    }
    val mAuth = FirebaseAuth.getInstance()
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.note_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout -> {
                mAuth.signOut()
                val intent = Intent(this, SignInActivity::class.java)
                startActivity(intent)
            }
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }
    private fun getNotes() {
        @SuppressLint("StaticFieldLeak")
        class GetNoteTask : AsyncTask<Void, Void, List<Note>>() {
            override fun doInBackground(vararg voids: Void): List<Note> {
                return NotesDatabase.getDatabase(applicationContext)
                    .notesDao().getAllNotes()
            }
            override fun onPostExecute(notes: List<Note>) {
                super.onPostExecute(notes)

            }
        }
        GetNoteTask().execute()
    }
}