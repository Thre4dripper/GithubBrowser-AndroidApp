package com.example.githubbrowser.Activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubbrowser.R
import com.example.githubbrowser.ViewModels.HomeViewModel
import com.example.githubbrowser.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initializeRecyclerView()

        binding.homeFab.setOnClickListener {
            addRepoActivity()
        }
    }

    /**========================================== METHOD FOR INITIALIZING RECYCLER VIEW ========================================**/
    private fun initializeRecyclerView() {
        binding.homeRv.layoutManager = LinearLayoutManager(this)
        binding.homeRv.adapter = HomeViewModel.adapter
    }

    /**========================================== METHOD FOR STARTING ADD REPO ACTIVITY ========================================**/
    private fun addRepoActivity() {
        val intent = Intent(this, AddRepoActivity::class.java)
        startActivity(intent)
    }
}