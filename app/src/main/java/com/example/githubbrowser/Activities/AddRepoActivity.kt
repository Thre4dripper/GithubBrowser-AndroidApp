package com.example.githubbrowser.Activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
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
        }
    }

    /**======================================== METHOD FOR ADD NEW REPO =========================================== **/
    private fun addRepo() = CoroutineScope(Dispatchers.Main).launch {

        val owner=binding.ownerTextView.text.toString()
        val repoName=binding.addRepoTextView.text.toString()

        //fields should not be empty
        if(owner.isNotEmpty() && repoName.isNotEmpty()) {
            binding.ownerTextLayout.error=null
            binding.repoNameTextLayout.error=null

            //sending api call
            val task = async(Dispatchers.IO) {
                HomeViewModel.addNewRepo(owner, repoName)
            }

            //getting response
            val result=task.await()

            //API Response validity Check
            if(result==null)
                binding.errorTextView.text=getString(R.string.error_text_view)
            else {
                binding.errorTextView.text=""
                HomeViewModel.adapter.notifyItemInserted(HomeViewModel.reposList.size)
                finish()
            }
        }
        else {
            binding.ownerTextLayout.error = "Required*"
            binding.repoNameTextLayout.error = "Required*"
        }
    }
}