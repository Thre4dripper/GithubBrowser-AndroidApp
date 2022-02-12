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
import com.example.githubbrowser.Adapters.BranchesRecyclerAdapter
import com.example.githubbrowser.R
import com.example.githubbrowser.ViewModels.BranchesViewModel
import com.example.githubbrowser.ViewModels.HomeViewModel
import com.example.githubbrowser.databinding.FragmentBranchesBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class BranchesFragment(private val index: Int) : Fragment() {

    private lateinit var binding: FragmentBranchesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_branches, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.branchRv.layoutManager = LinearLayoutManager(requireContext() as Activity)
        binding.branchRv.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                LinearLayout.VERTICAL
            )
        )
        BranchesViewModel.adapter =
            BranchesRecyclerAdapter(BranchesViewModel.branchesList)

        if (BranchesViewModel.branchesList.size == 0)
            getBranches(index)
    }

    private fun getBranches(index: Int) = CoroutineScope(Dispatchers.Main).launch {
        //sending api call
        val task = async(Dispatchers.IO) {
            BranchesViewModel.getBranchesList(
                HomeViewModel.reposList[index].repoOwner,
                HomeViewModel.reposList[index].repoName
            )
        }
        //getting response
        task.await()

        binding.branchRv.adapter = BranchesViewModel.adapter
        //     BranchFragmentViewModel.adapter.notifyDataSetChanged()
    }
}