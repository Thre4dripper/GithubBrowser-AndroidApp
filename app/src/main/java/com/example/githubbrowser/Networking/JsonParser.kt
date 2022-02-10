package com.example.githubbrowser.Networking

import com.example.githubbrowser.dataModels.RepoItem
import org.json.JSONObject

class JsonParser {
    companion object {
        private var jsonResponse = "{\n" +
                "  \"id\": 75324506,\n" +
                "  \"node_id\": \"MDEwOlJlcG9zaXRvcnk3NTMyNDUwNg==\",\n" +
                "  \"name\": \"material-components\",\n" +
                "  \"full_name\": \"material-components/material-components\",\n" +
                "  \"private\": false,\n" +
                "  \"owner\": {\n" +
                "    \"login\": \"material-components\",\n" +
                "    \"id\": 19478152,\n" +
                "    \"node_id\": \"MDEyOk9yZ2FuaXphdGlvbjE5NDc4MTUy\",\n" +
                "    \"avatar_url\": \"https://avatars.githubusercontent.com/u/19478152?v=4\",\n" +
                "    \"gravatar_id\": \"\",\n" +
                "    \"url\": \"https://api.github.com/users/material-components\",\n" +
                "    \"html_url\": \"https://github.com/material-components\",\n" +
                "    \"followers_url\": \"https://api.github.com/users/material-components/followers\",\n" +
                "    \"following_url\": \"https://api.github.com/users/material-components/following{/other_user}\",\n" +
                "    \"gists_url\": \"https://api.github.com/users/material-components/gists{/gist_id}\",\n" +
                "    \"starred_url\": \"https://api.github.com/users/material-components/starred{/owner}{/repo}\",\n" +
                "    \"subscriptions_url\": \"https://api.github.com/users/material-components/subscriptions\",\n" +
                "    \"organizations_url\": \"https://api.github.com/users/material-components/orgs\",\n" +
                "    \"repos_url\": \"https://api.github.com/users/material-components/repos\",\n" +
                "    \"events_url\": \"https://api.github.com/users/material-components/events{/privacy}\",\n" +
                "    \"received_events_url\": \"https://api.github.com/users/material-components/received_events\",\n" +
                "    \"type\": \"Organization\",\n" +
                "    \"site_admin\": false\n" +
                "  },\n" +
                "  \"html_url\": \"https://github.com/material-components/material-components\",\n" +
                "  \"description\": \"Documentation and policies for Material Components (all platforms)\",\n" +
                "  \"fork\": false,\n" +
                "  \"url\": \"https://api.github.com/repos/material-components/material-components\",\n" +
                "  \"forks_url\": \"https://api.github.com/repos/material-components/material-components/forks\",\n" +
                "  \"keys_url\": \"https://api.github.com/repos/material-components/material-components/keys{/key_id}\",\n" +
                "  \"collaborators_url\": \"https://api.github.com/repos/material-components/material-components/collaborators{/collaborator}\",\n" +
                "  \"teams_url\": \"https://api.github.com/repos/material-components/material-components/teams\",\n" +
                "  \"hooks_url\": \"https://api.github.com/repos/material-components/material-components/hooks\",\n" +
                "  \"issue_events_url\": \"https://api.github.com/repos/material-components/material-components/issues/events{/number}\",\n" +
                "  \"events_url\": \"https://api.github.com/repos/material-components/material-components/events\",\n" +
                "  \"assignees_url\": \"https://api.github.com/repos/material-components/material-components/assignees{/user}\",\n" +
                "  \"branches_url\": \"https://api.github.com/repos/material-components/material-components/branches{/branch}\",\n" +
                "  \"tags_url\": \"https://api.github.com/repos/material-components/material-components/tags\",\n" +
                "  \"blobs_url\": \"https://api.github.com/repos/material-components/material-components/git/blobs{/sha}\",\n" +
                "  \"git_tags_url\": \"https://api.github.com/repos/material-components/material-components/git/tags{/sha}\",\n" +
                "  \"git_refs_url\": \"https://api.github.com/repos/material-components/material-components/git/refs{/sha}\",\n" +
                "  \"trees_url\": \"https://api.github.com/repos/material-components/material-components/git/trees{/sha}\",\n" +
                "  \"statuses_url\": \"https://api.github.com/repos/material-components/material-components/statuses/{sha}\",\n" +
                "  \"languages_url\": \"https://api.github.com/repos/material-components/material-components/languages\",\n" +
                "  \"stargazers_url\": \"https://api.github.com/repos/material-components/material-components/stargazers\",\n" +
                "  \"contributors_url\": \"https://api.github.com/repos/material-components/material-components/contributors\",\n" +
                "  \"subscribers_url\": \"https://api.github.com/repos/material-components/material-components/subscribers\",\n" +
                "  \"subscription_url\": \"https://api.github.com/repos/material-components/material-components/subscription\",\n" +
                "  \"commits_url\": \"https://api.github.com/repos/material-components/material-components/commits{/sha}\",\n" +
                "  \"git_commits_url\": \"https://api.github.com/repos/material-components/material-components/git/commits{/sha}\",\n" +
                "  \"comments_url\": \"https://api.github.com/repos/material-components/material-components/comments{/number}\",\n" +
                "  \"issue_comment_url\": \"https://api.github.com/repos/material-components/material-components/issues/comments{/number}\",\n" +
                "  \"contents_url\": \"https://api.github.com/repos/material-components/material-components/contents/{+path}\",\n" +
                "  \"compare_url\": \"https://api.github.com/repos/material-components/material-components/compare/{base}...{head}\",\n" +
                "  \"merges_url\": \"https://api.github.com/repos/material-components/material-components/merges\",\n" +
                "  \"archive_url\": \"https://api.github.com/repos/material-components/material-components/{archive_format}{/ref}\",\n" +
                "  \"downloads_url\": \"https://api.github.com/repos/material-components/material-components/downloads\",\n" +
                "  \"issues_url\": \"https://api.github.com/repos/material-components/material-components/issues{/number}\",\n" +
                "  \"pulls_url\": \"https://api.github.com/repos/material-components/material-components/pulls{/number}\",\n" +
                "  \"milestones_url\": \"https://api.github.com/repos/material-components/material-components/milestones{/number}\",\n" +
                "  \"notifications_url\": \"https://api.github.com/repos/material-components/material-components/notifications{?since,all,participating}\",\n" +
                "  \"labels_url\": \"https://api.github.com/repos/material-components/material-components/labels{/name}\",\n" +
                "  \"releases_url\": \"https://api.github.com/repos/material-components/material-components/releases{/id}\",\n" +
                "  \"deployments_url\": \"https://api.github.com/repos/material-components/material-components/deployments\",\n" +
                "  \"created_at\": \"2016-12-01T19:17:49Z\",\n" +
                "  \"updated_at\": \"2022-02-08T01:13:02Z\",\n" +
                "  \"pushed_at\": \"2021-12-21T19:48:59Z\",\n" +
                "  \"git_url\": \"git://github.com/material-components/material-components.git\",\n" +
                "  \"ssh_url\": \"git@github.com:material-components/material-components.git\",\n" +
                "  \"clone_url\": \"https://github.com/material-components/material-components.git\",\n" +
                "  \"svn_url\": \"https://github.com/material-components/material-components\",\n" +
                "  \"homepage\": \"\",\n" +
                "  \"size\": 42,\n" +
                "  \"stargazers_count\": 940,\n" +
                "  \"watchers_count\": 940,\n" +
                "  \"language\": null,\n" +
                "  \"has_issues\": true,\n" +
                "  \"has_projects\": false,\n" +
                "  \"has_downloads\": false,\n" +
                "  \"has_wiki\": false,\n" +
                "  \"has_pages\": false,\n" +
                "  \"forks_count\": 154,\n" +
                "  \"mirror_url\": null,\n" +
                "  \"archived\": false,\n" +
                "  \"disabled\": false,\n" +
                "  \"open_issues_count\": 46,\n" +
                "  \"license\": {\n" +
                "    \"key\": \"apache-2.0\",\n" +
                "    \"name\": \"Apache License 2.0\",\n" +
                "    \"spdx_id\": \"Apache-2.0\",\n" +
                "    \"url\": \"https://api.github.com/licenses/apache-2.0\",\n" +
                "    \"node_id\": \"MDc6TGljZW5zZTI=\"\n" +
                "  },\n" +
                "  \"allow_forking\": true,\n" +
                "  \"is_template\": false,\n" +
                "  \"topics\": [\n" +
                "    \"material\",\n" +
                "    \"material-components\",\n" +
                "    \"material-design\"\n" +
                "  ],\n" +
                "  \"visibility\": \"public\",\n" +
                "  \"forks\": 154,\n" +
                "  \"open_issues\": 46,\n" +
                "  \"watchers\": 940,\n" +
                "  \"default_branch\": \"develop\",\n" +
                "  \"temp_clone_token\": null,\n" +
                "  \"organization\": {\n" +
                "    \"login\": \"material-components\",\n" +
                "    \"id\": 19478152,\n" +
                "    \"node_id\": \"MDEyOk9yZ2FuaXphdGlvbjE5NDc4MTUy\",\n" +
                "    \"avatar_url\": \"https://avatars.githubusercontent.com/u/19478152?v=4\",\n" +
                "    \"gravatar_id\": \"\",\n" +
                "    \"url\": \"https://api.github.com/users/material-components\",\n" +
                "    \"html_url\": \"https://github.com/material-components\",\n" +
                "    \"followers_url\": \"https://api.github.com/users/material-components/followers\",\n" +
                "    \"following_url\": \"https://api.github.com/users/material-components/following{/other_user}\",\n" +
                "    \"gists_url\": \"https://api.github.com/users/material-components/gists{/gist_id}\",\n" +
                "    \"starred_url\": \"https://api.github.com/users/material-components/starred{/owner}{/repo}\",\n" +
                "    \"subscriptions_url\": \"https://api.github.com/users/material-components/subscriptions\",\n" +
                "    \"organizations_url\": \"https://api.github.com/users/material-components/orgs\",\n" +
                "    \"repos_url\": \"https://api.github.com/users/material-components/repos\",\n" +
                "    \"events_url\": \"https://api.github.com/users/material-components/events{/privacy}\",\n" +
                "    \"received_events_url\": \"https://api.github.com/users/material-components/received_events\",\n" +
                "    \"type\": \"Organization\",\n" +
                "    \"site_admin\": false\n" +
                "  },\n" +
                "  \"network_count\": 154,\n" +
                "  \"subscribers_count\": 106\n" +
                "}"


        fun getRepoDetails(): RepoItem {
            val rootObject = JSONObject(jsonResponse)

            val repoName = rootObject.getString("name")
            val repoDesc = rootObject.getString("description")
            val avatar = rootObject.getJSONObject("owner").getString("avatar_url")

            return RepoItem(repoName, repoDesc, avatar)
        }
    }

}