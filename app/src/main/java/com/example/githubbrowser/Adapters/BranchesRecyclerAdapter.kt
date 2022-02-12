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
        return ViewHolder(view,branchClickInterface)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.branchName.text = branchList[position]
    }

    override fun getItemCount() = branchList.size


    class ViewHolder(itemView: View,private val branchClickInterface: BranchClickInterface) : RecyclerView.ViewHolder(itemView) {
        var branchName = itemView.findViewById<TextView>(R.id.branch_name_text_view)!!
        private var branchCard = itemView.findViewById<ConstraintLayout>(R.id.branch_card)!!

        init {
            branchCard.setOnClickListener { branchClickInterface.branchOnClick(adapterPosition) }
        }
    }

    interface BranchClickInterface {
        fun branchOnClick(position: Int)
    }
}