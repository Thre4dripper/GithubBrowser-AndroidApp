package com.example.githubbrowser.Networking

import com.example.githubbrowser.dataModels.IssueItem
import com.example.githubbrowser.dataModels.RepoItem
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.Charset


class ApiResponse {

    companion object {
        private const val BASE_URL = "https://api.github.com/repos/"


        fun getRepoInfo(owner: String, repoName: String): RepoItem? {
            val response = getJsonResponse("$BASE_URL$owner/$repoName") ?: return null

            return JsonParser.repoDetailsJsonParser(response.toString())
        }

        fun getBranchesList(owner: String, repoName: String): MutableList<String> {
            val response = getJsonResponse("$BASE_URL$owner/$repoName/branches")

            return JsonParser.branchesListJsonParser(response.toString())
        }

        fun getIssuesList(owner: String, repoName: String): MutableList<IssueItem> {
            val response = getJsonResponse("$BASE_URL$owner/$repoName/issues?state=open&per_page=100000")

            return JsonParser.issuesListJsonParser(response.toString())
        }

        private fun getJsonResponse(apiUrl: String): StringBuilder? {

            val response = StringBuilder()
            var url: URL? = null
            try {
                url = URL(apiUrl)
            } catch (e: Exception) {

            }

            var urlConnection: HttpURLConnection? = null
            var inputStream: InputStream? = null
            val inputStreamReader: InputStreamReader
            val bufferedReader: BufferedReader
            var line: String?
            try {
                urlConnection = url!!.openConnection() as HttpURLConnection
                urlConnection.requestMethod = "GET"
                urlConnection.readTimeout = 10000
                urlConnection.connectTimeout = 15000
                urlConnection.connect()
                inputStream = urlConnection.inputStream
                inputStreamReader = InputStreamReader(inputStream, Charset.forName("UTF-8"))
                bufferedReader = BufferedReader(inputStreamReader)
                line = bufferedReader.readLine()
                while (line != null) {
                    response.append(line)
                    line = bufferedReader.readLine()
                }
            } catch (e: IOException) {
                e.printStackTrace()
                return null
            } finally {
                urlConnection?.disconnect()
                if (inputStream != null) {
                    try {
                        inputStream.close()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }
            return response
        }
    }
}