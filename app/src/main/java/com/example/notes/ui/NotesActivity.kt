package com.example.notes.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notes.R
import com.example.notes.adapters.NotesItemRVAdapter
import com.example.notes.adapters.RecyclerViewAdapter
import com.example.notes.data.Note
import com.example.notes.model.MyViewModel
import kotlinx.android.synthetic.main.activity_notes.*

class NotesActivity : AppCompatActivity() , NotesItemRVAdapter {

    private lateinit var viewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)

        val recyclerViewAdapter = RecyclerViewAdapter(this)
        RecyclerViewNote.layoutManager = LinearLayoutManager(this)
        RecyclerViewNote.adapter = recyclerViewAdapter

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(MyViewModel::class.java)

        viewModel.allNotes.observe(this, Observer {  responseList ->
            responseList?.let {
                recyclerViewAdapter.setUpNewList(it)
            }
        })
    }



    override fun onItemClicked(note: Note) {
        viewModel.deleteNote(note)
        Toast.makeText(this,"Note item deleted successfully", Toast.LENGTH_SHORT).show()
    }


    fun submitNote(view: android.view.View) {
        val noteText = EditTextNote.text.toString()
        if(noteText.isNotEmpty()){
            viewModel.insertNote(Note(noteText))
            Toast.makeText(this,"Note item added successfully", Toast.LENGTH_SHORT).show()
        }

    }

}