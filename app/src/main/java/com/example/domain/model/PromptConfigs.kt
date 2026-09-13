package com.example.domain.model

data class ImagePromptConfig(
    val idea: String,
    val style: String = "Cinematic",
    val lighting: String = "Golden Hour",
    val camera: String = "Medium Shot",
    val quality: String = "Ultra Detailed",
    val aspectRatio: String = "16:9",
    val variation: Int = 0
)

data class VideoPromptConfig(
    val actionIdea: String,
    val videoStyle: String = "Cinematic",
    val cameraMovement: String = "Tracking Shot",
    val duration: String = "10 seconds",
    val lighting: String = "Golden Hour",
    val aspectRatio: String = "16:9",
    val variation: Int = 0
)

data class WritingPromptConfig(
    val category: String = "Blog",
    val topic: String,
    val tone: String = "Professional",
    val length: String = "Medium",
    val audience: String = "General",
    val variation: Int = 0
)

data class MarketingPromptConfig(
    val category: String = "Facebook Ad",
    val productOrBusiness: String,
    val targetAudience: String,
    val mainBenefit: String,
    val offer: String = "",
    val callToAction: String = "Learn More",
    val tone: String = "Persuasive",
    val variation: Int = 0
)

data class AiToolPromptConfig(
    val tool: String,
    val subCategory: String,
    val topic: String,
    val complexity: String = "Advanced",
    val extraOptions: Map<String, String> = emptyMap(),
    val variation: Int = 0
)

data class GeneratedPrompt(
    val title: String,
    val category: String,
    val fullText: String,
    val tags: List<String> = emptyList()
)
