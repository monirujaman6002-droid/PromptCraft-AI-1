package com.example.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Onboarding : Screen("onboarding")
    object Home : Screen("home")
    object Explore : Screen("explore")
    object Favorites : Screen("favorites")
    object History : Screen("history")
    object Settings : Screen("settings")

    // Generators
    object ImageGenerator : Screen("generator/image?templateId={templateId}") {
        fun createRoute(templateId: String? = null) = "generator/image?templateId=${templateId ?: ""}"
    }

    object VideoGenerator : Screen("generator/video?templateId={templateId}") {
        fun createRoute(templateId: String? = null) = "generator/video?templateId=${templateId ?: ""}"
    }

    object WritingGenerator : Screen("generator/writing?templateId={templateId}") {
        fun createRoute(templateId: String? = null) = "generator/writing?templateId=${templateId ?: ""}"
    }

    object MarketingGenerator : Screen("generator/marketing?templateId={templateId}") {
        fun createRoute(templateId: String? = null) = "generator/marketing?templateId=${templateId ?: ""}"
    }

    object AiToolsGenerator : Screen("generator/ai_tools?tool={tool}&templateId={templateId}") {
        fun createRoute(tool: String? = null, templateId: String? = null) =
            "generator/ai_tools?tool=${tool ?: "ChatGPT"}&templateId=${templateId ?: ""}"
    }

    // Result Screen
    object Result : Screen("result?category={category}&title={title}&promptText={promptText}") {
        fun createRoute(category: String, title: String, promptText: String): String {
            val encodedCat = java.net.URLEncoder.encode(category, "UTF-8")
            val encodedTitle = java.net.URLEncoder.encode(title, "UTF-8")
            val encodedText = java.net.URLEncoder.encode(promptText, "UTF-8")
            return "result?category=$encodedCat&title=$encodedTitle&promptText=$encodedText"
        }
    }

    // Legal
    object PrivacyPolicy : Screen("privacy_policy")
    object TermsAndConditions : Screen("terms_and_conditions")
}
