package com.senaaslantas.todolist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.senaaslantas.todolist.databinding.RecyclerRowBinding
import com.senaaslantas.todolist.model.Post

class FeedRecyclerAdapter(private val postList:ArrayList<Post>):RecyclerView.Adapter<FeedRecyclerAdapter.PostHolder>(){
    class PostHolder(val binding: RecyclerRowBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        val binding=RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PostHolder(binding)

    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {

        holder.binding.Recyclerdate.text=postList.get(position).date
        holder.binding.Recyclernote.text=postList.get(position).note
        holder.binding.Recyclertime.text=postList.get(position).time

    }

    override fun getItemCount(): Int {
        return postList.size

    }
}