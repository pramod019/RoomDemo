package com.pramodk.roomdemo.data.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.pramodk.roomdemo.R
import com.pramodk.roomdemo.data.Note
import kotlinx.android.synthetic.main.note_lists.view.*

class ListAdapter():RecyclerView.Adapter<ListAdapter.MyViewHolder>(){

     private var noteList = emptyList<Note>()

    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.note_lists,parent,false)
            return MyViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = noteList[position]
        holder.itemView.txt_id.text = currentItem.id.toString()
        holder.itemView.txt_title.text = currentItem.Title
        holder.itemView.txt_Description.text = currentItem.Description
        holder.itemView.note_Layout.setOnClickListener{
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return noteList.size
    }
    fun setData(note:List<Note>){
        this.noteList = note
        notifyDataSetChanged()
    }
}
