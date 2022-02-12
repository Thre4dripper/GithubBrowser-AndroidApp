package com.example.githubbrowser.Fragments

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubbrowser.Adapters.IssueRecyclerAdapter
import com.example.githubbrowser.R
import com.example.githubbrowser.ViewModels.DetailsViewModel
import com.example.githubbrowser.ViewModels.HomeViewModel
import com.example.githubbrowser.databinding.FragmentIssuesBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class IssuesFragment(private val index: Int) : Fragment() {

    private val TAG = "IssuesFragment"
    private lateinit var binding: FragmentIssuesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_issues, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.issueRv.layoutManager = LinearLayoutManager(requireContext() as Activity)
        getIssues(index)
    }

    private fun getIssues(index: Int) = CoroutineScope(Dispatchers.Main).launch {

        //API is only called first time
        if (DetailsViewModel.issuesList.size == 0) {
            //sending api call
            val task = async(Dispatchers.IO) {
                DetailsViewModel.getIssuesList(
                    HomeViewModel.reposList[index].repoOwner,
                    HomeViewModel.reposList[index].repoName
                )
            }

            //getting response
            task.await()
        }


        binding.issueRv.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                LinearLayout.VERTICAL
            )
        )

        DetailsViewModel.issuesAdapter =
            IssueRecyclerAdapter(DetailsViewModel.issuesList)
        binding.issueRv.adapter = DetailsViewModel.issuesAdapter
    }
}