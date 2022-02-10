package com.example.githubbrowser.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubbrowser.R

class ReposRecyclerAdapter : RecyclerView.Adapter<ReposRecyclerAdapter.ViewHolder>() {

    var RepoList:List<String>?=null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_repo_card,parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    }

    override fun getItemCount() = 10

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    }
}