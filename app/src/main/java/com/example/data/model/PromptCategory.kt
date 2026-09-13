package com.example.data.model

enum class PromptCategory(
    val id: String,
    val title: String,
    val subtitle: String,
    val shortName: String
) {
    IMAGE(
        id = "image",
        title = "Image Prompts",
        subtitle = "Create professional AI image prompts.",
        shortName = "Image"
    ),
    VIDEO(
        id = "video",
        title = "Video Prompts",
        subtitle = "Create cinematic AI video prompts.",
        shortName = "Video"
    ),
    WRITING(
        id = "writing",
        title = "Writing Prompts",
        subtitle = "Create high-quality writing prompts.",
        shortName = "Writing"
    ),
    MARKETING(
        id = "marketing",
        title = "Marketing Prompts",
        subtitle = "Create advertisements and marketing content.",
        shortName = "Marketing"
    ),
    AI_TOOLS(
        id = "ai_tools",
        title = "AI Tools",
        subtitle = "Prompts optimized for popular AI tools.",
        shortName = "AI Tools"
    );

    companion object {
        fun fromId(id: String?): PromptCategory {
            return entries.find { it.id.equals(id, ignoreCase = true) } ?: IMAGE
        }
    }
}
