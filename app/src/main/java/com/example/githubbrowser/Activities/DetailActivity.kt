package com.example.githubbrowser.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import androidx.databinding.DataBindingUtil
import com.example.githubbrowser.R
import com.example.githubbrowser.ViewModels.HomeViewModel
import com.example.githubbrowser.databinding.ActivityAddRepoBinding
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
        setNameAndDesc(repoCardIndex)
    }

    /**================================== METHOD FOR SETTING REPO NAME AND DESC =========================================**/
    private fun setNameAndDesc(index: Int) {
        binding.detailsRepoNameText.text = Html.fromHtml(
            "<b>" + getString(R.string.details_repo_name) + "</b> " +
                    HomeViewModel.reposList[index].repoName
        )

        binding.detailsRepoDescText.text = Html.fromHtml(
            "<b>" + getString(R.string.details_repo_desc) + "</b> " +
                    HomeViewModel.reposList[index].repoDesc
        )
    }
}