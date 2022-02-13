package com.example.githubbrowser.Adapters

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubbrowser.R
import com.example.githubbrowser.dataModels.RepoItem

class ReposRecyclerAdapter(
    private var repoList: MutableList<RepoItem>,
    private val homeOnClickInterface: HomeOnClickInterface
) :
    RecyclerView.Adapter<ReposRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_repo_card, parent, false)

        return ViewHolder(view, homeOnClickInterface)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repoItem = repoList[position]
        holder.repoName.text = repoItem.repoName
        if (repoItem.repoDesc != "null")
            holder.repoDesc.text = repoItem.repoDesc
        else holder.repoDesc.text = Html.fromHtml("<em>No Description</em>")

        Glide.with(holder.image.context).load(repoItem.imgUrl).circleCrop().into(holder.image)
    }

    override fun getItemCount() = repoList.size

    class ViewHolder(itemView: View, private val homeOnClickInterface: HomeOnClickInterface) :
        RecyclerView.ViewHolder(itemView) {
        var repoName = itemView.findViewById<TextView>(R.id.repo_name)!!
        var repoDesc = itemView.findViewById<TextView>(R.id.repo_desc)!!
        var image = itemView.findViewById<ImageView>(R.id.repo_owner_avatar)!!
        private var repoCard = itemView.findViewById<CardView>(R.id.repo_card)!!
        private var sendRepoDetails = itemView.findViewById<ImageView>(R.id.send_repo_info)!!

        init {
            repoCard.setOnClickListener { homeOnClickInterface.repoClick(adapterPosition) }

            sendRepoDetails.setOnClickListener {
                homeOnClickInterface.sendRepoDetails(
                    adapterPosition
                )
            }
        }
    }

    interface HomeOnClickInterface {
        fun repoClick(position: Int);
        fun sendRepoDetails(position: Int)
    }
}