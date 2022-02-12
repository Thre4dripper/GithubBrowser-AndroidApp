package com.example.githubbrowser.Fragments

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
import com.example.githubbrowser.ViewModels.BranchFragmentViewModel
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

        getBranches(index)
    }

    private fun getBranches(index: Int) = CoroutineScope(Dispatchers.Main).launch {
        //sending api call
        val task = async(Dispatchers.IO) {
            BranchFragmentViewModel.getBranchesList(
                HomeViewModel.reposList[index].repoOwner,
                HomeViewModel.reposList[index].repoName
            )
        }
        //getting response
        task.await()

        binding.branchRv.layoutManager = LinearLayoutManager(requireContext())
        binding.branchRv.addItemDecoration(DividerItemDecoration(requireContext(),LinearLayout.VERTICAL))
        BranchFragmentViewModel.adapter =
            BranchesRecyclerAdapter(BranchFragmentViewModel.branchesList)
        binding.branchRv.adapter = BranchFragmentViewModel.adapter
    }
}