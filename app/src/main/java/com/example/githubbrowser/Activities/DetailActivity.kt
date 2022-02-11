package com.example.githubbrowser.Activities

import android.os.Bundle
import android.text.Html
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.githubbrowser.R
import com.example.githubbrowser.ViewModels.HomeViewModel
import com.example.githubbrowser.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        binding.backButtonDetails.setOnClickListener {
            super.onBackPressed()
        }

        val repoCardIndex: Int = intent.getIntExtra("repo.card.onclick", -1)
        setDetails(repoCardIndex)
    }

    /**================================== METHOD FOR SETTING REPO NAME AND DESC =========================================**/
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
    }
}