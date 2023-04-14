package com.example.meebooapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.meebooapp.R
import com.example.meebooapp.entities.Note


class NotesAdapter(private val notes: List<Note>) : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_container_note, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.setNote(notes[position])
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textTitle: TextView = itemView.findViewById(R.id.textTitle)
        private val textSubtitle: TextView = itemView.findViewById(R.id.textSubTitle)
        private val textDateTime: TextView = itemView.findViewById(R.id.textDateTime)

        fun setNote(note: Note) {
            textTitle.text = note.title
            if (note.subTitle?.trim()?.isEmpty() == true) {
                textSubtitle.visibility = View.GONE
            } else {
                textSubtitle.text = note.subTitle
            }
            textDateTime.text = note.dateTime
        }
    }
}
