package edu.singaporetech.githubstar

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GithubApi {
    private const val BASE_URL = "https://api.github.com/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: GithubService = retrofit.create(GithubService::class.java)
}