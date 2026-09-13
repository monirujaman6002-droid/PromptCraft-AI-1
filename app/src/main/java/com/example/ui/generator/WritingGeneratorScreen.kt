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
import com.example.domain.model.WritingPromptConfig
import com.example.ui.components.AppTopBar
import com.example.ui.components.ChipSelector
import kotlinx.coroutines.flow.collectLatest

@Composable
fun WritingGeneratorScreen(
    templateId: String?,
    viewModel: GeneratorViewModel,
    onNavigateBack: () -> Unit,
    onGenerated: (GeneratedPrompt) -> Unit,
    modifier: Modifier = Modifier
) {
    val template = remember(templateId) { viewModel.getTemplateById(templateId) }

    var topicText by remember {
        mutableStateOf(template?.exampleInput ?: "")
    }
    var selectedCategory by remember {
        mutableStateOf(template?.subCategory ?: "Blog")
    }
    var selectedTone by remember {
        mutableStateOf(template?.defaultOptions?.get("tone") ?: "Professional")
    }
    var selectedLength by remember {
        mutableStateOf(template?.defaultOptions?.get("length") ?: "Medium")
    }
    var selectedAudience by remember {
        mutableStateOf(template?.defaultOptions?.get("audience") ?: "General")
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

    val categories = listOf(
        "YouTube Script", "Facebook Post", "Instagram Caption", "Blog",
        "Story", "Email", "Product Description", "SEO Content", "Educational Content", "Creative Writing"
    )
    val tones = listOf("Professional", "Friendly", "Casual", "Persuasive", "Emotional", "Educational", "Funny")
    val lengths = listOf("Short", "Medium", "Long")
    val audiences = listOf("General", "Students", "Business", "Professionals", "Social Media", "Customers")

    Scaffold(
        modifier = modifier.fillMaxSize().testTag("writing_generator_screen"),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            AppTopBar(
                title = "AI Writing Prompt",
                subtitle = "Generate structured briefs for copywriting & scripts",
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
                        viewModel.generateWritingPrompt(
                            WritingPromptConfig(
                                category = selectedCategory,
                                topic = topicText,
                                tone = selectedTone,
                                length = selectedLength,
                                audience = selectedAudience
                            )
                        )
                    },
                    enabled = !isGenerating,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp)
                        .testTag("generate_writing_prompt_button"),
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
                        Text("Drafting Prompt...")
                    } else {
                        Icon(
                            imageVector = Icons.Default.AutoAwesome,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "✨ Generate Writing Prompt",
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
                    title = "Writing Format / Medium",
                    options = categories,
                    selectedOption = selectedCategory,
                    onOptionSelected = { selectedCategory = it },
                    testTagPrefix = "write_cat"
                )
            }

            item {
                Column {
                    Text(
                        text = "Enter your topic or key idea",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = topicText,
                        onValueChange = { topicText = it },
                        placeholder = {
                            Text("e.g. 5 essential strategies to master time management for remote workers")
                        },
                        minLines = 3,
                        maxLines = 5,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("writing_topic_input"),
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
                    title = "Tone of Voice",
                    options = tones,
                    selectedOption = selectedTone,
                    onOptionSelected = { selectedTone = it },
                    testTagPrefix = "write_tone"
                )
            }

            item {
                ChipSelector(
                    title = "Length & Depth",
                    options = lengths,
                    selectedOption = selectedLength,
                    onOptionSelected = { selectedLength = it },
                    testTagPrefix = "write_length"
                )
            }

            item {
                ChipSelector(
                    title = "Target Reader / Audience",
                    options = audiences,
                    selectedOption = selectedAudience,
                    onOptionSelected = { selectedAudience = it },
                    testTagPrefix = "write_aud"
                )
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}
