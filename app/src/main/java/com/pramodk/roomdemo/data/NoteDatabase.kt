package com.pramodk.roomdemo.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Note::class],version = 1,exportSchema = false)
abstract class NoteDatabase:RoomDatabase() {
    //fun to acess the note dao(noteDatabase->noteDao)
    abstract fun noteDao(): NoteDao
    //creating singleton object
    companion object{
        @Volatile
        private var INSTANCE: NoteDatabase? = null

        fun getDatabase(context: Context):NoteDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null)
            {
                return tempInstance
            }
                synchronized(this){
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        NoteDatabase::class.java,
                        "note_database"
                    ).build()
                    INSTANCE = instance
                    return instance
                }
        }
    }
}