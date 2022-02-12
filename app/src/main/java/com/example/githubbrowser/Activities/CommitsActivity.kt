package com.example.githubbrowser.Activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubbrowser.Adapters.CommitsRecyclerAdapter
import com.example.githubbrowser.Fragments.BranchesFragment
import com.example.githubbrowser.R
import com.example.githubbrowser.ViewModels.CommitsViewModel
import com.example.githubbrowser.ViewModels.DetailsViewModel
import com.example.githubbrowser.ViewModels.HomeViewModel
import com.example.githubbrowser.databinding.ActivityCommitsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class CommitsActivity : AppCompatActivity() {

    lateinit var binding: ActivityCommitsBinding

    /**========================================================== ON CREATE =========================================================**/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_commits)

        val branchIndex = intent.getIntExtra(BranchesFragment.BRANCH_CARD_KEY, -1)
        CommitsViewModel.selectedBranch = branchIndex

        binding.backButtonAddRepo.setOnClickListener {
            super.onBackPressed()
        }

        binding.commitsBranchName.text = DetailsViewModel.branchesList!![branchIndex]

        initializeRecyclerView()
    }

    /**===================================================== INITIALIZING RECYCLER VIEW =============================================**/
    private fun initializeRecyclerView() {
        binding.commitsRv.layoutManager = LinearLayoutManager(this)

        //Network Check
        if (AddRepoActivity.checkForInternet(this))
            getCommits(CommitsViewModel.selectedBranch, this)
        else {
            binding.commitsLoadingTextView.visibility = View.VISIBLE
            binding.commitsLoadingTextView.text = getString(R.string.no_internet)
        }
    }

    /**====================================  METHOD FOR FETCHING COMMITS BY API CALL ============================================**/
    private fun getCommits(index: Int, context: Context) =
        CoroutineScope(Dispatchers.Main).launch {

            //API is only called first time
            if (CommitsViewModel.commitsList!!.size == 0) {
                binding.commitsLoadingProgressView.visibility = View.VISIBLE
                binding.commitsLoadingTextView.visibility = View.VISIBLE
                //sending api call
                val task = async(Dispatchers.IO) {
                    CommitsViewModel.getCommitsList(
                        HomeViewModel.reposList[index].repoOwner,
                        HomeViewModel.reposList[index].repoName,
                        DetailsViewModel.branchesList!![index]
                    )
                }

                //getting response
                val result = task.await()

                if (result == null) {
                    binding.commitsLoadingTextView.text = getString(R.string.time_out)
                    binding.commitsLoadingProgressView.visibility = View.GONE

                    //resetting list for recall api req in case on time out
                    CommitsViewModel.commitsList = mutableListOf()
                    return@launch
                } else {
                    binding.commitsLoadingTextView.visibility = View.GONE
                    binding.commitsLoadingProgressView.visibility = View.GONE
                }

            }

            CommitsViewModel.commitsAdapter =
                CommitsRecyclerAdapter(CommitsViewModel.commitsList!!)
            binding.commitsRv.adapter = CommitsViewModel.commitsAdapter
        }
}