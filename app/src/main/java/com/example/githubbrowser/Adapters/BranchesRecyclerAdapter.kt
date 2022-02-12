package com.example.githubbrowser.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.githubbrowser.R

class BranchesRecyclerAdapter(
    private var branchList: MutableList<String>,
    private var branchClickInterface: BranchClickInterface
) :
    RecyclerView.Adapter<BranchesRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_branch_name, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.branchName.text = branchList[position]
        holder.branchCard.setOnClickListener {
            branchClickInterface.branchOnClick(position)
        }
    }

    override fun getItemCount() = branchList.size


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var branchName = itemView.findViewById<TextView>(R.id.branch_name_text_view)!!
        var branchCard = itemView.findViewById<ConstraintLayout>(R.id.branch_card)!!
    }

    interface BranchClickInterface {
        fun branchOnClick(position: Int)
    }
}