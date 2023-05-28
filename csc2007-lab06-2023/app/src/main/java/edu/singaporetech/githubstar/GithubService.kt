package edu.singaporetech.githubstar

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

data class RepoListResponse(val items: List<RepoItem>)
data class RepoItem(val id: Int, val name: String, val stargazers_count: Int)

interface GithubService {
    @GET("search/repositories")
    suspend fun searchRepositories(
        @Query("q") query: String,
        @Query("sort") sort: String,
        @Query("per_page") perPage: Int,
    ): Response<RepoListResponse>
}
