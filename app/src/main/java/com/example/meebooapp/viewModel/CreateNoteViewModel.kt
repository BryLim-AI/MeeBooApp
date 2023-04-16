package com.example.meebooapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CreateNoteViewModel: ViewModel() {
        var noteTitle: String? = null
        var noteSubtitle: String? = null
        var noteContent: String? = null
}