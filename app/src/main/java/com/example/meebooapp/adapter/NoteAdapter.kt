package com.example.meebooapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.meebooapp.R
import com.example.meebooapp.entities.Notes

class NotesAdapter() :
    RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {
    var listener:onItemClickedListener?= null
    var arrList = ArrayList<Notes>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
      return NotesViewHolder(
          LayoutInflater.from(parent.context).inflate(R.layout.item_recyclerview_notes,parent,false)
      )
    }

    override fun getItemCount(): Int {
       return arrList.size
    }

    fun setData(arrNoteList:List<Notes>){
        arrList = arrNoteList as ArrayList<Notes>
    }

    fun setOnClickListener(listener1:onItemClickedListener){
        listener = listener1
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val titleTextView = holder.itemView.findViewById<TextView>(R.id.tvTitle)
        titleTextView.text = arrList[position].title

        val titleSubTextView = holder.itemView.findViewById<TextView>(R.id.tvDesc)
        titleSubTextView.text = arrList[position].noteText

        val titleDateView = holder.itemView.findViewById<TextView>(R.id.tvDateTime)
        titleDateView.text = arrList[position].dateTime

        val cardView = holder.itemView.findViewById<CardView>(R.id.cardView)

        cardView.setOnClickListener {
                listener!!.onClicked(arrList[position].id!!)
            }

    }
    class NotesViewHolder(view:View) : RecyclerView.ViewHolder(view){
    }
    interface onItemClickedListener{
        fun onClicked(noteId: Int)
    }
}
