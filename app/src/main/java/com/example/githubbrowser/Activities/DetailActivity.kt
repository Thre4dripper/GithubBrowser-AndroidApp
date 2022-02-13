package com.example.githubbrowser.Activities

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.text.Html
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.githubbrowser.Fragments.BranchesFragment
import com.example.githubbrowser.Fragments.IssuesFragment
import com.example.githubbrowser.R
import com.example.githubbrowser.ViewModels.DetailsViewModel
import com.example.githubbrowser.ViewModels.HomeViewModel
import com.example.githubbrowser.dataModels.RepoItem
import com.example.githubbrowser.database.RepoDetailsDatabase
import com.example.githubbrowser.database.RepoDetailsEntity
import com.example.githubbrowser.databinding.ActivityDetailBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    private var selectedColor = Color.parseColor("#78909C")
    private var unSelectedColor = Color.WHITE

    /**========================================================== ON CREATE =========================================================**/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        binding.backButtonDetails.setOnClickListener { super.onBackPressed() }

        val repoCardIndex: Int = intent.getIntExtra(MainActivity.REPO_CARD_KEY, -1)
        DetailsViewModel.selectedRepo = repoCardIndex
        setDetails(repoCardIndex)
        setFragments()
    }

    /**================================= METHOD FOR SETTING REPO NAME, DESC AND ISSUES COUNT ==============================**/
    private fun setDetails(index: Int) {
        binding.detailsRepoNameText.text = Html.fromHtml(
            "<b>" + getString(R.string.details_repo_name) + "</b> " +
                    HomeViewModel.reposList[index].repoName
        )

        binding.detailsRepoDescText.text = Html.fromHtml(
            "<b>" + getString(R.string.details_repo_desc) + "</b> " +
                    HomeViewModel.reposList[index].repoDesc
        )

        binding.detailsIssueTextView.text = getString(
            R.string.details_issues_text,
            HomeViewModel.reposList[index].issues.toString()
        )

        binding.deleteRepoButton.setOnClickListener { deleteRepo(index) }
        binding.openRepoLinkButton.setOnClickListener { openRepo(index) }
    }

    /**============================================ METHOD FOR FRAGMENT TRANSACTIONS ========================================**/
    private fun setFragments() {
        var fragment: Fragment = BranchesFragment()
        supportFragmentManager.beginTransaction().replace(R.id.branch_frame_layout, fragment)
            .commit()

        binding.detailsBranchCardView.setBackgroundColor(selectedColor)

        binding.detailsBranchCardView.setOnClickListener {
            fragment = BranchesFragment()
            supportFragmentManager.beginTransaction().replace(R.id.branch_frame_layout, fragment)
                .commit()

            binding.detailsBranchCardView.setBackgroundColor(selectedColor)
            binding.detailsIssuesCardView.setBackgroundColor(unSelectedColor)
            binding.detailsBranchesTextView.setTextColor(Color.WHITE)
            binding.detailsIssueTextView.setTextColor(resources.getColor(R.color.text_color))
        }

        binding.detailsIssuesCardView.setOnClickListener {
            fragment = IssuesFragment()
            supportFragmentManager.beginTransaction().replace(R.id.branch_frame_layout, fragment)
                .commit()

            binding.detailsBranchCardView.setBackgroundColor(unSelectedColor)
            binding.detailsIssuesCardView.setBackgroundColor(selectedColor)
            binding.detailsIssueTextView.setTextColor(Color.WHITE)
            binding.detailsBranchesTextView.setTextColor(resources.getColor(R.color.text_color))
        }
    }

    /**================================================ METHOD FOR DELETING REPO ==================================================**/
    private fun deleteRepo(index: Int) {
        val deletingRepo = HomeViewModel.reposList[index]
        HomeViewModel.reposList.removeAt(index)
        HomeViewModel.adapter.notifyItemRemoved(index)
        HomeViewModel.repoCount.value = HomeViewModel.repoCount.value!! - 1

        lifecycleScope.launch(Dispatchers.IO) {
            deleteRepoFromDatabase(deletingRepo)
        }
        finish()
    }

    /**====================================== METHOD FOR DELETING REPO FROM DATABASE ============================================**/
    private suspend fun deleteRepoFromDatabase(deletingRepo: RepoItem) {
        val repoDetailsEntity = RepoDetailsEntity(
            deletingRepo.id,
            deletingRepo.repoOwner,
            deletingRepo.repoName,
            deletingRepo.repoDesc,
            deletingRepo.imgUrl,
            deletingRepo.issues
        )
        RepoDetailsDatabase.getDatabase(this).entityDao().deleteData(repoDetailsEntity)
    }

    /**=============================================== METHOD FOR OPENING REPO LINK ==============================================**/
    private fun openRepo(index: Int) {
        val github = "https://github.com/"
        val ownerName = HomeViewModel.reposList[index].repoOwner
        val repoName = HomeViewModel.reposList[index].repoName
        val repoUrl = "$github/$ownerName/$repoName"

        val uri = Uri.parse(repoUrl) // missing 'http://' will cause crashed

        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }
}