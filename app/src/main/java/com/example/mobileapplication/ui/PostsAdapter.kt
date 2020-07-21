package com.example.mobileapplication.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileapplication.Item
import com.example.mobileapplication.R
import com.example.mobileapplication.data.local.posts.PostEntity

class PostsAdapter(comments : ArrayList<PostEntity>, var listener: AdapterCallback) : RecyclerView.Adapter<PostsAdapter.PostsViewHolder>() {

    private var dataSource: ArrayList<PostEntity> = ArrayList<PostEntity>()
    init {
        dataSource = comments
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return PostsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataSource.size
    }

    fun setItems(items: ArrayList<PostEntity>){
        dataSource?.clear()
        if (items != null) {
            dataSource.addAll(items)
        };
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        val item: PostEntity = dataSource[position]
        holder.title.text = item.name
    }

    class PostsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title : TextView = view.findViewById<TextView>(R.id.title)

    }


    interface AdapterCallback {
        fun onItemClicked(menuPosition: Int?)
    }

}
