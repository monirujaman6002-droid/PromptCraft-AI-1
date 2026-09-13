package com.example.ui.legal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.AppTopBar

@Composable
fun PrivacyPolicyScreen(
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.fillMaxSize().testTag("privacy_policy_screen"),
        topBar = {
            AppTopBar(
                title = "Privacy Policy",
                subtitle = "Last updated: September 2026",
                onBackClick = onNavigateBack
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item {
                Text(
                    text = "Privacy Policy for PromptCraft AI",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
            }

            item {
                LegalParagraph(
                    title = "1. Introduction & Overview",
                    body = "PromptCraft AI (\"we\", \"our\", or \"app\"), developed by Monir IT, is an offline-first utility application engineered to help creators formulate structured prompts for creative AI tools. We respect your privacy and are committed to protecting any information handled through your use of the application."
                )
            }

            item {
                LegalParagraph(
                    title = "2. No Sensitive Personal Data Collection",
                    body = "PromptCraft AI does NOT require account creation, registration, or sign-in. We do not collect, harvest, store, or sell any personal identifying information (PII) such as your real name, email address, phone number, contacts, location coordinates, or payment details."
                )
            }

            item {
                LegalParagraph(
                    title = "3. Local Device Storage",
                    body = "All your generated prompts, favorites, history records, and application settings are stored locally on your device using Android Room Database and DataStore. This information never leaves your device unless you explicitly choose to export, copy, or share it via your device's native sharing sheet."
                )
            }

            item {
                LegalParagraph(
                    title = "4. Advertising & Third-Party SDKs",
                    body = "The application integrates Google AdMob to display advertisements (banner, interstitial, and rewarded formats). Google AdMob may use standard advertising identifiers (such as the Android Advertising ID) and anonymous diagnostic telemetry to deliver relevant advertisements in compliance with Google Play Developer Program policies and COPPA regulations."
                )
            }

            item {
                LegalParagraph(
                    title = "5. Independent Tool Notice & Trademarks",
                    body = "PromptCraft AI is an independent development utility. ChatGPT is a registered trademark of OpenAI. Gemini and Google Flow are trademarks of Google LLC. Claude is a trademark of Anthropic PBC. Midjourney is a trademark of Midjourney, Inc. ElevenLabs is a trademark of ElevenLabs, Inc. PromptCraft AI is not affiliated with, sponsored by, endorsed by, or associated with any of these entities."
                )
            }

            item {
                LegalParagraph(
                    title = "6. Contact Us",
                    body = "For any questions or privacy inquiries regarding PromptCraft AI, please reach out to our team at support@monirit.com."
                )
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun TermsScreen(
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.fillMaxSize().testTag("terms_screen"),
        topBar = {
            AppTopBar(
                title = "Terms & Conditions",
                subtitle = "Last updated: September 2026",
                onBackClick = onNavigateBack
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item {
                Text(
                    text = "Terms and Conditions of Use",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
            }

            item {
                LegalParagraph(
                    title = "1. Acceptance of Terms",
                    body = "By downloading, installing, or using PromptCraft AI, you agree to be bound by these Terms and Conditions. If you do not agree, please discontinue use of the application."
                )
            }

            item {
                LegalParagraph(
                    title = "2. License & Intellectual Property",
                    body = "Monir IT grants you a personal, non-exclusive, revocable, non-transferable license to use PromptCraft AI on your personal Android device. All prompts generated by you using the smart engine belong entirely to you for personal or commercial use."
                )
            }

            item {
                LegalParagraph(
                    title = "3. Acceptable Use Policy",
                    body = "You agree not to generate prompts intended to promote hate speech, defamation, unlawful activity, violence, or infringements of third-party intellectual property rights."
                )
            }

            item {
                LegalParagraph(
                    title = "4. Disclaimer of Warranty",
                    body = "PromptCraft AI is provided \"as is\" and \"as available\" without warranties of any kind. AI models evolve continuously; we do not guarantee specific outputs from third-party artificial intelligence engines."
                )
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
private fun LegalParagraph(title: String, body: String) {
    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp
            ),
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = body,
            style = MaterialTheme.typography.bodyMedium.copy(lineHeight = 22.sp),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
