package com.example.data.local

import android.content.Context
import android.content.SharedPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

enum class ThemeMode {
    SYSTEM, LIGHT, DARK
}

class UserPreferences(context: Context) {

    private val prefs: SharedPreferences =
        context.applicationContext.getSharedPreferences("promptcraft_prefs", Context.MODE_PRIVATE)

    private val _themeMode = MutableStateFlow(loadThemeMode())
    val themeMode: StateFlow<ThemeMode> = _themeMode.asStateFlow()

    private val _themeModeString = MutableStateFlow(loadThemeMode().name.lowercase())
    val themeModeString: StateFlow<String> = _themeModeString.asStateFlow()

    private val _language = MutableStateFlow(loadLanguage())
    val language: StateFlow<String> = _language.asStateFlow()

    private val _hasSeenOnboarding = MutableStateFlow(prefs.getBoolean(KEY_ONBOARDING, false))
    val hasSeenOnboarding: StateFlow<Boolean> = _hasSeenOnboarding.asStateFlow()

    private val _isFirstLaunch = MutableStateFlow(!prefs.getBoolean(KEY_ONBOARDING, false))
    val isFirstLaunch: StateFlow<Boolean> = _isFirstLaunch.asStateFlow()

    private val _generationCount = MutableStateFlow(prefs.getInt(KEY_GEN_COUNT, 0))
    val generationCount: StateFlow<Int> = _generationCount.asStateFlow()

    private val _bonusGenerations = MutableStateFlow(prefs.getInt(KEY_BONUS_GEN, 0))
    val bonusGenerations: StateFlow<Int> = _bonusGenerations.asStateFlow()

    private fun loadThemeMode(): ThemeMode {
        val name = prefs.getString(KEY_THEME, ThemeMode.SYSTEM.name) ?: ThemeMode.SYSTEM.name
        return try {
            ThemeMode.valueOf(name)
        } catch (_: Exception) {
            ThemeMode.SYSTEM
        }
    }

    private fun loadLanguage(): String {
        return prefs.getString(KEY_LANG, "en") ?: "en"
    }

    fun setThemeMode(mode: ThemeMode) {
        prefs.edit().putString(KEY_THEME, mode.name).apply()
        _themeMode.value = mode
        _themeModeString.value = mode.name.lowercase()
    }

    fun setThemeMode(modeStr: String) {
        val mode = when (modeStr.lowercase()) {
            "dark" -> ThemeMode.DARK
            "light" -> ThemeMode.LIGHT
            else -> ThemeMode.SYSTEM
        }
        setThemeMode(mode)
    }

    fun setLanguage(lang: String) {
        prefs.edit().putString(KEY_LANG, lang).apply()
        _language.value = lang
    }

    fun setFirstLaunchCompleted() {
        prefs.edit().putBoolean(KEY_ONBOARDING, true).apply()
        _hasSeenOnboarding.value = true
        _isFirstLaunch.value = false
    }

    fun incrementGenerationCount(): Int {
        val next = _generationCount.value + 1
        prefs.edit().putInt(KEY_GEN_COUNT, next).apply()
        _generationCount.value = next
        return next
    }

    fun addBonusGenerations(amount: Int = 5) {
        val next = _bonusGenerations.value + amount
        prefs.edit().putInt(KEY_BONUS_GEN, next).apply()
        _bonusGenerations.value = next
    }

    companion object {
        private const val KEY_THEME = "key_theme_mode"
        private const val KEY_LANG = "key_language"
        private const val KEY_ONBOARDING = "key_has_seen_onboarding"
        private const val KEY_GEN_COUNT = "key_gen_count"
        private const val KEY_BONUS_GEN = "key_bonus_gen"

        @Volatile
        private var INSTANCE: UserPreferences? = null

        fun getInstance(context: Context): UserPreferences {
            return INSTANCE ?: synchronized(this) {
                val instance = UserPreferences(context)
                INSTANCE = instance
                instance
            }
        }
    }
}
