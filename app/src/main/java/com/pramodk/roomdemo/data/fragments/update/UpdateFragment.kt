package com.pramodk.roomdemo.data.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.pramodk.roomdemo.R
import com.pramodk.roomdemo.data.Note
import com.pramodk.roomdemo.data.NoteViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*


class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var mNoteViewModel:NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)
        //passing the viewModel
        mNoteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        //setting the values of ed txt from the recyclerView
        view.edTxt_update_title.setText(args.currentNote.Title)
        view.edTxt_update_description.setText(args.currentNote.Description)
        //Btn to update the values
        view.btn_update_Note.setOnClickListener{
            updateItem()
        }
        //add menu
        setHasOptionsMenu(true)
        return view
    }
    //fun to updaae the note
    private fun updateItem(){

        val title = edTxt_update_title.text.toString()
        val description = edTxt_update_description.text.toString()

        if(inputCheck(title,description)){
            //create note object
            val updateNote = Note(args.currentNote.id,title,description)
            //update data to database
            mNoteViewModel.updateNote(updateNote)
            Toast.makeText(requireContext(),"SUCCESSFULLY UPDATED", Toast.LENGTH_SHORT).show()
            //Navigate back
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(),"PLEASE FILL ALL THE FIELDS", Toast.LENGTH_SHORT).show()
        }
    }
    // checking if any field is empty
    private fun inputCheck(title:String,description:String): Boolean{
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(description))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deleteNote()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteNote() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){ _, _ ->
                mNoteViewModel.deleteNote(args.currentNote)
            Toast.makeText(requireContext(),"SUCCESSFULLY DELETED ${args.currentNote.Title}",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No"){ _, _ ->
        }
        builder.setTitle("Delete ${args.currentNote.Title}?")
        builder.setMessage("Are you sure u want to delete ${args.currentNote.Title}?")
        builder.create().show()

    }

}