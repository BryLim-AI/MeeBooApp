package com.example.meebooapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.meebooapp.R
import com.example.meebooapp.entities.Notes

class NotesAdapter(val notesList: List<Notes>) :
    RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {
        class NotesViewHolder(view:View):RecyclerView.ViewHolder(view){

        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
      return NotesViewHolder(
          LayoutInflater.from(parent.context).inflate(R.layout.item_recyclerview_notes,parent,false)
      )
    }

    override fun getItemCount(): Int {
       return notesList.size
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val titleTextView = holder.itemView.findViewById<TextView>(R.id.tvTitle)
        titleTextView.text = notesList[position].title

        val titleSubTextView = holder.itemView.findViewById<TextView>(R.id.tvDesc)
        titleSubTextView.text = notesList[position].noteText

        val titleDateView = holder.itemView.findViewById<TextView>(R.id.tvDateTime)
        titleDateView.text = notesList[position].dateTime


    }


}
class NotesViewHolder(view:View) : RecyclerView.ViewHolder(view){

}