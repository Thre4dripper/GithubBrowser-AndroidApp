package com.example.githubbrowser.Activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.githubbrowser.R
import com.example.githubbrowser.ViewModels.HomeViewModel
import com.example.githubbrowser.databinding.ActivityAddRepoBinding

class AddRepoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddRepoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_add_repo)

        binding.addRepoButton.setOnClickListener {
            addRepo()
            finish()
        }
    }

    private fun addRepo() {
        HomeViewModel.getReposList()
    }
}