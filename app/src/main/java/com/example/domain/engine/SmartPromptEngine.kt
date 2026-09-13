package com.example.domain.engine

import com.example.domain.model.AiToolPromptConfig
import com.example.domain.model.GeneratedPrompt
import com.example.domain.model.ImagePromptConfig
import com.example.domain.model.MarketingPromptConfig
import com.example.domain.model.VideoPromptConfig
import com.example.domain.model.WritingPromptConfig

object SmartPromptEngine {

    fun generateImagePrompt(config: ImagePromptConfig): GeneratedPrompt {
        val subject = config.idea.trim().ifBlank { "A cinematic scene with atmospheric lighting" }
        val style = config.style
        val lighting = config.lighting
        val camera = config.camera
        val quality = config.quality
        val ar = config.aspectRatio

        val qualityPhrase = when (quality.lowercase()) {
            "ultra detailed" -> "8k resolution, ultra-detailed textures, masterwork quality, octane render aesthetics"
            "high quality" -> "high resolution, sharp focus, refined details, crisp photographic elements"
            else -> "clean composition, balanced contrast, smooth rendering"
        }

        val lightingPhrase = when (lighting.lowercase()) {
            "golden hour" -> "bathed in warm golden hour sunlight, soft sun flares, long gentle shadows"
            "natural light" -> "illuminated by soft natural daylight, true-to-life color temperature"
            "soft light" -> "diffused softbox lighting, gentle gradients, no harsh shadows"
            "studio light" -> "professional high-key studio lighting with rim light separation"
            "dramatic light" -> "chiaroscuro dramatic lighting, deep evocative shadows, moody highlights"
            "neon light" -> "vibrant neon cyberpunk lighting, electric cyan and magenta reflections"
            "moody light" -> "dim atmospheric ambient light, fog haze, evocative cinematic gloom"
            else -> "$lighting illumination"
        }

        val cameraPhrase = when (camera.lowercase()) {
            "close-up" -> "macro close-up framing, shallow depth of field, sharp subject focus, blurred background bokeh"
            "medium shot" -> "balanced medium-shot framing, waist-up composition, natural subject separation"
            "wide shot" -> "expansive wide-angle lens view, deep perspective, environmental grandeur"
            "low angle" -> "imposing low-angle perspective, heroic framing, dramatic scale"
            "high angle" -> "expressive high-angle overhead perspective looking down"
            "eye level" -> "grounded eye-level perspective, direct intimate connection"
            "drone shot" -> "sweeping aerial drone photography, bird's-eye vantage point, vast panoramic scale"
            else -> "$camera framing"
        }

        val styleModifier = when (style.lowercase()) {
            "realistic" -> "photorealistic capture, authentic skin texture, realistic optical aberrations"
            "cinematic" -> "cinematic film still, 35mm film grain, anamorphic lens flare, color-graded"
            "anime" -> "vibrant modern anime style, clean cel-shading, dynamic line art, Makoto Shinkai inspired sky"
            "3d" -> "stylized 3D digital art, Unreal Engine 5 render, raytracing, smooth subsurface scattering"
            "fantasy" -> "epic fantasy concept art, mystical glowing accents, whimsical world-building"
            "luxury" -> "prestigious luxury editorial look, elegant minimalist accents, pristine materials"
            "minimalist" -> "minimalist contemporary composition, ample negative space, purposeful simplicity"
            "product photography" -> "commercial product photography, reflective acrylic surface, flawless presentation"
            "fashion" -> "high-fashion editorial photography, haute couture styling, artistic model pose"
            "editorial" -> "award-winning magazine cover aesthetic, graphic typography balance, bold tonality"
            else -> "$style visual aesthetic"
        }

        val variations = listOf(
            "Create a $style, $quality visual of $subject. Lighting: $lightingPhrase. Camera & Framing: $cameraPhrase. Visual details: $styleModifier, $qualityPhrase. Aspect Ratio: $ar. Professional composition, color graded.",
            "A masterfully crafted $style composition depicting $subject. Illuminated with $lightingPhrase. Shot using $cameraPhrase. Characterized by $styleModifier, $qualityPhrase. Finished with a $ar aspect ratio.",
            "Award-winning $style shot: $subject, framed via $cameraPhrase. Features $lightingPhrase, realistic environmental interplay, $styleModifier, and $qualityPhrase. Aspect ratio: $ar."
        )

        val text = variations[config.variation % variations.size]
        return GeneratedPrompt(
            title = "AI Image Prompt • $style",
            category = "Image",
            fullText = text,
            tags = listOf(style, lighting, camera, ar)
        )
    }

