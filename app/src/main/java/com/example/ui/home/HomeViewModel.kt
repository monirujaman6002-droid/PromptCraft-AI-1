package com.example.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ads.AdManager
import com.example.data.local.UserPreferences
import com.example.data.model.PromptCategory
import com.example.data.model.PromptTemplate
import com.example.data.repository.PromptRepository
import com.example.data.repository.TemplateRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class HomeUiState(
    val searchQuery: String = "",
    val trendingTemplates: List<PromptTemplate> = emptyList(),
    val filteredTemplates: List<PromptTemplate> = emptyList(),
    val isSearching: Boolean = false,
    val bonusGenerations: Int = 0
)

class HomeViewModel(
    private val templateRepository: TemplateRepository = TemplateRepository,
    private val promptRepository: PromptRepository,
    private val userPreferences: UserPreferences
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _trending = MutableStateFlow(templateRepository.getTrendingTemplates())

    val uiState: StateFlow<HomeUiState> = combine(
        _searchQuery,
        _trending,
        userPreferences.bonusGenerations
    ) { query, trending, bonus ->
        val isSearching = query.isNotBlank()
        val filtered = if (isSearching) {
            templateRepository.searchTemplates(query)
        } else {
            emptyList()
        }
        HomeUiState(
            searchQuery = query,
            trendingTemplates = trending,
            filteredTemplates = filtered,
            isSearching = isSearching,
            bonusGenerations = bonus
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = HomeUiState(trendingTemplates = templateRepository.getTrendingTemplates())
    )

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
    }

    fun clearSearch() {
        _searchQuery.value = ""
    }

    fun claimBonusGenerations() {
        userPreferences.addBonusGenerations(5)
    }
}
