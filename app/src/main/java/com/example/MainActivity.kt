package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.example.ads.AdManager
import com.example.data.local.AppDatabase
import com.example.data.local.UserPreferences
import com.example.data.repository.PromptRepository
import com.example.ui.generator.GeneratorViewModel
import com.example.ui.home.HomeViewModel
import com.example.ui.navigation.AppNavHost
import com.example.ui.theme.PromptCraftTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Initialize AdManager
        AdManager.initialize(applicationContext)

        // Initialize local Room database and data layer
        val database = AppDatabase.getDatabase(applicationContext)
        val promptRepository = PromptRepository(database.promptDao())
        val userPreferences = UserPreferences(applicationContext)

        val homeViewModel = HomeViewModel(
            promptRepository = promptRepository,
            userPreferences = userPreferences
        )
        val generatorViewModel = GeneratorViewModel(
            promptRepository = promptRepository,
            userPreferences = userPreferences
        )

        setContent {
            val themeMode by userPreferences.themeModeString.collectAsStateWithLifecycle(initialValue = "system")
            val isFirstLaunch by userPreferences.isFirstLaunch.collectAsStateWithLifecycle(initialValue = false)

            val useDarkTheme = when (themeMode) {
                "dark" -> true
                "light" -> false
                else -> isSystemInDarkTheme()
            }

            PromptCraftTheme(darkTheme = useDarkTheme) {
                val navController = rememberNavController()
                AppNavHost(
                    navController = navController,
                    homeViewModel = homeViewModel,
                    generatorViewModel = generatorViewModel,
                    promptRepository = promptRepository,
                    userPreferences = userPreferences,
                    isFirstLaunch = isFirstLaunch
                )
            }
        }
    }
}
