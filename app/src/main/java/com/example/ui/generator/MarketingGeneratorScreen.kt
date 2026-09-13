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
import com.example.domain.model.MarketingPromptConfig
import com.example.ui.components.AppTopBar
import com.example.ui.components.ChipSelector
import kotlinx.coroutines.flow.collectLatest

@Composable
fun MarketingGeneratorScreen(
    templateId: String?,
    viewModel: GeneratorViewModel,
    onNavigateBack: () -> Unit,
    onGenerated: (GeneratedPrompt) -> Unit,
    modifier: Modifier = Modifier
) {
    val template = remember(templateId) { viewModel.getTemplateById(templateId) }

    var selectedCategory by remember {
        mutableStateOf(template?.subCategory ?: "Facebook Ad")
    }
    var productName by remember {
        mutableStateOf(template?.exampleInput ?: "")
    }
    var targetAudience by remember {
        mutableStateOf(template?.defaultOptions?.get("targetAudience") ?: "Entrepreneurs and business owners")
    }
    var mainBenefit by remember {
        mutableStateOf(template?.defaultOptions?.get("mainBenefit") ?: "Boost revenue and automate workflow")
    }
    var specialOffer by remember {
        mutableStateOf(template?.defaultOptions?.get("offer") ?: "30% off first 3 months")
    }
    var callToAction by remember {
        mutableStateOf(template?.defaultOptions?.get("cta") ?: "Claim Your Offer")
    }
    var selectedTone by remember {
        mutableStateOf("High Converting & Persuasive")
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
        "Facebook Ad", "Instagram Ad", "YouTube Ad", "Product Advertisement",
        "Sales Copy", "Landing Page", "Promotional Poster", "Promotional Banner", "Business Promotion"
    )
    val tones = listOf("High Converting & Persuasive", "Urgent & Direct", "Friendly & Relatable", "Premium & Exclusive")

    Scaffold(
        modifier = modifier.fillMaxSize().testTag("marketing_generator_screen"),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            AppTopBar(
                title = "Marketing Campaign Prompt",
                subtitle = "High-converting ad copy and direct-response hooks",
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
                        viewModel.generateMarketingPrompt(
                            MarketingPromptConfig(
                                category = selectedCategory,
                                productOrBusiness = productName,
                                targetAudience = targetAudience,
                                mainBenefit = mainBenefit,
                                offer = specialOffer,
                                callToAction = callToAction,
                                tone = selectedTone
                            )
                        )
                    },
                    enabled = !isGenerating,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp)
                        .testTag("generate_marketing_prompt_button"),
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
                        Text("Creating Campaign Prompt...")
                    } else {
                        Icon(
                            imageVector = Icons.Default.AutoAwesome,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "✨ Generate Marketing Prompt",
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
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            item {
                ChipSelector(
                    title = "Campaign Channel / Format",
                    options = categories,
                    selectedOption = selectedCategory,
                    onOptionSelected = { selectedCategory = it },
                    testTagPrefix = "mkt_cat"
                )
            }

            item {
                Column {
                    Text(
                        text = "Product or Business Name *",
                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    OutlinedTextField(
                        value = productName,
                        onValueChange = { productName = it },
                        placeholder = { Text("e.g. Lumina Smart Sleep Tracker") },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("marketing_product_input"),
                        shape = RoundedCornerShape(14.dp)
                    )
                }
            }

            item {
                Column {
                    Text(
                        text = "Target Audience *",
                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    OutlinedTextField(
                        value = targetAudience,
                        onValueChange = { targetAudience = it },
                        placeholder = { Text("e.g. Busy professionals who struggle to get quality sleep") },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("marketing_audience_input"),
                        shape = RoundedCornerShape(14.dp)
                    )
                }
            }

            item {
                Column {
                    Text(
                        text = "Main Transformation or Benefit *",
                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    OutlinedTextField(
                        value = mainBenefit,
                        onValueChange = { mainBenefit = it },
                        placeholder = { Text("e.g. Wake up refreshed with deep restorative sleep in 7 days") },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("marketing_benefit_input"),
                        shape = RoundedCornerShape(14.dp)
                    )
                }
            }

            item {
                Column {
                    Text(
                        text = "Special Offer or Hook (Optional)",
                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    OutlinedTextField(
                        value = specialOffer,
                        onValueChange = { specialOffer = it },
                        placeholder = { Text("e.g. 50% off + 30-Day Money Back Guarantee") },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("marketing_offer_input"),
                        shape = RoundedCornerShape(14.dp)
                    )
                }
            }

            item {
                Column {
                    Text(
                        text = "Call to Action (CTA)",
                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    OutlinedTextField(
                        value = callToAction,
                        onValueChange = { callToAction = it },
                        placeholder = { Text("e.g. Claim Your 50% Discount Now") },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("marketing_cta_input"),
                        shape = RoundedCornerShape(14.dp)
                    )
                }
            }

            item {
                ChipSelector(
                    title = "Marketing Tone",
                    options = tones,
                    selectedOption = selectedTone,
                    onOptionSelected = { selectedTone = it },
                    testTagPrefix = "mkt_tone"
                )
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}
