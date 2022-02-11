package com.example.githubbrowser.Activities

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.Toast
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
            if (verifyAvailableNetwork(this))
                addRepo()
            else Toast.makeText(this, "No Internet Connection", Toast.LENGTH_LONG).show()
        }
    }

    /**======================================== METHOD FOR ADD NEW REPO =========================================== **/
    private fun addRepo() = CoroutineScope(Dispatchers.Main).launch {

        val owner = binding.ownerTextView.text.toString()
        val repoName = binding.addRepoTextView.text.toString()

        //fields should not be empty
        if (owner.isNotEmpty() && repoName.isNotEmpty()) {
            binding.ownerTextLayout.error = null
            binding.repoNameTextLayout.error = null

            //sending api call
            val task = async(Dispatchers.IO) {
                HomeViewModel.addNewRepo(owner, repoName)
            }

            //getting response
            val result = task.await()

            //API Response validity Check
            if (result == null) {
                binding.repoNameTextLayout.error = getString(R.string.repo_not_found)
            } else {
                binding.repoNameTextLayout.error = null
                HomeViewModel.adapter.notifyItemInserted(HomeViewModel.reposList.size)
                finish()
            }
        } else {
            binding.ownerTextLayout.error = "Required*"
            binding.repoNameTextLayout.error = "Required*"
        }
    }

    fun verifyAvailableNetwork(activity: AppCompatActivity): Boolean {
        val connectivityManager =
            activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}