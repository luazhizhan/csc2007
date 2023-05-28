package edu.singaporetech.githubstar

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class GithubViewModel(private val repository: GithubRepository) : ViewModel() {
    val repos: LiveData<List<Repo>> = repository.repos.asLiveData()

    private val _insertStatus = MutableLiveData<String>()
    val insertStatus: LiveData<String> get() = _insertStatus

    fun searchAndInsert(name: String) = viewModelScope.launch {
        val existingRepo = repository.getByName(name)
        if (existingRepo == null) {
            val searchedRepo = repository.search(name)
            if (searchedRepo != null) {
                repository.insert(searchedRepo)
                _insertStatus.value = "Repository added successfully."
            } else {
                _insertStatus.value = "Repository not found or not among top 50."
            }
        } else {
            _insertStatus.value = "Repository already in the list."
        }
    }
}

class GithubViewModelFactory(private val repository: GithubRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GithubViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GithubViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}