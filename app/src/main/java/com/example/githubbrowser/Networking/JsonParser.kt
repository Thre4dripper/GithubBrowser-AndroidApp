package com.example.githubbrowser.Networking

import com.example.githubbrowser.dataModels.RepoItem
import org.json.JSONArray
import org.json.JSONObject

class JsonParser {
    companion object {

        fun getRepoDetails(jsonResponse: String): RepoItem {
            val rootObject = JSONObject(jsonResponse)

            val repoName = rootObject.getString("name")
            val repoDesc = rootObject.getString("description")
            val avatar = rootObject.getJSONObject("owner").getString("avatar_url")
            val issues = rootObject.getInt("open_issues")
            return RepoItem(repoName, repoDesc, avatar, issues)
        }

        fun getBranchesList(jsonResponse: String): List<String> {
            val rootObject = JSONArray(jsonResponse)

            val branchList: MutableList<String> = mutableListOf()

            for (i in 1..rootObject.length()) {
                val branchName = rootObject.getJSONObject(i).getString("name")
                branchList.add(branchName)
            }
            return branchList
        }
    }

}