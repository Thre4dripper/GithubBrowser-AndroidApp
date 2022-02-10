package com.example.githubbrowser.Networking

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
        fun checkValidity(owner: String, repoName: String):RepoItem? {

            val response = StringBuilder()
            var url: URL?=null
            try {
                url=URL("$BASE_URL$owner/$repoName")
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
            println(response)
            return JsonParser.getRepoDetails(response.toString())
        }
    }
}