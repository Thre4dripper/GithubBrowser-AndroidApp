package com.example.githubbrowser.Adapters

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubbrowser.R
import com.example.githubbrowser.dataModels.IssueItem

class IssueRecyclerAdapter(private val issueList: MutableList<IssueItem>) :
    RecyclerView.Adapter<IssueRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_issue_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val issueItem = issueList[position]
        holder.issueText.text = issueItem.issue
        Glide.with(holder.issuerAvatar.context).load(issueItem.imgUrl).circleCrop()
            .into(holder.issuerAvatar)

        holder.issuerName.text = issueItem.issuerName
    }

    override fun getItemCount() = issueList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val issueText = itemView.findViewById<TextView>(R.id.issue_title_text_view)!!
        val issuerAvatar = itemView.findViewById<ImageView>(R.id.issue_creator_avatar)!!
        val issuerName = itemView.findViewById<TextView>(R.id.issue_creator_name)!!
    }
}