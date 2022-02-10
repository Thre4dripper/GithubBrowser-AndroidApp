package com.example.githubbrowser.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubbrowser.Adapters.ReposRecyclerAdapter
import com.example.githubbrowser.R
import com.example.githubbrowser.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)

        initializeRecyclerView()
    }

    private fun initializeRecyclerView()
    {
        binding.homeRv.layoutManager= LinearLayoutManager(this)
        val adapter= ReposRecyclerAdapter()
        binding.homeRv.adapter=adapter
    }
}