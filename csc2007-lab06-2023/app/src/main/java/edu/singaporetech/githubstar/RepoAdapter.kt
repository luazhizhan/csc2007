package edu.singaporetech.githubstar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import edu.singaporetech.githubstar.databinding.ListItemBinding

class RepoAdapter : ListAdapter<Repo, RepoAdapter.RepoViewHolder>(RepoDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(inflater, parent, false)
        return RepoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val repo = getItem(position)
        holder.bind(repo)
    }

    inner class RepoViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(repo: Repo) {
            binding.reponame.text = repo.name
            binding.repostar.text = repo.stars.toString()
        }
    }
}

class RepoDiffCallback : DiffUtil.ItemCallback<Repo>() {
    override fun areItemsTheSame(old: Repo, new: Repo): Boolean {
        return old.id == new.id
    }

    override fun areContentsTheSame(old: Repo, new: Repo): Boolean {
        return old.name == new.name && old.stars == new.stars
    }
}