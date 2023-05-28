package edu.singaporetech.githubstar

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RepoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(repo: Repo)

    @Query("SELECT * FROM repo WHERE name = :name")
    suspend fun getByName(name: String): Repo?

    @Query("SELECT * FROM repo")
    fun getAll(): Flow<List<Repo>>
}