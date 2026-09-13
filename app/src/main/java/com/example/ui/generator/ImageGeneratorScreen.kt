package com.example.ui.generator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.domain.model.GeneratedPrompt
import com.example.domain.model.ImagePromptConfig
import com.example.ui.components.AppTopBar
import com.example.ui.components.ChipSelector
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ImageGeneratorScreen(
    templateId: String?,
    viewModel: GeneratorViewModel,
    onNavigateBack: () -> Unit,
    onGenerated: (GeneratedPrompt) -> Unit,
    modifier: Modifier = Modifier
) {
    val template = remember(templateId) { viewModel.getTemplateById(templateId) }

    var ideaText by remember {
        mutableStateOf(template?.exampleInput ?: "")
    }
    var selectedStyle by remember {
        mutableStateOf(template?.defaultOptions?.get("style") ?: "Cinematic")
    }
    var selectedLighting by remember {
        mutableStateOf(template?.defaultOptions?.get("lighting") ?: "Golden Hour")
    }
    var selectedCamera by remember {
        mutableStateOf(template?.defaultOptions?.get("camera") ?: "Medium Shot")
    }
    var selectedQuality by remember {
        mutableStateOf(template?.defaultOptions?.get("quality") ?: "Ultra Detailed")
    }
    var selectedAspectRatio by remember {
        mutableStateOf(template?.defaultOptions?.get("aspectRatio") ?: "16:9")
    }

    val isGenerating by viewModel.isGenerating.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(viewModel) {
        viewModel.events.collectLatest { event ->
            when (event) {
                is GeneratorEvent.PromptGenerated -> onGenerated(event.generatedPrompt)
                is GeneratorEvent.ShowMessage -> snackbarHostState.showSnackbar(event.message)
            }
        }
    }

    val styles = listOf(
        "Realistic", "Cinematic", "Anime", "3D", "Fantasy",
        "Luxury", "Minimalist", "Product Photography", "Fashion", "Editorial"
    )
    val lightings = listOf(
        "Natural Light", "Golden Hour", "Soft Light", "Studio Light",
        "Dramatic Light", "Neon Light", "Moody Light"
    )
    val cameras = listOf(
        "Close-up", "Medium Shot", "Wide Shot", "Low Angle",
        "High Angle", "Eye Level", "Drone Shot"
    )
    val qualities = listOf("Standard", "High Quality", "Ultra Detailed")
    val aspectRatios = listOf("1:1", "4:5", "16:9", "9:16")

    Scaffold(
        modifier = modifier.fillMaxSize().testTag("image_generator_screen"),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            AppTopBar(
                title = "AI Image Prompt",
                subtitle = "Design photorealistic & artistic prompts",
                onBackClick = onNavigateBack
            )
        },
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Button(
                    onClick = {
                        viewModel.generateImagePrompt(
                            ImagePromptConfig(
                                idea = ideaText,
                                style = selectedStyle,
                                lighting = selectedLighting,
                                camera = selectedCamera,
                                quality = selectedQuality,
                                aspectRatio = selectedAspectRatio
                            )
                        )
                    },
                    enabled = !isGenerating,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp)
                        .testTag("generate_image_prompt_button"),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    if (isGenerating) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(22.dp),
                            color = MaterialTheme.colorScheme.onPrimary,
                            strokeWidth = 2.dp
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Constructing Prompt...")
                    } else {
                        Icon(
                            imageVector = Icons.Default.AutoAwesome,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "✨ Generate Prompt",
                            style = MaterialTheme.typography.labelLarge.copy(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // Idea Input
            item {
                Column {
                    Text(
                        text = "What do you want to create?",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = ideaText,
                        onValueChange = { ideaText = it },
                        placeholder = {
                            Text("e.g. A young man standing beside a river at sunset")
                        },
                        minLines = 3,
                        maxLines = 5,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("image_idea_input"),
                        shape = RoundedCornerShape(16.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
                            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.25f),
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
                        )
                    )
                }
            }

            // Options
            item {
                ChipSelector(
                    title = "Visual Style",
                    options = styles,
                    selectedOption = selectedStyle,
                    onOptionSelected = { selectedStyle = it },
                    testTagPrefix = "style"
                )
            }

            item {
                ChipSelector(
                    title = "Lighting & Atmosphere",
                    options = lightings,
                    selectedOption = selectedLighting,
                    onOptionSelected = { selectedLighting = it },
                    testTagPrefix = "lighting"
                )
            }

            item {
                ChipSelector(
                    title = "Camera Angle & Shot Type",
                    options = cameras,
                    selectedOption = selectedCamera,
                    onOptionSelected = { selectedCamera = it },
                    testTagPrefix = "camera"
                )
            }

            item {
                ChipSelector(
                    title = "Rendering Quality",
                    options = qualities,
                    selectedOption = selectedQuality,
                    onOptionSelected = { selectedQuality = it },
                    testTagPrefix = "quality"
                )
            }

            item {
                ChipSelector(
                    title = "Aspect Ratio",
                    options = aspectRatios,
                    selectedOption = selectedAspectRatio,
                    onOptionSelected = { selectedAspectRatio = it },
                    testTagPrefix = "ar"
                )
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}
