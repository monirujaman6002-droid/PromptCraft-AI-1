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
import com.example.domain.model.AiToolPromptConfig
import com.example.domain.model.GeneratedPrompt
import com.example.ui.components.AppTopBar
import com.example.ui.components.ChipSelector
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AiToolsGeneratorScreen(
    initialTool: String?,
    templateId: String?,
    viewModel: GeneratorViewModel,
    onNavigateBack: () -> Unit,
    onGenerated: (GeneratedPrompt) -> Unit,
    modifier: Modifier = Modifier
) {
    val template = remember(templateId) { viewModel.getTemplateById(templateId) }

    val tools = listOf("ChatGPT", "Gemini", "Claude", "Midjourney", "Google Flow", "ElevenLabs")

    var selectedTool by remember {
        mutableStateOf(template?.targetTool ?: initialTool ?: "ChatGPT")
    }

    val subCategoriesByTool = remember(selectedTool) {
        when (selectedTool.lowercase()) {
            "chatgpt" -> listOf("Coding", "Business", "Writing", "Research", "Learning")
            "gemini" -> listOf("Research", "Writing", "Analysis", "Creative")
            "claude" -> listOf("Writing", "Analysis", "Reasoning", "Code Review")
            "midjourney" -> listOf("Portrait", "Product", "Architecture", "Fantasy")
            "google flow" -> listOf("Cinematic Video", "Character", "Camera Movement", "Advertisement")
            "elevenlabs" -> listOf("Voiceover", "Narration", "Character Voice", "Advertisement")
            else -> listOf("General", "Advanced", "Creative")
        }
    }

    var selectedSubCategory by remember(selectedTool) {
        mutableStateOf(template?.subCategory ?: subCategoriesByTool.first())
    }

    var topicText by remember {
        mutableStateOf(template?.exampleInput ?: "")
    }

    var selectedComplexity by remember {
        mutableStateOf("Expert")
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

    val complexities = listOf("Standard", "Advanced", "Expert", "Production Grade")

    Scaffold(
        modifier = modifier.fillMaxSize().testTag("ai_tools_generator_screen"),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            AppTopBar(
                title = "AI Tools Optimizer",
                subtitle = "Tailored for $selectedTool architecture",
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
                        viewModel.generateAiToolPrompt(
                            AiToolPromptConfig(
                                tool = selectedTool,
                                subCategory = selectedSubCategory,
                                topic = topicText,
                                complexity = selectedComplexity
                            )
                        )
                    },
                    enabled = !isGenerating,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp)
                        .testTag("generate_ai_tool_prompt_button"),
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
                        Text("Optimizing for $selectedTool...")
                    } else {
                        Icon(
                            imageVector = Icons.Default.AutoAwesome,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "✨ Optimize for $selectedTool",
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
            item {
                ChipSelector(
                    title = "Select AI Platform",
                    options = tools,
                    selectedOption = selectedTool,
                    onOptionSelected = {
                        selectedTool = it
                    },
                    testTagPrefix = "tool_platform"
                )
            }

            item {
                ChipSelector(
                    title = "$selectedTool Domain / Focus",
                    options = subCategoriesByTool,
                    selectedOption = selectedSubCategory,
                    onOptionSelected = { selectedSubCategory = it },
                    testTagPrefix = "tool_focus"
                )
            }

            item {
                Column {
                    Text(
                        text = "Task, Objective, or Core Description",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = topicText,
                        onValueChange = { topicText = it },
                        placeholder = {
                            Text("e.g. Build an asynchronous thread-safe caching service in Kotlin")
                        },
                        minLines = 4,
                        maxLines = 6,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("ai_tool_topic_input"),
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
                    title = "Prompt Complexity & Rigor",
                    options = complexities,
                    selectedOption = selectedComplexity,
                    onOptionSelected = { selectedComplexity = it },
                    testTagPrefix = "tool_complexity"
                )
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}
