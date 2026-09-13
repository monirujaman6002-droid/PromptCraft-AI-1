package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.domain.engine.SmartPromptEngine
import com.example.domain.model.ImagePromptConfig
import com.example.domain.model.WritingPromptConfig
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

  @Test
  fun `read string from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("PromptCraft AI", appName)
  }

  @Test
  fun `smart prompt engine generates structured image prompt`() {
    val prompt = SmartPromptEngine.generateImagePrompt(
      ImagePromptConfig(
        idea = "A young Bangladeshi man standing beside a river",
        style = "Cinematic",
        lighting = "Golden Hour",
        camera = "Medium Shot",
        quality = "Ultra Detailed",
        aspectRatio = "9:16"
      )
    )
    assertNotNull(prompt)
    assertTrue(prompt.fullText.contains("Cinematic", ignoreCase = true))
    assertTrue(prompt.fullText.contains("golden hour", ignoreCase = true))
    assertTrue(prompt.fullText.contains("Bangladeshi man", ignoreCase = true))
    assertTrue(prompt.fullText.contains("9:16", ignoreCase = true))
  }

  @Test
  fun `smart prompt engine generates structured writing prompt`() {
    val prompt = SmartPromptEngine.generateWritingPrompt(
      WritingPromptConfig(
        category = "Blog",
        topic = "AI Prompt Engineering Best Practices",
        tone = "Professional",
        length = "Medium",
        audience = "Students"
      )
    )
    assertNotNull(prompt)
    assertTrue(prompt.fullText.contains("AI Prompt Engineering Best Practices"))
    assertTrue(prompt.fullText.contains("Professional", ignoreCase = true))
    assertTrue(prompt.fullText.contains("Blog"))
  }
}
