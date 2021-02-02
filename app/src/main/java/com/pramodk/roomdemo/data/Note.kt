package com.pramodk.roomdemo.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "Note_data_table")
data class Note(
        @PrimaryKey(autoGenerate = true)
        var id:Int,
        var Title:String,
        var Description:String
):Parcelable