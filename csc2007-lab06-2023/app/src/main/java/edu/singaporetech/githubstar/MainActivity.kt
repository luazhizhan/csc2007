package edu.singaporetech.githubstar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import edu.singaporetech.githubstar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: GithubViewModel by viewModels {
        GithubViewModelFactory(
            GithubRepository(
                GithubDatabase.getInstance(application).dao(),
                GithubApi.service
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = RepoAdapter()
        binding.reporecyclerview.adapter = adapter
        binding.reporecyclerview.layoutManager = LinearLayoutManager(this)

        viewModel.repos.observe(this) { repos ->
            adapter.submitList(repos)
        }

        viewModel.insertStatus.observe(this) { status ->
            Toast.makeText(this, status, Toast.LENGTH_SHORT).show()
        }

        binding.findbutton.setOnClickListener {
            val repoName = binding.inputname.text.toString().trim()
            if (repoName.isNotEmpty()) {
                viewModel.searchAndInsert(repoName)
            } else {
                Toast.makeText(this, "Please enter a repository name.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}