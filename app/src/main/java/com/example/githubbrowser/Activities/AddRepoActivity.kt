package com.example.githubbrowser.Activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.githubbrowser.Networking.ApiResponse
import com.example.githubbrowser.R
import com.example.githubbrowser.ViewModels.HomeViewModel
import com.example.githubbrowser.databinding.ActivityAddRepoBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class AddRepoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddRepoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_repo)

        binding.addRepoButton.setOnClickListener {
            addRepo()
            finish()
        }
    }

    private fun addRepo() = CoroutineScope(Dispatchers.Main).launch {

        val task = async(Dispatchers.IO) {
            HomeViewModel.addNewRepo(binding.ownerTextView.text.toString(),binding.addRepoTextView.text.toString())
        }
        task.await()
        HomeViewModel.adapter.notifyItemInserted(HomeViewModel.reposList.size)
    }
}