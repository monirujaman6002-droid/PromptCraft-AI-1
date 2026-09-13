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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.domain.model.GeneratedPrompt
import com.example.domain.model.VideoPromptConfig
import com.example.ui.components.AppTopBar
import com.example.ui.components.ChipSelector
import kotlinx.coroutines.flow.collectLatest

@Composable
fun VideoGeneratorScreen(
    templateId: String?,
    viewModel: GeneratorViewModel,
    onNavigateBack: () -> Unit,
    onGenerated: (GeneratedPrompt) -> Unit,
    modifier: Modifier = Modifier
) {
    val template = remember(templateId) { viewModel.getTemplateById(templateId) }

    var actionText by remember {
        mutableStateOf(template?.exampleInput ?: "")
    }
    var selectedVideoStyle by remember {
        mutableStateOf(template?.defaultOptions?.get("videoStyle") ?: "Cinematic")
    }
    var selectedMovement by remember {
        mutableStateOf(template?.defaultOptions?.get("movement") ?: "Tracking Shot")
    }
    var selectedDuration by remember {
        mutableStateOf(template?.defaultOptions?.get("duration") ?: "10 seconds")
    }
    var selectedLighting by remember {
        mutableStateOf(template?.defaultOptions?.get("lighting") ?: "Golden Hour")
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

    val videoStyles = listOf(
        "Cinematic", "Realistic", "Documentary", "Advertisement",
        "Short Film", "Anime", "Music Video"
    )
    val movements = listOf(
        "Slow Zoom In", "Slow Zoom Out", "Tracking Shot", "Pan Left",
        "Pan Right", "Tilt Up", "Tilt Down", "Handheld", "Drone Shot", "Orbit Shot"
    )
    val durations = listOf("5 seconds", "10 seconds", "15 seconds", "30 seconds")
    val lightings = listOf("Natural", "Golden Hour", "Studio", "Dramatic", "Neon", "Night")
    val aspectRatios = listOf("9:16", "16:9", "1:1")

    Scaffold(
        modifier = modifier.fillMaxSize().testTag("video_generator_screen"),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            AppTopBar(
                title = "AI Video Prompt",
                subtitle = "Generate cinematic motion & camera direction",
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
                        viewModel.generateVideoPrompt(
                            VideoPromptConfig(
                                actionIdea = actionText,
                                videoStyle = selectedVideoStyle,
                                cameraMovement = selectedMovement,
                                duration = selectedDuration,
                                lighting = selectedLighting,
                                aspectRatio = selectedAspectRatio
                            )
                        )
                    },
                    enabled = !isGenerating,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp)
                        .testTag("generate_video_prompt_button"),
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
                        Text("Composing Video Prompt...")
                    } else {
                        Icon(
                            imageVector = Icons.Default.AutoAwesome,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "✨ Generate Video Prompt",
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
            // Action / Scenario Input
            item {
                Column {
                    Text(
                        text = "What should happen in the video?",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = actionText,
                        onValueChange = { actionText = it },
                        placeholder = {
                            Text("e.g. A lone astronaut discovering a glowing crystal obelisk on Mars at twilight")
                        },
                        minLines = 3,
                        maxLines = 5,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("video_action_input"),
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

            item {
                ChipSelector(
                    title = "Video Aesthetic Style",
                    options = videoStyles,
                    selectedOption = selectedVideoStyle,
                    onOptionSelected = { selectedVideoStyle = it },
                    testTagPrefix = "vid_style"
                )
            }

            item {
                ChipSelector(
                    title = "Camera Movement",
                    options = movements,
                    selectedOption = selectedMovement,
                    onOptionSelected = { selectedMovement = it },
                    testTagPrefix = "vid_movement"
                )
            }

            item {
                ChipSelector(
                    title = "Scene Duration",
                    options = durations,
                    selectedOption = selectedDuration,
                    onOptionSelected = { selectedDuration = it },
                    testTagPrefix = "vid_duration"
                )
            }

            item {
                ChipSelector(
                    title = "Lighting Environment",
                    options = lightings,
                    selectedOption = selectedLighting,
                    onOptionSelected = { selectedLighting = it },
                    testTagPrefix = "vid_lighting"
                )
            }

            item {
                ChipSelector(
                    title = "Aspect Ratio",
                    options = aspectRatios,
                    selectedOption = selectedAspectRatio,
                    onOptionSelected = { selectedAspectRatio = it },
                    testTagPrefix = "vid_ar"
                )
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}
