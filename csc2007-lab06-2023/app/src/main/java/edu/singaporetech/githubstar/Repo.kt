package edu.singaporetech.githubstar

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repo")
data class Repo(
    @PrimaryKey val id: Int,
    val name: String,
    val stars: Int,
)