    fun generateVideoPrompt(config: VideoPromptConfig): GeneratedPrompt {
        val action = config.actionIdea.trim().ifBlank { "A cinematic sequence unfolding with continuous motion" }
        val style = config.videoStyle
        val movement = config.cameraMovement
        val duration = config.duration
        val lighting = config.lighting
        val ar = config.aspectRatio

        val cameraDirectives = when (movement.lowercase()) {
            "slow zoom in" -> "Camera executes a steady, hypnotic slow zoom pushing in towards the subject to build tension and intimacy."
            "slow zoom out" -> "Camera begins tightly framed on the core subject before smoothly pulling back to reveal the expansive surrounding environment."
            "tracking shot" -> "Smooth dolly tracking shot moving alongside the subject at matching velocity with fluid optical stability."
            "pan left" -> "Smooth horizontal pan sweeping leftward to follow motion across the widescreen frame."
            "pan right" -> "Graceful rightward panning movement establishing spatial awareness and reveal."
            "tilt up" -> "Dramatic upward vertical camera tilt sweeping from ground elements to the sky."
            "tilt down" -> "Contemplative downward camera tilt revealing key details below."
            "handheld" -> "Subtle documentary-style handheld camera sway adding kinetic realism, urgency, and raw human presence."
            "drone shot" -> "High-altitude cinematic drone fly-through gliding forward gracefully with gentle parallax."
            "orbit shot" -> "360-degree rotational orbital camera movement circling the subject with parallax depth."
            else -> "Fluid camera movement with $movement."
        }

        val lightingMood = when (lighting.lowercase()) {
            "golden hour" -> "Sunlight casts warm amber beams through atmospheric haze, soft rim light highlighting edges."
            "neon" -> "Vibrant electric neon lights pulsing in the background, creating vivid reflections on reflective surfaces."
            "dramatic" -> "High-contrast chiaroscuro lighting, deep mysterious shadows with intentional spotlight accents."
            "studio" -> "Even commercial studio lighting with soft fill and crystal-clear edge separation."
            "night" -> "Midnight ambiance with subtle ambient city glow and volumetric moonlight shafts."
            else -> "Natural $lighting lighting environment."
        }

        val variations = listOf(
            """
            Cinematic AI Video Prompt ($duration, Aspect Ratio $ar):
            
            [SCENE & ACTION]:
            $action
            
            [STYLE & AESTHETIC]:
            $style visual treatment with filmic color grading, realistic physics simulation, and atmospheric volume.
            
            [CAMERA & MOTION]:
            $cameraDirectives Frame rate: 24fps cinematic shutter. Composition is tailored for a $ar screen.
            
            [LIGHTING & ATMOSPHERE]:
            $lightingMood
            
            [TECHNICAL PARAMETERS]:
            Duration: $duration | Aspect Ratio: $ar | Motion smoothness: High | Temporal consistency: Ultra-high.
            """.trimIndent(),
            """
            Create a high-fidelity $style video scene ($duration):
            Action Sequence: $action.
            Cinematography: $cameraDirectives
            Atmosphere & Lighting: $lightingMood
            Visual Style: $style motion aesthetics, hyper-realistic physics, volumetric depth, film grain, and 24fps shutter cadence. Formatted in $ar aspect ratio for maximum visual impact.
            """.trimIndent()
        )

        val text = variations[config.variation % variations.size]
        return GeneratedPrompt(
            title = "Cinematic Video • $style",
            category = "Video",
            fullText = text,
            tags = listOf(style, movement, duration, ar)
        )
    }

