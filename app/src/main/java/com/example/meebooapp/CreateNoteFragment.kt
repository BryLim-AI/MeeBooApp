package com.example.meebooapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.meebooapp.database.NotesDatabase
import com.example.meebooapp.databinding.FragmentCreateNoteBinding
import com.example.meebooapp.entities.Notes
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class CreateNoteFragment : BaseFragment() {
    var currentDate:String? = null
    private var noteId = -1

    private lateinit var binding: FragmentCreateNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        noteId = requireArguments().getInt("noteId",-1)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateNoteBinding.inflate(inflater, container, false)
        return binding.root
    }
    companion object {
        @JvmStatic
        fun newInstance() =
            CreateNoteFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        if(noteId != -1){
            launch {
                context?.let {
                    var notes = NotesDatabase.getDatabase(it).noteDao().getSpecificNote(noteId)
                    binding.etNoteTitle.setText(notes.title)
                    binding.etNoteSubTitle.setText(notes.subTitle)
                    binding.etNoteDesc.setText(notes.noteText)
                    binding.imgNote.visibility = View.VISIBLE
                }
            }
        }

        val dateNowFormat = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        currentDate = dateNowFormat.format(Date())
        binding.tvDateTime.text = currentDate

        binding.imgDone.setOnClickListener {
            if(noteId != -1){
                updateNotes()
            }
            else{
                saveNotes()
            }
        }
        binding.imgBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        binding.imgDeleteBtn.setOnClickListener {
            DeleteNote()
        }

    }
    private fun DeleteNote(){
        launch {
            context?.let{
                NotesDatabase.getDatabase(it).noteDao().deleteSpecificNote(noteId)
                requireActivity().supportFragmentManager.popBackStack()
            }
        }
    }
    private fun updateNotes(){
        launch {
            context?.let {
                var notes = NotesDatabase.getDatabase(it).noteDao().getSpecificNote(noteId)
                notes.title = binding.etNoteTitle.text.toString()
                notes.subTitle = binding.etNoteSubTitle.text.toString()
                notes.noteText = binding.etNoteDesc.text.toString()
                notes.dateTime = currentDate
                NotesDatabase.getDatabase(it).noteDao().updateNote(notes)
                binding.etNoteTitle.setText("")
                binding.etNoteSubTitle.setText("")
                binding.etNoteDesc.setText("")
                requireActivity().supportFragmentManager.popBackStack()
            }
        }
    }
    private fun saveNotes() {
        if (binding.etNoteTitle.text.isNullOrEmpty()){
            Toast.makeText(context,"Note Title is Required",Toast.LENGTH_SHORT).show()
        }
        else if (binding.etNoteSubTitle.text.isNullOrEmpty()){
            Toast.makeText(context,"Note Sub Title is Required",Toast.LENGTH_SHORT).show()
        }
        else if (binding.etNoteDesc.text.isNullOrEmpty()){
            Toast.makeText(context,"Note Description is Required",Toast.LENGTH_SHORT).show()
        }
        else{

            launch {
                var notes = Notes()
                notes.title = binding.etNoteTitle.text.toString()
                notes.subTitle = binding.etNoteSubTitle.text.toString()
                notes.noteText = binding.etNoteDesc.text.toString()
                notes.dateTime = currentDate
//                notes.color = binding.selectedColor
//                notes.imgPath = binding.selectedImagePath
//                notes.webLink = binding.webLink
                context?.let {
                    NotesDatabase.getDatabase(it).noteDao().insertNotes(notes)
                    binding.etNoteTitle.setText("")
                    binding.etNoteSubTitle.setText("")
                    binding.etNoteDesc.setText("")
                    requireActivity().supportFragmentManager.popBackStack()
                }
            }
        }
    }

    fun replaceFragment(fragment:Fragment, istransition:Boolean){
        val fragmentTransition = requireActivity().supportFragmentManager.beginTransaction()

        if (istransition){
            fragmentTransition.setCustomAnimations(android.R.anim.slide_out_right,android.R.anim.slide_in_left)
        }
        fragmentTransition.replace(R.id.frame_layout,fragment).addToBackStack(fragment.javaClass.simpleName).commit()
    }


}//CREATE NOTE FRAGMENT