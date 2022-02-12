package com.example.githubbrowser.Activities

import android.graphics.Color
import android.os.Bundle
import android.text.Html
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.githubbrowser.Fragments.BranchesFragment
import com.example.githubbrowser.Fragments.IssuesFragment
import com.example.githubbrowser.R
import com.example.githubbrowser.ViewModels.HomeViewModel
import com.example.githubbrowser.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    private var selectedColor = Color.parseColor("#78909C")
    private var unSelectedColor = Color.WHITE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        binding.backButtonDetails.setOnClickListener {
            super.onBackPressed()
        }

        val repoCardIndex: Int = intent.getIntExtra("repo.card.onclick", -1)
        setDetails(repoCardIndex)
        setFragments(repoCardIndex)
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

    /**============================================== METHOD FOR FRAGMENT TRANSACTIONS ==========================================**/
    private fun setFragments(index: Int) {
        var fragment: Fragment = BranchesFragment(index)
        supportFragmentManager.beginTransaction().replace(R.id.branch_frame_layout, fragment)
            .commit()

        binding.detailsBranchCardView.setBackgroundColor(selectedColor)

        binding.detailsBranchCardView.setOnClickListener {
            fragment = BranchesFragment(index)
            supportFragmentManager.beginTransaction().replace(R.id.branch_frame_layout, fragment)
                .commit()

            binding.detailsBranchCardView.setBackgroundColor(selectedColor)
            binding.detailsIssuesCardView.setBackgroundColor(unSelectedColor)
            binding.detailsBranchesTextView.setTextColor(Color.WHITE)
            binding.detailsIssueTextView.setTextColor(resources.getColor(R.color.text_color))
        }

        binding.detailsIssuesCardView.setOnClickListener {
            fragment = IssuesFragment(index)
            supportFragmentManager.beginTransaction().replace(R.id.branch_frame_layout, fragment)
                .commit()

            binding.detailsBranchCardView.setBackgroundColor(unSelectedColor)
            binding.detailsIssuesCardView.setBackgroundColor(selectedColor)
            binding.detailsIssueTextView.setTextColor(Color.WHITE)
            binding.detailsBranchesTextView.setTextColor(resources.getColor(R.color.text_color))
        }
    }
}