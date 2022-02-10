package com.example.githubbrowser.Networking

import com.example.githubbrowser.dataModels.RepoItem
import org.json.JSONObject

class JsonParser {
    companion object {

        fun getRepoDetails(jsonResponse: String): RepoItem {
            val rootObject = JSONObject(jsonResponse)

            val repoName = rootObject.getString("name")
            val repoDesc = rootObject.getString("description")
            val avatar = rootObject.getJSONObject("owner").getString("avatar_url")

            return RepoItem(repoName, repoDesc, avatar)
        }
    }

}