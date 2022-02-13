package com.example.githubbrowser.Networking

import com.example.githubbrowser.dataModels.CommitItem
import com.example.githubbrowser.dataModels.IssueItem
import com.example.githubbrowser.dataModels.RepoItem
import org.json.JSONArray
import org.json.JSONObject

class JsonParser {
    companion object {
        private var databaseIndex = 1

        /**================================== JSON PARSER FOR API RESPONSE OF REPO DETAILS =====================================**/
        fun repoDetailsJsonParser(jsonResponse: String): RepoItem {
            val rootObject = JSONObject(jsonResponse)

            val repoOwner = rootObject.getJSONObject("owner").getString("login")
            val repoName = rootObject.getString("name")
            val repoDesc = rootObject.getString("description")
            val avatar = rootObject.getJSONObject("owner").getString("avatar_url")
            val issues = rootObject.getInt("open_issues")
            return RepoItem(
                databaseIndex++,
                repoOwner,
                repoName,
                repoDesc,
                avatar,
                issues
            )
        }

        /**================================== JSON PARSER FOR API RESPONSE OF BRANCHES LIST ===================================**/
        fun branchesListJsonParser(jsonResponse: String): MutableList<String> {
            val rootObject = JSONArray(jsonResponse)

            val branchList: MutableList<String> = mutableListOf()

            for (i in 0 until rootObject.length()) {
                val branchName = rootObject.getJSONObject(i).getString("name")
                branchList.add(branchName)
            }
            return branchList
        }

        /**================================== JSON PARSER FOR API RESPONSE OF ISSUES LIST ======================================**/
        fun issuesListJsonParser(jsonResponse: String): MutableList<IssueItem> {
            val rootObject = JSONArray(jsonResponse)

            val issuesList: MutableList<IssueItem> = mutableListOf()

            for (i in 0 until rootObject.length()) {
                val issueTitle = rootObject.getJSONObject(i).getString("title")
                val issuerAvatar =
                    rootObject.getJSONObject(i).getJSONObject("user").getString("avatar_url")
                val issuerName =
                    rootObject.getJSONObject(i).getJSONObject("user").getString("login")

                issuesList.add(IssueItem(issueTitle, issuerAvatar, issuerName))
            }
            return issuesList
        }

        /**================================== JSON PARSER FOR API RESPONSE OF COMMITS LIST =====================================**/
        fun commitsListJsonParser(jsonResponse: String): MutableList<CommitItem>? {
            val rootObject = JSONArray(jsonResponse)

            val commitsList: MutableList<CommitItem> = mutableListOf()

            for (i in 0 until rootObject.length()) {

                val commitDate =
                    rootObject.getJSONObject(i).getJSONObject("commit").getJSONObject("committer")
                        .getString("date")
                val shaCode = rootObject.getJSONObject(i).getString("sha").substring(0, 6)
                val commitMessage =
                    rootObject.getJSONObject(i).getJSONObject("commit").getString("message")
                val commitUserName: String
                val commitUserAvatar: String

                var author = rootObject.getJSONObject(i)

                if (!author.isNull("author")) {
                    author = author.getJSONObject("author")
                    commitUserName = author.getString("login")
                    commitUserAvatar = author.getString("avatar_url")
                } else {
                    commitUserAvatar = ""
                    commitUserName =
                        author.getJSONObject("commit").getJSONObject("committer").getString("name")
                }

                commitsList.add(
                    CommitItem(
                        commitDate,
                        shaCode,
                        commitMessage,
                        commitUserAvatar,
                        commitUserName
                    )
                )

                println(commitUserName)
            }
            return commitsList
        }
    }

}