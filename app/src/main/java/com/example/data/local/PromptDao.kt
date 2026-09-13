package com.example.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface PromptDao {

    @Query("SELECT * FROM prompts WHERE isFavorite = 1 ORDER BY createdAt DESC")
    fun getFavorites(): Flow<List<PromptEntity>>

    @Query("SELECT * FROM prompts WHERE isHistory = 1 ORDER BY createdAt DESC LIMIT :limit")
    fun getRecentHistory(limit: Int = 50): Flow<List<PromptEntity>>

    @Query("SELECT * FROM prompts WHERE id = :id LIMIT 1")
    fun getPromptById(id: Long): Flow<PromptEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPrompt(prompt: PromptEntity): Long

    @Update
    suspend fun updatePrompt(prompt: PromptEntity)

    @Delete
    suspend fun deletePrompt(prompt: PromptEntity)

    @Query("DELETE FROM prompts WHERE id = :id")
    suspend fun deleteById(id: Long)

    @Query("UPDATE prompts SET isFavorite = :isFavorite WHERE id = :id")
    suspend fun setFavorite(id: Long, isFavorite: Boolean)

    @Query("DELETE FROM prompts WHERE isHistory = 1 AND isFavorite = 0")
    suspend fun clearHistory()

    @Query("""
        SELECT * FROM prompts 
        WHERE title LIKE '%' || :query || '%' 
           OR promptText LIKE '%' || :query || '%' 
           OR category LIKE '%' || :query || '%'
        ORDER BY createdAt DESC
    """)
    fun searchPrompts(query: String): Flow<List<PromptEntity>>

    @Query("""
        DELETE FROM prompts 
        WHERE id NOT IN (
            SELECT id FROM prompts ORDER BY createdAt DESC LIMIT :limit
        ) AND isFavorite = 0
    """)
    suspend fun trimHistory(limit: Int = 50)
}
