package com.example.notes.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.data.Note

class RecyclerViewAdapter(private val listener: NotesItemRVAdapter): RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewViewHolder>() {
    private val NoteList = ArrayList<Note>()

    inner class RecyclerViewViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        val textView:TextView = itemView.findViewById(R.id.TextViewNote)
        val button:Button = itemView.findViewById(R.id.ButtonAddNote)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)

        RecyclerViewViewHolder(view).button.setOnClickListener {
            listener.onItemClicked(NoteList[RecyclerViewViewHolder(view).adapterPosition])
        }

        return return RecyclerViewViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewViewHolder, position: Int) {
        val currentNote = NoteList[position]
        holder.textView.text = currentNote.text

    }

    override fun getItemCount(): Int {
        return NoteList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setUpNewList(newList: List<Note>) {
        NoteList.clear()
        NoteList.addAll(newList)
        notifyDataSetChanged()
    }
}

interface NotesItemRVAdapter {
    fun onItemClicked(note: Note)

}