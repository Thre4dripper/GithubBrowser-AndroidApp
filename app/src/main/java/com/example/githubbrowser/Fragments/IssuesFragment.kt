package com.example.githubbrowser.Fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubbrowser.Activities.AddRepoActivity
import com.example.githubbrowser.Adapters.IssueRecyclerAdapter
import com.example.githubbrowser.R
import com.example.githubbrowser.ViewModels.DetailsViewModel
import com.example.githubbrowser.ViewModels.HomeViewModel
import com.example.githubbrowser.databinding.FragmentIssuesBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class IssuesFragment() : Fragment() {

    private val TAG = "IssuesFragment"
    private lateinit var binding: FragmentIssuesBinding

    /**======================================================= ON CREATE VIEW =======================================================**/
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_issues, container, false)
        return binding.root
    }

    /**======================================================= ON VIEW CREATED =======================================================**/
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val context = requireContext() as Activity
        binding.issueRv.layoutManager = LinearLayoutManager(context)

        //Network Check
        if (AddRepoActivity.checkForInternet(context))
            getIssues(DetailsViewModel.selectedRepo, context)
        else {
            binding.issuesLoadingTextView.visibility = View.VISIBLE
            binding.issuesLoadingTextView.text = getString(R.string.no_internet)
        }
    }

    /**====================================  METHOD FOR FETCHING ISSUES BY API CALL ============================================**/
    private fun getIssues(index: Int, context: Context) = CoroutineScope(Dispatchers.Main).launch {

        //API will not be called on 0 issues
        if (HomeViewModel.reposList[index].issues == 0) {
            binding.issuesLoadingTextView.visibility = View.VISIBLE
            binding.issuesLoadingTextView.text = getString(R.string.no_issues)
        }
        //API is only called first time
        else if (DetailsViewModel.issuesList!!.size == 0) {
            binding.issuesLoadingProgressView.visibility = View.VISIBLE
            binding.issuesLoadingTextView.visibility = View.VISIBLE
            //sending api call
            val task = async(Dispatchers.IO) {
                DetailsViewModel.getIssuesList(
                    HomeViewModel.reposList[index].repoOwner,
                    HomeViewModel.reposList[index].repoName
                )
            }

            //getting response
            val result = task.await()
            if (result == null) {
                binding.issuesLoadingTextView.text = getString(R.string.time_out)
                binding.issuesLoadingProgressView.visibility = View.GONE

                //resetting list for recall api req in case on time out
                DetailsViewModel.issuesList = mutableListOf()
                return@launch
            } else {
                binding.issuesLoadingTextView.visibility = View.GONE
                binding.issuesLoadingProgressView.visibility = View.GONE
            }
        }


        binding.issueRv.addItemDecoration(
            DividerItemDecoration(
                context,
                LinearLayout.VERTICAL
            )
        )

        DetailsViewModel.issuesAdapter =
            IssueRecyclerAdapter(DetailsViewModel.issuesList!!)
        binding.issueRv.adapter = DetailsViewModel.issuesAdapter
    }
}