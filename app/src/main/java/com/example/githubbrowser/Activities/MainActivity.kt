package com.example.githubbrowser.Activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubbrowser.Adapters.ReposRecyclerAdapter
import com.example.githubbrowser.R
import com.example.githubbrowser.ViewModels.DetailsViewModel
import com.example.githubbrowser.ViewModels.HomeViewModel
import com.example.githubbrowser.dataModels.RepoItem
import com.example.githubbrowser.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ReposRecyclerAdapter.HomeOnClickInterface {

    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding

    companion object {
        const val REPO_CARD_KEY: String = "repo.card.onclick"
    }

    /**========================================================== ON CREATE =========================================================**/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initializeRecyclerView()

        binding.homeFab.setOnClickListener { addRepoActivity() }

        initializeHintLayouts()
    }

    /**===================================== METHOD FOR INITIALIZING HINT LAYOUTS ==============================================**/
    private fun initializeHintLayouts() {
        binding.addRepoButton2.setOnClickListener { addRepoActivity() }

        HomeViewModel.repoCount.value = 0
        HomeViewModel.repoCount.observe(this, Observer {
            if (it == 0) {
                binding.trackRepoTextView.visibility = View.VISIBLE
                binding.addRepoButton2.visibility = View.VISIBLE
            } else {
                binding.trackRepoTextView.visibility = View.GONE
                binding.addRepoButton2.visibility = View.GONE
            }
        })
    }

    /**========================================== METHOD FOR INITIALIZING RECYCLER VIEW ========================================**/
    private fun initializeRecyclerView() {
        binding.homeRv.layoutManager = LinearLayoutManager(this)
        HomeViewModel.adapter = ReposRecyclerAdapter(HomeViewModel.reposList, this)
        binding.homeRv.adapter = HomeViewModel.adapter
    }

    /**========================================== METHOD FOR STARTING ADD REPO ACTIVITY ========================================**/
    private fun addRepoActivity() {
        HomeViewModel.reposList.add(RepoItem("Owner", "Name", "Desc", "url", 25))
        HomeViewModel.adapter.notifyItemInserted(HomeViewModel.reposList.size)
//        val intent = Intent(this, AddRepoActivity::class.java)
//        startActivity(intent)
        HomeViewModel.repoCount.value = HomeViewModel.repoCount.value!! + 1
        Log.d(TAG, "addRepoActivity: " + HomeViewModel.repoCount.value)
    }

    /**============================================ ONCLICK METHOD FOR REPO CARDS ========================================**/
    override fun repoClick(position: Int) {

        //clearing lists before opening new repo
        DetailsViewModel.branchesList!!.clear()
        DetailsViewModel.issuesList!!.clear()
        Log.d(TAG, "repoClick: $position")

        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(REPO_CARD_KEY, position)
        startActivity(intent)
    }
}