package com.example.mobileapplication.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileapplication.Item
import com.example.mobileapplication.R

class CommentsAdapter(comments : ArrayList<Item>, var listener: AdapterCallback) : RecyclerView.Adapter<CommentsAdapter.CommentViewHolder>() {

    private var dataSource: ArrayList<Item> = ArrayList<Item>()
    init {
        dataSource = comments
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return CommentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataSource.size
    }

    fun setItems(items: ArrayList<Item>){
        dataSource?.clear()
        if (items != null) {
            dataSource.addAll(items)
        };
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val item: Item = dataSource[position]
        holder.title.text = item.Title
    }

    class CommentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title : TextView = view.findViewById<TextView>(R.id.title)

    }


    interface AdapterCallback {
        fun onItemClicked(menuPosition: Int?)
    }

}




