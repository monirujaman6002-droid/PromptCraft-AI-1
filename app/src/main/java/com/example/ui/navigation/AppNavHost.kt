package com.example.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.example.data.local.UserPreferences
import com.example.data.model.PromptCategory
import com.example.data.repository.PromptRepository
import com.example.navigation.Screen
import com.example.ui.components.AppBottomNav
import com.example.ui.explore.ExploreScreen
import com.example.ui.favorites.FavoritesScreen
import com.example.ui.generator.AiToolsGeneratorScreen
import com.example.ui.generator.GeneratorViewModel
import com.example.ui.generator.ImageGeneratorScreen
import com.example.ui.generator.MarketingGeneratorScreen
import com.example.ui.generator.VideoGeneratorScreen
import com.example.ui.generator.WritingGeneratorScreen
import com.example.ui.history.HistoryScreen
import com.example.ui.home.HomeScreen
import com.example.ui.home.HomeViewModel
import com.example.ui.legal.PrivacyPolicyScreen
import com.example.ui.legal.TermsScreen
import com.example.ui.onboarding.OnboardingScreen
import com.example.ui.result.ResultScreen
import com.example.ui.settings.SettingsScreen
import com.example.ui.splash.SplashScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    homeViewModel: HomeViewModel,
    generatorViewModel: GeneratorViewModel,
    promptRepository: PromptRepository,
    userPreferences: UserPreferences,
    isFirstLaunch: Boolean,
    modifier: Modifier = Modifier
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val bottomNavRoutes = listOf(
        Screen.Home.route,
        Screen.Explore.route,
        Screen.Favorites.route,
        Screen.Settings.route
    )
    val showBottomNav = currentRoute in bottomNavRoutes

    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            if (showBottomNav) {
                AppBottomNav(
                    currentRoute = currentRoute,
                    onNavigate = { route ->
                        navController.navigate(route) {
                            popUpTo(Screen.Home.route) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Splash.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Splash.route) {
                SplashScreen(
                    onSplashFinished = {
                        if (isFirstLaunch) {
                            navController.navigate(Screen.Onboarding.route) {
                                popUpTo(Screen.Splash.route) { inclusive = true }
                            }
                        } else {
                            navController.navigate(Screen.Home.route) {
                                popUpTo(Screen.Splash.route) { inclusive = true }
                            }
                        }
                    }
                )
            }

            composable(Screen.Onboarding.route) {
                OnboardingScreen(
                    onGetStarted = {
                        userPreferences.setFirstLaunchCompleted()
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Onboarding.route) { inclusive = true }
                        }
                    }
                )
            }

            composable(Screen.Home.route) {
                HomeScreen(
                    viewModel = homeViewModel,
                    onCategoryClick = { category ->
                        when (category) {
                            PromptCategory.IMAGE -> navController.navigate(Screen.ImageGenerator.createRoute())
                            PromptCategory.VIDEO -> navController.navigate(Screen.VideoGenerator.createRoute())
                            PromptCategory.WRITING -> navController.navigate(Screen.WritingGenerator.createRoute())
                            PromptCategory.MARKETING -> navController.navigate(Screen.MarketingGenerator.createRoute())
                            PromptCategory.AI_TOOLS -> navController.navigate(Screen.AiToolsGenerator.createRoute())
                        }
                    },
                    onTemplateClick = { template ->
                        when (template.category) {
                            PromptCategory.IMAGE -> navController.navigate(Screen.ImageGenerator.createRoute(template.id))
                            PromptCategory.VIDEO -> navController.navigate(Screen.VideoGenerator.createRoute(template.id))
                            PromptCategory.WRITING -> navController.navigate(Screen.WritingGenerator.createRoute(template.id))
                            PromptCategory.MARKETING -> navController.navigate(Screen.MarketingGenerator.createRoute(template.id))
                            PromptCategory.AI_TOOLS -> navController.navigate(Screen.AiToolsGenerator.createRoute(template.targetTool, template.id))
                        }
                    },
                    onNavigateHistory = {
                        navController.navigate(Screen.History.route)
                    }
                )
            }

            composable(Screen.Explore.route) {
                ExploreScreen(
                    onTemplateSelect = { template ->
                        when (template.category) {
                            PromptCategory.IMAGE -> navController.navigate(Screen.ImageGenerator.createRoute(template.id))
                            PromptCategory.VIDEO -> navController.navigate(Screen.VideoGenerator.createRoute(template.id))
                            PromptCategory.WRITING -> navController.navigate(Screen.WritingGenerator.createRoute(template.id))
                            PromptCategory.MARKETING -> navController.navigate(Screen.MarketingGenerator.createRoute(template.id))
                            PromptCategory.AI_TOOLS -> navController.navigate(Screen.AiToolsGenerator.createRoute(template.targetTool, template.id))
                        }
                    },
                    onCategorySelect = { category ->
                        when (category) {
                            PromptCategory.IMAGE -> navController.navigate(Screen.ImageGenerator.createRoute())
                            PromptCategory.VIDEO -> navController.navigate(Screen.VideoGenerator.createRoute())
                            PromptCategory.WRITING -> navController.navigate(Screen.WritingGenerator.createRoute())
                            PromptCategory.MARKETING -> navController.navigate(Screen.MarketingGenerator.createRoute())
                            PromptCategory.AI_TOOLS -> navController.navigate(Screen.AiToolsGenerator.createRoute())
                        }
                    }
                )
            }

            composable(Screen.Favorites.route) {
                FavoritesScreen(
                    promptRepository = promptRepository,
                    onPromptClick = { prompt ->
                        navController.navigate(
                            Screen.Result.createRoute(
                                category = prompt.category,
                                title = prompt.title,
                                promptText = prompt.promptText
                            )
                        )
                    }
                )
            }

            composable(Screen.History.route) {
                HistoryScreen(
                    promptRepository = promptRepository,
                    onPromptClick = { prompt ->
                        navController.navigate(
                            Screen.Result.createRoute(
                                category = prompt.category,
                                title = prompt.title,
                                promptText = prompt.promptText
                            )
                        )
                    },
                    onNavigateBack = { navController.popBackStack() }
                )
            }

            composable(Screen.Settings.route) {
                SettingsScreen(
                    userPreferences = userPreferences,
                    onNavigatePrivacyPolicy = { navController.navigate(Screen.PrivacyPolicy.route) },
                    onNavigateTerms = { navController.navigate(Screen.TermsAndConditions.route) }
                )
            }

            composable(
                route = Screen.ImageGenerator.route,
                arguments = listOf(navArgument("templateId") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                })
            ) { backStackEntry ->
                val templateId = backStackEntry.arguments?.getString("templateId")
                ImageGeneratorScreen(
                    templateId = templateId,
                    viewModel = generatorViewModel,
                    onNavigateBack = { navController.popBackStack() },
                    onGenerated = { generated ->
                        navController.navigate(
                            Screen.Result.createRoute(
                                category = generated.category,
                                title = generated.title,
                                promptText = generated.fullText
                            )
                        )
                    }
                )
            }

            composable(
                route = Screen.VideoGenerator.route,
                arguments = listOf(navArgument("templateId") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                })
            ) { backStackEntry ->
                val templateId = backStackEntry.arguments?.getString("templateId")
                VideoGeneratorScreen(
                    templateId = templateId,
                    viewModel = generatorViewModel,
                    onNavigateBack = { navController.popBackStack() },
                    onGenerated = { generated ->
                        navController.navigate(
                            Screen.Result.createRoute(
                                category = generated.category,
                                title = generated.title,
                                promptText = generated.fullText
                            )
                        )
                    }
                )
            }

            composable(
                route = Screen.WritingGenerator.route,
                arguments = listOf(navArgument("templateId") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                })
            ) { backStackEntry ->
                val templateId = backStackEntry.arguments?.getString("templateId")
                WritingGeneratorScreen(
                    templateId = templateId,
                    viewModel = generatorViewModel,
                    onNavigateBack = { navController.popBackStack() },
                    onGenerated = { generated ->
                        navController.navigate(
                            Screen.Result.createRoute(
                                category = generated.category,
                                title = generated.title,
                                promptText = generated.fullText
                            )
                        )
                    }
                )
            }

            composable(
                route = Screen.MarketingGenerator.route,
                arguments = listOf(navArgument("templateId") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                })
            ) { backStackEntry ->
                val templateId = backStackEntry.arguments?.getString("templateId")
                MarketingGeneratorScreen(
                    templateId = templateId,
                    viewModel = generatorViewModel,
                    onNavigateBack = { navController.popBackStack() },
                    onGenerated = { generated ->
                        navController.navigate(
                            Screen.Result.createRoute(
                                category = generated.category,
                                title = generated.title,
                                promptText = generated.fullText
                            )
                        )
                    }
                )
            }

            composable(
                route = Screen.AiToolsGenerator.route,
                arguments = listOf(
                    navArgument("tool") {
                        type = NavType.StringType
                        nullable = true
                        defaultValue = "ChatGPT"
                    },
                    navArgument("templateId") {
                        type = NavType.StringType
                        nullable = true
                        defaultValue = null
                    }
                )
            ) { backStackEntry ->
                val tool = backStackEntry.arguments?.getString("tool")
                val templateId = backStackEntry.arguments?.getString("templateId")
                AiToolsGeneratorScreen(
                    initialTool = tool,
                    templateId = templateId,
                    viewModel = generatorViewModel,
                    onNavigateBack = { navController.popBackStack() },
                    onGenerated = { generated ->
                        navController.navigate(
                            Screen.Result.createRoute(
                                category = generated.category,
                                title = generated.title,
                                promptText = generated.fullText
                            )
                        )
                    }
                )
            }

            composable(
                route = Screen.Result.route,
                arguments = listOf(
                    navArgument("category") { type = NavType.StringType },
                    navArgument("title") { type = NavType.StringType },
                    navArgument("promptText") { type = NavType.StringType }
                )
            ) { backStackEntry ->
                val rawCategory = backStackEntry.arguments?.getString("category") ?: "Prompt"
                val rawTitle = backStackEntry.arguments?.getString("title") ?: "Generated Prompt"
                val rawPromptText = backStackEntry.arguments?.getString("promptText") ?: ""

                val category = java.net.URLDecoder.decode(rawCategory, "UTF-8")
                val title = java.net.URLDecoder.decode(rawTitle, "UTF-8")
                val promptText = java.net.URLDecoder.decode(rawPromptText, "UTF-8")

                ResultScreen(
                    category = category,
                    initialTitle = title,
                    initialPromptText = promptText,
                    promptRepository = promptRepository,
                    onNavigateBack = { navController.popBackStack() }
                )
            }

            composable(Screen.PrivacyPolicy.route) {
                PrivacyPolicyScreen(onNavigateBack = { navController.popBackStack() })
            }

            composable(Screen.TermsAndConditions.route) {
                TermsScreen(onNavigateBack = { navController.popBackStack() })
            }
        }
    }
}
