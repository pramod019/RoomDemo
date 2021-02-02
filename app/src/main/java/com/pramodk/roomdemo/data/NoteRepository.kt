package com.pramodk.roomdemo.data

import androidx.lifecycle.LiveData

class NoteRepository(private val noteDao: NoteDao) {

    //noteRepository has access to noteDao

    //Read all the data
    val readAllData:LiveData<List<Note>> = noteDao.readAllNotes()

    //add note to DB
    suspend fun addNote(note: Note){
        noteDao.addNote(note)
    }
    suspend fun updateNote(note: Note){
        noteDao.updateNote(note)
    }
    suspend fun deleteNote(note: Note){
        noteDao.deleteNote(note)
    }
    suspend fun deleteAllNotes(){
        noteDao.deleteAllNotes()
    }
}