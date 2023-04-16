package com.example.meebooapp.viewModel

import android.widget.EditText
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    private val _email = MutableLiveData<String>()
    private val _password = MutableLiveData<String>()
    private val _confirmPassword = MutableLiveData<String>()
    private val _search = MutableLiveData<String>()
    private val _title = MutableLiveData<String>()
    private val _subTitle = MutableLiveData<String>()
    private val _note = MutableLiveData<String>()

    val email: LiveData<String> = _email
    val password: LiveData<String> = _password
    val confirmPassword: LiveData<String> = _confirmPassword
    val search:LiveData<String> = _search
    var title:LiveData<String> = _title
    val subTitle:LiveData<String> = _subTitle
    val note:LiveData<String> = _note

    fun setTitle(title:String){
        _title.value = title
    }

    fun setSubTitle(subTitle:String){
        _subTitle.value = subTitle
    }
    fun note(note:String){
        _note.value = note
    }

    fun setEmail(email: String) {
        _email.value = email
    }

    fun setPassword(password: String) {
        _password.value = password
    }

    fun setConfirmPassword(confirmPassword: String) {
        _confirmPassword.value = confirmPassword
    }

    fun setSearch(search:String){
        _search.value = search
    }


}