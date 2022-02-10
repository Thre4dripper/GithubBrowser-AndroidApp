package com.example.githubbrowser.Activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubbrowser.Adapters.ReposRecyclerAdapter
import com.example.githubbrowser.R
import com.example.githubbrowser.ViewModels.HomeViewModel
import com.example.githubbrowser.dataModels.RepoItem
import com.example.githubbrowser.databinding.ActivityMainBinding
import java.io.Serializable

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initializeRecyclerView()

        binding.homeFab.setOnClickListener {
            addRepo()
        }
    }

    private fun initializeRecyclerView() {
        binding.homeRv.layoutManager = LinearLayoutManager(this)
        binding.homeRv.adapter = HomeViewModel.adapter
    }

    private fun addRepo() {
        val intent = Intent(this, AddRepoActivity::class.java);
        startActivity(intent)
    }
}