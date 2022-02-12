package com.example.githubbrowser.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.githubbrowser.R
import com.example.githubbrowser.databinding.ActivityCommitsBinding

class CommitsActivity : AppCompatActivity() {

    lateinit var binding: ActivityCommitsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_commits)

        binding.backButtonAddRepo.setOnClickListener {
            super.onBackPressed()
        }
    }
}