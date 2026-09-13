package com.example.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

// Primary brand colors
val IndigoPrimaryDark = Color(0xFF818CF8)
val IndigoPrimaryLight = Color(0xFF4F46E5)
val CyanAccentDark = Color(0xFF38BDF8)
val CyanAccentLight = Color(0xFF0284C7)
val PurpleAccentDark = Color(0xFFC084FC)
val PurpleAccentLight = Color(0xFF9333EA)

// Background & Surface - Dark Mode (Futuristic Obsidian / Slate)
val DarkBackground = Color(0xFF090D16)
val DarkSurface = Color(0xFF111827)
val DarkSurfaceElevated = Color(0xFF1A2234)
val DarkSurfaceVariant = Color(0xFF243048)
val DarkOutline = Color(0xFF334155)
val DarkOnBackground = Color(0xFFF1F5F9)
val DarkOnSurface = Color(0xFFE2E8F0)
val DarkOnSurfaceVariant = Color(0xFF94A3B8)

// Background & Surface - Light Mode
val LightBackground = Color(0xFFF8FAFC)
val LightSurface = Color(0xFFFFFFFF)
val LightSurfaceElevated = Color(0xFFF1F5F9)
val LightSurfaceVariant = Color(0xFFE2E8F0)
val LightOutline = Color(0xFFCBD5E1)
val LightOnBackground = Color(0xFF0F172A)
val LightOnSurface = Color(0xFF1E293B)
val LightOnSurfaceVariant = Color(0xFF64748B)

// Accent Gradients
val PrimaryGradient = Brush.linearGradient(
    colors = listOf(Color(0xFF6366F1), Color(0xFF8B5CF6), Color(0xFF06B6D4))
)

val GlowGradient = Brush.linearGradient(
    colors = listOf(Color(0xFF38BDF8).copy(alpha = 0.25f), Color(0xFFA855F7).copy(alpha = 0.25f))
)

val CardGlowGradient = Brush.linearGradient(
    colors = listOf(Color(0xFF4F46E5).copy(alpha = 0.15f), Color(0xFF06B6D4).copy(alpha = 0.10f))
)

val AccentGold = Color(0xFFF59E0B)
val SuccessGreen = Color(0xFF10B981)
val ErrorRed = Color(0xFFEF4444)
