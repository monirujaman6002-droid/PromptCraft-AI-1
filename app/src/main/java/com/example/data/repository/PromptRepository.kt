package com.example.data.repository

import com.example.data.local.PromptDao
import com.example.data.local.PromptEntity
import kotlinx.coroutines.flow.Flow

class PromptRepository(private val promptDao: PromptDao) {

    val favorites: Flow<List<PromptEntity>> = promptDao.getFavorites()

    val history: Flow<List<PromptEntity>> = promptDao.getRecentHistory(50)

    fun getPromptById(id: Long): Flow<PromptEntity?> = promptDao.getPromptById(id)

    suspend fun savePrompt(prompt: PromptEntity): Long {
        return promptDao.insertPrompt(prompt)
    }

    suspend fun recordGeneration(category: String, title: String, promptText: String): Long {
        val entity = PromptEntity(
            category = category,
            title = title.ifBlank { "$category Prompt" },
            promptText = promptText,
            createdAt = System.currentTimeMillis(),
            isFavorite = false,
            isHistory = true
        )
        val id = promptDao.insertPrompt(entity)
        promptDao.trimHistory(50)
        return id
    }

    suspend fun toggleFavorite(id: Long, isFavorite: Boolean) {
        promptDao.setFavorite(id, isFavorite)
    }

    suspend fun updatePrompt(prompt: PromptEntity) {
        promptDao.updatePrompt(prompt)
    }

    suspend fun deletePrompt(prompt: PromptEntity) {
        promptDao.deletePrompt(prompt)
    }

    suspend fun deleteById(id: Long) {
        promptDao.deleteById(id)
    }

    suspend fun clearHistory() {
        promptDao.clearHistory()
    }

    fun searchPrompts(query: String): Flow<List<PromptEntity>> {
        return promptDao.searchPrompts(query)
    }
}
