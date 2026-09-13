package com.example.data.model

data class PromptTemplate(
    val id: String,
    val category: PromptCategory,
    val subCategory: String,
    val title: String,
    val description: String,
    val exampleInput: String,
    val defaultOptions: Map<String, String> = emptyMap(),
    val isTrending: Boolean = false,
    val targetTool: String? = null // ChatGPT, Gemini, Claude, Midjourney, Google Flow, ElevenLabs
)
