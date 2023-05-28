package edu.singaporetech.githubstar

import kotlinx.coroutines.flow.Flow

class GithubRepository(private val dao: RepoDao, private val service: GithubService) {
    val repos: Flow<List<Repo>> = dao.getAll()

    suspend fun getByName(name: String): Repo? {
        return dao.getByName(name)
    }

    suspend fun search(name: String): Repo? {
        val response = service.searchRepositories("Android", "stars", 50)
        if (response.isSuccessful) {
            val searchResponse = response.body()
            searchResponse?.items?.forEach { repoItem ->
                if (repoItem.name == name) {
                    return Repo(repoItem.id, repoItem.name, repoItem.stargazers_count)
                }
            }
        }
        return null
    }

    suspend fun insert(repo: Repo) {
        dao.insert(repo)
    }
}