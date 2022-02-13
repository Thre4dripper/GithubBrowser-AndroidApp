package com.example.githubbrowser.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubbrowser.R
import com.example.githubbrowser.dataModels.CommitItem

class CommitsRecyclerAdapter(private val commitsList: MutableList<CommitItem>) :
    RecyclerView.Adapter<CommitsRecyclerAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_commit_card, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val commitItem = commitsList[position]

        holder.commitDate.text = parseDate(commitItem.date)
        holder.commitSHA.text = commitItem.shaCode
        holder.commitMessage.text = commitItem.commitMessage
        Glide.with(holder.committerAvatar.context).load(commitItem.imgUrl).circleCrop()
            .into(holder.committerAvatar)

        holder.committerName.text = commitItem.committerName
    }

    override fun getItemCount() = commitsList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val commitDate = itemView.findViewById<TextView>(R.id.commit_date_text_view)!!
        val commitSHA = itemView.findViewById<TextView>(R.id.commit_sha_text_view)!!
        val commitMessage = itemView.findViewById<TextView>(R.id.commit_message_text_view)!!
        val committerAvatar = itemView.findViewById<ImageView>(R.id.committer_avatar)!!
        val committerName = itemView.findViewById<TextView>(R.id.committer_name_text_view)!!
    }

    private fun parseDate(dateString: String): String {
        val year = dateString.substring(0, 4)
        var month = dateString.substring(5, 7)
        val date = dateString.substring(8, 10)

        month = when (month) {
            "01" -> "Jan"
            "02" -> "Feb"
            "03" -> "Mar"
            "04" -> "Apr"
            "05" -> "May"
            "06" -> "Jun"
            "07" -> "July"
            "08" -> "Aug"
            "09" -> "Sept"
            "10" -> "Oct"
            "11" -> "Nov"
            else -> "Dec"
        }

        return "$date $month $year"
    }
}