package com.example.ui.generator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ads.AdManager
import com.example.data.local.UserPreferences
import com.example.data.model.PromptTemplate
import com.example.data.repository.PromptRepository
import com.example.data.repository.TemplateRepository
import com.example.domain.engine.SmartPromptEngine
import com.example.domain.model.AiToolPromptConfig
import com.example.domain.model.GeneratedPrompt
import com.example.domain.model.ImagePromptConfig
import com.example.domain.model.MarketingPromptConfig
import com.example.domain.model.VideoPromptConfig
import com.example.domain.model.WritingPromptConfig
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class GeneratorEvent {
    data class PromptGenerated(val generatedPrompt: GeneratedPrompt) : GeneratorEvent()
    data class ShowMessage(val message: String) : GeneratorEvent()
}

class GeneratorViewModel(
    private val promptRepository: PromptRepository,
    private val userPreferences: UserPreferences
) : ViewModel() {

    private val _events = MutableSharedFlow<GeneratorEvent>()
    val events: SharedFlow<GeneratorEvent> = _events.asSharedFlow()

    private val _isGenerating = MutableStateFlow(false)
    val isGenerating: StateFlow<Boolean> = _isGenerating.asStateFlow()

    fun generateImagePrompt(config: ImagePromptConfig) {
        if (config.idea.trim().isEmpty()) {
            viewModelScope.launch {
                _events.emit(GeneratorEvent.ShowMessage("Please enter what you want to create first."))
            }
            return
        }

        viewModelScope.launch {
            _isGenerating.value = true
            val prompt = SmartPromptEngine.generateImagePrompt(config)
            saveAndEmit(prompt)
        }
    }

    fun generateVideoPrompt(config: VideoPromptConfig) {
        if (config.actionIdea.trim().isEmpty()) {
            viewModelScope.launch {
                _events.emit(GeneratorEvent.ShowMessage("Please enter what should happen in the video."))
            }
            return
        }

        viewModelScope.launch {
            _isGenerating.value = true
            val prompt = SmartPromptEngine.generateVideoPrompt(config)
            saveAndEmit(prompt)
        }
    }

    fun generateWritingPrompt(config: WritingPromptConfig) {
        if (config.topic.trim().isEmpty()) {
            viewModelScope.launch {
                _events.emit(GeneratorEvent.ShowMessage("Please enter your writing topic."))
            }
            return
        }

        viewModelScope.launch {
            _isGenerating.value = true
            val prompt = SmartPromptEngine.generateWritingPrompt(config)
            saveAndEmit(prompt)
        }
    }

    fun generateMarketingPrompt(config: MarketingPromptConfig) {
        if (config.productOrBusiness.trim().isEmpty()) {
            viewModelScope.launch {
                _events.emit(GeneratorEvent.ShowMessage("Please enter your product or business name."))
            }
            return
        }

        viewModelScope.launch {
            _isGenerating.value = true
            val prompt = SmartPromptEngine.generateMarketingPrompt(config)
            saveAndEmit(prompt)
        }
    }

    fun generateAiToolPrompt(config: AiToolPromptConfig) {
        if (config.topic.trim().isEmpty()) {
            viewModelScope.launch {
                _events.emit(GeneratorEvent.ShowMessage("Please enter your task or idea."))
            }
            return
        }

        viewModelScope.launch {
            _isGenerating.value = true
            val prompt = SmartPromptEngine.generateAiToolPrompt(config)
            saveAndEmit(prompt)
        }
    }

    private suspend fun saveAndEmit(prompt: GeneratedPrompt) {
        try {
            // Save to Room history
            promptRepository.recordGeneration(
                category = prompt.category,
                title = prompt.title,
                promptText = prompt.fullText
            )
            // Increment count for AdMob interstitial triggers
            userPreferences.incrementGenerationCount()
            AdManager.onPromptGenerated()

            _isGenerating.value = false
            _events.emit(GeneratorEvent.PromptGenerated(prompt))
        } catch (e: Exception) {
            _isGenerating.value = false
            _events.emit(GeneratorEvent.ShowMessage("Error saving prompt: ${e.localizedMessage ?: "Unknown error"}"))
        }
    }

    fun getTemplateById(id: String?): PromptTemplate? {
        if (id.isNullOrBlank()) return null
        return TemplateRepository.getTemplateById(id)
    }
}