    fun generateWritingPrompt(config: WritingPromptConfig): GeneratedPrompt {
        val topic = config.topic.trim().ifBlank { "Modern creative strategies and insights" }
        val category = config.category
        val tone = config.tone
        val length = config.length
        val audience = config.audience

        val lengthGuideline = when (length.lowercase()) {
            "short" -> "Concise and punchy (approximately 150-300 words). Maximize value per sentence, eliminate filler."
            "long" -> "Comprehensive, in-depth longform (approximately 1,200-2,000 words). Include exhaustive analysis, examples, and breakdown."
            else -> "Well-rounded medium length (approximately 600-900 words) balancing detail and readability."
        }

        val audienceContext = when (audience.lowercase()) {
            "students" -> "Targeted at ambitious students and early learners. Use intuitive analogies and encouraging, clear prose."
            "business" -> "Targeted at founders, C-level executives, and business decision-makers. Focus on ROI, operational efficiency, and tangible leverage."
            "professionals" -> "Crafted for experienced industry practitioners. Utilize domain-specific vocabulary and advanced actionable workflows."
            "social media" -> "Tailored for digital audiences. Use high-impact hooks, single-sentence paragraphs, and relatable viral rhythm."
            "customers" -> "Written directly for potential buyers. Speak directly to desires, eliminate friction, and build trust."
            else -> "Accessible to a general, curious audience while maintaining authority."
        }

        val prompt = """
        Act as an elite content creator and professional copywriter specializing in $category.
        
        Write a $tone, highly compelling $category on the following topic:
        "$topic"
        
        KEY SPECIFICATIONS:
        • Target Audience: $audience ($audienceContext)
        • Tone & Voice: $tone, authoritative yet relatable, and free of generic clichés or robotic buzzwords.
        • Scope & Length: $lengthGuideline
        
        STRUCTURAL DIRECTIVES:
        1. Hook: Start with a powerful first sentence that immediately grabs attention.
        2. Body: Organize thoughts with clear headings, bulleted takeaways, and concrete examples.
        3. Actionable Insights: Provide practical value the reader can apply immediately.
        4. Conclusion & Call-to-Action: End with a memorable takeaway and a natural engagement prompt.
        """.trimIndent()

        return GeneratedPrompt(
            title = "Writing Prompt • $category",
            category = "Writing",
            fullText = prompt,
            tags = listOf(category, tone, length, audience)
        )
    }

    fun generateMarketingPrompt(config: MarketingPromptConfig): GeneratedPrompt {
        val product = config.productOrBusiness.trim().ifBlank { "Our innovative solution" }
        val audience = config.targetAudience.trim().ifBlank { "Ideal target consumers" }
        val benefit = config.mainBenefit.trim().ifBlank { "Save significant time while achieving superior results" }
        val offer = config.offer.trim()
        val cta = config.callToAction.trim().ifBlank { "Learn More" }
        val tone = config.tone
        val category = config.category

        val offerSection = if (offer.isNotBlank()) {
            "• Irresistible Offer: $offer"
        } else {
            "• Offer: Highlight high value with immediate risk-free accessibility"
        }

        val prompt = """
        Act as an award-winning direct-response marketer and growth strategist.
        
        Create a high-converting $category campaign asset for:
        Product / Business: $product
        
        CAMPAIGN PARAMETERS:
        • Target Audience: $audience
        • Core Transformation / Benefit: $benefit
        $offerSection
        • Call to Action (CTA): $cta
        • Brand Tone: $tone and persuasive
        
        DELIVERABLES REQUIRED:
        1. 3 Scroll-Stopping Hooks / Headlines (curiosity, fear of missing out, benefit-driven).
        2. Primary Ad Body Copy utilizing the PAS (Problem - Agitation - Solution) or AIDA (Attention - Interest - Desire - Action) framework.
        3. Visual / Creative Direction recommendation (what graphic, video shot, or b-roll best complements this copy).
        4. 2 High-CTR Call to Action variations.
        
        Ensure the copy is punchy, emotion-stirring, and directly addresses the primary objection of $audience.
        """.trimIndent()

        return GeneratedPrompt(
            title = "Marketing Campaign • $category",
            category = "Marketing",
            fullText = prompt,
            tags = listOf(category, tone, "Conversion", "Copywriting")
        )
    }

