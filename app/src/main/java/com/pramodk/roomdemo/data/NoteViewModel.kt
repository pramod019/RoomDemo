package com.pramodk.roomdemo.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class NoteViewModel(application: Application):AndroidViewModel(application) {
    //to read all the data in DB
    val readAllData:LiveData<List<Note>>
    // to access the function in repository
    private val noteRepository:NoteRepository
    // this block will run first when viewModel is called
    init {
        //to get the DB
        val noteDao = NoteDatabase.getDatabase(application).noteDao()
        //
        noteRepository = NoteRepository(noteDao)
        //read the data from db
        readAllData = noteRepository.readAllData
    }

    //to add note to the data base
    fun addNote(note: Note){
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.addNote(note)
        }
    }
    fun updateNote(note: Note){
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.updateNote(note)
        }
    }
    fun deleteNote(note: Note){
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.deleteNote(note)
        }
    }
    fun deleteAllNotes(){
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.deleteAllNotes()
        }
    }

}