package com.pramodk.roomdemo.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNote(note:Note)

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("DELETE FROM Note_data_table")
    suspend fun deleteAllNotes()

    @Query("SELECT * FROM NOTE_DATA_TABLE ORDER BY id ASC")
    fun readAllNotes():LiveData<List<Note>>


}