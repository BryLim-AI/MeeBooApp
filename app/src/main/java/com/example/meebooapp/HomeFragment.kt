package com.example.meebooapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.meebooapp.adapter.NotesAdapter
import com.example.meebooapp.database.NotesDatabase
import com.example.meebooapp.databinding.FragmentHomeBinding
import com.example.meebooapp.entities.Notes
import com.example.meebooapp.viewModel.MainViewModel
import com.example.meebooapp.viewModel.MyObserver
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider

class HomeFragment : BaseFragment() {
    private lateinit var binding: FragmentHomeBinding
    var notesAdapter: NotesAdapter= NotesAdapter()
    var arrNotes = ArrayList<Notes>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
        lifecycle.addObserver(MyObserver())
        val viewModel by viewModels<MainViewModel> ()


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)

        launch {
            context?.let {
                var notes = NotesDatabase.getDatabase(it).noteDao().getAllNotes()
                notesAdapter?.setData(notes)
                arrNotes = notes as ArrayList<Notes>
                binding.recyclerView.adapter = notesAdapter
            }
        }

        notesAdapter?.setOnClickListener(onClicked)

        binding.BtnCreateNote.setOnClickListener {
            replaceFragment(CreateNoteFragment.newInstance(),true)
        }

        binding.SearchViewComp.setOnQueryTextListener( object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {

                var tempArr = ArrayList<Notes>()

                for (arr in arrNotes){
                    if (arr.title!!.toLowerCase(Locale.getDefault()).contains(p0.toString())){
                        tempArr.add(arr)
                    }
                }
                notesAdapter.setData(tempArr)
                notesAdapter.notifyDataSetChanged()
                return true
            }

        })

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

    private val onClicked = object :NotesAdapter.onItemClickedListener{
        override fun onClicked(notesId: Int) {

            var fragment:Fragment
            var bundle=Bundle()
            bundle.putInt("noteId",notesId)
            fragment = CreateNoteFragment.newInstance()
            fragment.arguments = bundle

            replaceFragment(fragment,false)

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