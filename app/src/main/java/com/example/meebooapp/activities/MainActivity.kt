package com.example.meebooapp.activities

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.meebooapp.R
import com.example.meebooapp.SignInActivity
import com.example.meebooapp.adapter.NotesAdapter
import com.example.meebooapp.database.NotesDatabase
import com.example.meebooapp.databinding.ActivityMainBinding
import com.example.meebooapp.entities.Note
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {

    //region BINDING and FIREBASE
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding
    val mAuth = FirebaseAuth.getInstance()
    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    //region MENU OPTIONS
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
    //endregion
}
