package com.example.githubbrowser.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.githubbrowser.Fragments.BranchesFragment
import com.example.githubbrowser.R
import com.example.githubbrowser.ViewModels.DetailsViewModel
import com.example.githubbrowser.databinding.ActivityCommitsBinding

class CommitsActivity : AppCompatActivity() {

    lateinit var binding: ActivityCommitsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_commits)

        val branchIndex = intent.getIntExtra(BranchesFragment.BRANCH_CARD_KEY, -1)
        binding.backButtonAddRepo.setOnClickListener {
            super.onBackPressed()
        }

        binding.commitsBranchName.text = DetailsViewModel.branchesList[branchIndex]
    }
}