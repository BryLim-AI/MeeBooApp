package com.example.meebooapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.meebooapp.adapter.NotesAdapter
import com.example.meebooapp.database.NotesDatabase
import com.example.meebooapp.databinding.FragmentCreateNoteBinding
import com.example.meebooapp.databinding.FragmentHomeBinding
import com.example.meebooapp.entities.Notes
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment() {
    private lateinit var binding: FragmentHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)

        launch {
            context?.let {
                var notes = NotesDatabase.getDatabase(it).noteDao().getAllNotes()
                binding.recyclerView.adapter = NotesAdapter(notes)
            }
        }



        binding.BtnCreateNote.setOnClickListener {
            replaceFragment(CreateNoteFragment.newInstance(),true)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }
    companion object {
        @JvmStatic
        fun newInstance() =
            HomeFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    fun replaceFragment(
        fragment: Fragment,
        istransition: Boolean
    ){
        val fragmentTransition = requireActivity().supportFragmentManager?.beginTransaction()

        if (istransition){
            if (fragmentTransition != null) {
                fragmentTransition.setCustomAnimations(android.R.anim.slide_out_right,android.R.anim.slide_in_left)
            }
        }
        if (fragmentTransition != null) {
            fragmentTransition.replace(R.id.frame_layout,fragment).addToBackStack(fragment.javaClass.simpleName).commit()
        }
    }
}