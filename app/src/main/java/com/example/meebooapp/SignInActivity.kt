package com.example.meebooapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.meebooapp.activities.MainActivity
import com.example.meebooapp.databinding.ActivitySignInBinding
import com.example.meebooapp.viewModel.MainViewModel
import com.example.meebooapp.viewModel.MyObserver
import com.google.firebase.auth.FirebaseAuth

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    private lateinit var firebaseAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()
        binding.textViewSignIn.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
        lifecycle.addObserver(MyObserver())
        val viewModel by viewModels<MainViewModel> ()

        viewModel.email.observe(this) { email ->
            binding.emailAdd.setText(email)
        }

        viewModel.password.observe(this){password ->
            binding.password.setText(password)
        }

        binding.SignUpbutton.setOnClickListener{
            val email = binding.emailAdd.text.toString()
            val password = binding.password.text.toString()

            if(email.isNotEmpty() && password.isNotEmpty()){
                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }else{
                Toast.makeText(this,"All fields must be filled out!", Toast.LENGTH_SHORT).show()
            }
        }


    }
}