    fun generateAiToolPrompt(config: AiToolPromptConfig): GeneratedPrompt {
        val tool = config.tool
        val subCategory = config.subCategory
        val topic = config.topic.trim().ifBlank { "Core system implementation and analysis" }
        val complexity = config.complexity

        val prompt = when (tool.lowercase()) {
            "chatgpt" -> when (subCategory.lowercase()) {
                "coding" -> """
                Act as a Principal Software Engineer. Provide a production-ready, clean-code solution for:
                "$topic"
                
                Requirements:
                - Implement using current best architectural practices and modern idiom.
                - Include comprehensive error handling, thread safety, and resource cleanups.
                - Add brief inline comments explaining non-obvious algorithmic choices.
                - Provide unit test examples demonstrating happy paths and edge cases.
                """.trimIndent()
                "business" -> """
                Act as a Senior Venture Partner and McKinsey Strategy Consultant.
                Conduct a rigorous business model and strategic breakdown for:
                "$topic"
                
                Please structure your output:
                1. Executive Summary & Value Proposition
                2. Target Market Sizing (TAM, SAM, SOM) & Customer Archetypes
                3. Unit Economics, Monetization Streams & Pricing Strategy
                4. Primary Competitive Moats & Go-to-Market (GTM) Funnel
                5. Key Operational Risks and Mitigation Roadmap
                """.trimIndent()
                "research" -> """
                Act as a Lead Academic Researcher. Synthesize a deep-dive research briefing on:
                "$topic"
                
                Structure:
                - State-of-the-art overview and core paradigms
                - Key methodological trade-offs and empirical evidence
                - Divergent viewpoints and ongoing debates
                - Future projections and unanswered inquiries
                """.trimIndent()
                else -> """
                Act as a world-class expert mentor in $subCategory.
                Provide a structured, deep, step-by-step masterclass on:
                "$topic"
                Complexity level: $complexity.
                Provide concrete frameworks, real-world examples, and common pitfalls to avoid.
                """.trimIndent()
            }

            "gemini" -> when (subCategory.lowercase()) {
                "analysis" -> """
                Perform an advanced multi-perspective analytical breakdown using Gemini's multimodal reasoning depth:
                Objective: "$topic"
                
                Requirements:
                - Deconstruct core components into first principles.
                - Cross-examine data trends, causal relationships, and subtle correlations.
                - Formulate a prioritized action matrix (Immediate vs Strategic leverage).
                """.trimIndent()
                "research" -> """
                Generate a comprehensive technological and market comparison matrix regarding:
                "$topic"
                
                Evaluate across: Scalability, Maintenance Burden, Security Posture, Developer Experience, and Total Cost of Ownership.
                """.trimIndent()
                else -> """
                Explore this topic with deep creative and cognitive insight:
                "$topic"
                Synthesize unconventional perspectives, cross-domain analogies, and actionable next steps.
                """.trimIndent()
            }

            "claude" -> when (subCategory.lowercase()) {
                "writing" -> """
                Write a nuanced, intellectually rigorous essay on:
                "$topic"
                
                Tone: Thoughtful, articulate, elegant, and devoid of hyperbolic AI filler.
                Craft an engaging narrative arc that explores subtle counterarguments with balanced intellectual honesty.
                """.trimIndent()
                "analysis" -> """
                Conduct a forensic clause-by-clause analysis of:
                "$topic"
                
                Identify ambiguous terminology, latent liabilities, leverage imbalances, and recommend specific redline improvements.
                """.trimIndent()
                else -> """
                You are Claude. Provide an exhaustive, deeply reasoned solution for:
                "$topic"
                Ensure precision, intellectual humility, and clear logical foundations.
                """.trimIndent()
            }

            "midjourney" -> {
                val ar = config.extraOptions["ar"] ?: "--ar 16:9"
                val version = config.extraOptions["version"] ?: "v 6.1"
                """
                $topic, masterfully rendered in $subCategory style, shot on 35mm lens, f/1.8 aperture, natural photorealistic lighting, octane render, volumetric atmospheric haze, hyper-detailed textures, symmetrical balance, award-winning composition $ar --$version --stylize 250
                """.trimIndent()
            }

            "google flow" -> """
            Generate an ultra-realistic continuous simulation for Google Flow:
            Scene Directive: $topic
            Subcategory: $subCategory
            Motion Vectors: Smooth physical simulation, fluid continuous camera trajectory, volumetric lighting interaction, photorealistic light scattering, 4K temporal fidelity.
            """.trimIndent()

            "elevenlabs" -> """
            Voice Profile & Speech Direction for ElevenLabs AI Voice:
            Context: $topic
            Style / Archetype: $subCategory
            Pacing: Natural cadence with deliberate pauses for dramatic emphasis.
            Tone / Emotion: Professional, warm, and resonant.
            Speech Delivery Guidelines:
            - Pause length at em-dashes: 0.4s
            - Breath intake: Subtle and realistic
            - Inflection: Upward on questions, grounded and confident on assertions.
            """.trimIndent()

            else -> """
            Optimized Prompt for $tool ($subCategory):
            Task: $topic
            Complexity: $complexity
            Please deliver a rigorous, production-grade output tailored specifically for $tool's architectural strengths.
            """.trimIndent()
        }

        return GeneratedPrompt(
            title = "$tool • $subCategory",
            category = "AI Tools",
            fullText = prompt,
            tags = listOf(tool, subCategory, complexity)
        )
    }
}
