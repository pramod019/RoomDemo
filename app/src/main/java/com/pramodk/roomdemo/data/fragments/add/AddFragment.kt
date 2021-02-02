package com.pramodk.roomdemo.data.fragments.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.pramodk.roomdemo.R
import com.pramodk.roomdemo.data.Note
import com.pramodk.roomdemo.data.NoteViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*
import kotlinx.android.synthetic.main.fragment_list.view.*


class AddFragment : Fragment() {

    private lateinit var mNoteViewModel:NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_add, container, false)

        //init the view model
        mNoteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        //button to add notes to the data base
        view.btn_addNote.setOnClickListener{
            insertDataToDatabase()
        }
        return view
    }

    private fun insertDataToDatabase() {

        val title = edTxt_title.text.toString()
        val description = edTxt_description.text.toString()

        if(inputCheck(title,description)){
            //create note object
            val note = Note(0,title,description)
            //add data to database
            mNoteViewModel.addNote(note)
            Toast.makeText(requireContext(),"SUCCESSFULLY ADDED",Toast.LENGTH_SHORT).show()
            //Navigate back
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(),"PLEASE FILL ALL THE FIELDS",Toast.LENGTH_SHORT).show()
        }

    }

    // checking if any field is empty
    private fun inputCheck(title:String,description:String): Boolean{
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(description))
    }


}