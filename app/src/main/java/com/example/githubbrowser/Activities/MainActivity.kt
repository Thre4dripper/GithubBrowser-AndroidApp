package com.example.githubbrowser.Activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubbrowser.Adapters.ReposRecyclerAdapter
import com.example.githubbrowser.R
import com.example.githubbrowser.ViewModels.DetailsViewModel
import com.example.githubbrowser.ViewModels.HomeViewModel
import com.example.githubbrowser.dataModels.RepoItem
import com.example.githubbrowser.database.RepoDetailsDatabase
import com.example.githubbrowser.database.RepoDetailsEntity
import com.example.githubbrowser.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

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

        binding.homeFab.setOnClickListener { addRepoActivity() }

        initializeRecyclerView()
        initializeHintLayouts()
        getFromDatabase()
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
        val intent = Intent(this, AddRepoActivity::class.java)
        startActivity(intent)
    }

    /**====================================== METHOD FOR GETTING REPO LIST FROM DATABASE =====================================**/
    private fun getFromDatabase() {
        val repoDetailsList: MutableList<RepoItem> = mutableListOf()
        lifecycleScope.launch(Dispatchers.Main) {

            val repoDetails =
                RepoDetailsDatabase.getDatabase(this@MainActivity).entityDao().getData()

            for (i in 0 until repoDetails.size)
                repoDetailsList.add(
                    RepoItem(
                        repoDetails[i].id,
                        repoDetails[i].repoOwner,
                        repoDetails[i].repoName,
                        repoDetails[i].repoDesc,
                        repoDetails[i].imgUrl,
                        repoDetails[i].issues
                    )
                )

            if (repoDetailsList.size > 0) {
                HomeViewModel.reposList.addAll(repoDetailsList)
                HomeViewModel.repoCount.value =
                    HomeViewModel.repoCount.value!! + repoDetailsList.size
                HomeViewModel.adapter.notifyItemRangeInserted(0, repoDetailsList.size)
                HomeViewModel.lastDataBaseID = repoDetailsList[repoDetailsList.size - 1].id + 1
            }
        }
    }


    /**============================================= ONCLICK METHOD FOR REPO CARDS ===========================================**/
    override fun repoClick(position: Int) {

        //clearing lists before opening new repo
        DetailsViewModel.branchesList!!.clear()
        DetailsViewModel.issuesList!!.clear()
        Log.d(TAG, "repoClick: $position")

        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(REPO_CARD_KEY, position)
        startActivity(intent)
    }

    /**=================================== ONCLICK METHOD FOR REPO CARDS SEND BUTTON ======================================**/
    override fun sendRepoDetails(position: Int) {
        val clickedRepo = HomeViewModel.reposList[position]
        val repoName = clickedRepo.repoName
        val repoDesc = clickedRepo.repoDesc

        val github = "https://github.com/"
        val ownerName = HomeViewModel.reposList[position].repoOwner
        val repoUrl = "$github/$ownerName/$repoName"

        val repoDetails = "Repo Name: $repoName\n" +
                "Repo Description: $repoDesc\n" +
                "Repo Link: $repoUrl"

        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, repoDetails)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }
}