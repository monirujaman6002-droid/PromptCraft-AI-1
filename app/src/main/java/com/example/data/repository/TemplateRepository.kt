package com.example.data.repository

import com.example.data.model.PromptCategory
import com.example.data.model.PromptTemplate

object TemplateRepository {

    val allTemplates: List<PromptTemplate> = listOf(
        // ==================== IMAGE TEMPLATES (12) ====================
        PromptTemplate(
            id = "img_cinematic_portrait",
            category = PromptCategory.IMAGE,
            subCategory = "Cinematic Portrait",
            title = "Cinematic Portrait",
            description = "Dramatic movie-still style portrait with rich lighting and depth of field.",
            exampleInput = "A weathered sailor with piercing blue eyes standing on a misty pier at dawn",
            defaultOptions = mapOf("style" to "Cinematic", "lighting" to "Golden Hour", "camera" to "Medium Shot", "quality" to "Ultra Detailed", "aspectRatio" to "16:9"),
            isTrending = true
        ),
        PromptTemplate(
            id = "img_realistic_portrait",
            category = PromptCategory.IMAGE,
            subCategory = "Realistic Portrait",
            title = "Realistic Portrait",
            description = "Photorealistic human portrait with natural skin textures and expressive eyes.",
            exampleInput = "An elderly woman with kind smile and silver hair in a cozy sunlit kitchen",
            defaultOptions = mapOf("style" to "Realistic", "lighting" to "Natural Light", "camera" to "Close-up", "quality" to "Ultra Detailed", "aspectRatio" to "1:1"),
            isTrending = false
        ),
        PromptTemplate(
            id = "img_product_advertisement",
            category = PromptCategory.IMAGE,
            subCategory = "Product Photography",
            title = "Product Advertisement",
            description = "High-end commercial product shot with clean reflective surfaces and studio lighting.",
            exampleInput = "A sleek matte-black wireless headphone resting on dark obsidian stone with water droplets",
            defaultOptions = mapOf("style" to "Product Photography", "lighting" to "Studio Light", "camera" to "Close-up", "quality" to "Ultra Detailed", "aspectRatio" to "1:1"),
            isTrending = true
        ),
        PromptTemplate(
            id = "img_anime_character",
            category = PromptCategory.IMAGE,
            subCategory = "Anime",
            title = "Anime Character",
            description = "Vibrant Japanese anime aesthetic with cel-shaded colors and dynamic lighting.",
            exampleInput = "A cyber samurai warrior standing under neon-lit cherry blossom trees in futuristic Neo Tokyo",
            defaultOptions = mapOf("style" to "Anime", "lighting" to "Neon Light", "camera" to "Medium Shot", "quality" to "High Quality", "aspectRatio" to "9:16"),
            isTrending = true
        ),
        PromptTemplate(
            id = "img_luxury_product",
            category = PromptCategory.IMAGE,
            subCategory = "Luxury",
            title = "Luxury Product",
            description = "Prestigious luxury aesthetic featuring gold accents, marble textures, and soft glow.",
            exampleInput = "An emerald green luxury perfume bottle with 24k gold engravings on white Carrara marble",
            defaultOptions = mapOf("style" to "Luxury", "lighting" to "Soft Light", "camera" to "Close-up", "quality" to "Ultra Detailed", "aspectRatio" to "4:5"),
            isTrending = true
        ),
        PromptTemplate(
            id = "img_fantasy_landscape",
            category = PromptCategory.IMAGE,
            subCategory = "Fantasy",
            title = "Fantasy Landscape",
            description = "Mythical magical realms with ethereal glowing flora, waterfalls, and starry skies.",
            exampleInput = "A floating crystal castle surrounded by bioluminescent waterfalls and twin moons",
            defaultOptions = mapOf("style" to "Fantasy", "lighting" to "Moody Light", "camera" to "Wide Shot", "quality" to "Ultra Detailed", "aspectRatio" to "16:9"),
            isTrending = false
        ),
        PromptTemplate(
            id = "img_youtube_thumbnail",
            category = PromptCategory.IMAGE,
            subCategory = "YouTube Thumbnail",
            title = "YouTube Thumbnail",
            description = "High-impact visual composition designed to grab immediate viewer attention.",
            exampleInput = "A tech reviewer reacting with wide eyes holding an ultra-futuristic transparent smartphone",
            defaultOptions = mapOf("style" to "Cinematic", "lighting" to "Dramatic Light", "camera" to "Medium Shot", "quality" to "High Quality", "aspectRatio" to "16:9"),
            isTrending = true
        ),
        PromptTemplate(
            id = "img_fashion_editorial",
            category = PromptCategory.IMAGE,
            subCategory = "Fashion",
            title = "Fashion Editorial",
            description = "Vogue-style haute couture photoshoot with high-fashion apparel and artistic pose.",
            exampleInput = "An avant-garde model in a flowing crimson silk gown walking in the sand dunes at sunset",
            defaultOptions = mapOf("style" to "Fashion", "lighting" to "Golden Hour", "camera" to "Eye Level", "quality" to "Ultra Detailed", "aspectRatio" to "4:5"),
            isTrending = false
        ),
        PromptTemplate(
            id = "img_architecture",
            category = PromptCategory.IMAGE,
            subCategory = "Architecture",
            title = "Modern Architecture",
            description = "Sleek geometric architectural marvels with organic curves and glass facades.",
            exampleInput = "A minimalist Scandinavian glass villa built over tranquil fjord waters surrounded by pine forest",
            defaultOptions = mapOf("style" to "Minimalist", "lighting" to "Natural Light", "camera" to "Drone Shot", "quality" to "Ultra Detailed", "aspectRatio" to "16:9"),
            isTrending = false
        ),
        PromptTemplate(
            id = "img_food_photography",
            category = PromptCategory.IMAGE,
            subCategory = "Food Photography",
            title = "Food Photography",
            description = "Mouthwatering culinary capture with steam, rich glaze, and rustic background.",
            exampleInput = "An artisanal wood-fired pizza with bubbling buffalo mozzarella, fresh basil, and shaved truffles",
            defaultOptions = mapOf("style" to "Product Photography", "lighting" to "Warm Studio", "camera" to "Close-up", "quality" to "Ultra Detailed", "aspectRatio" to "1:1"),
            isTrending = false
        ),
        PromptTemplate(
            id = "img_landscape",
            category = PromptCategory.IMAGE,
            subCategory = "Landscape",
            title = "Alpine Landscape",
            description = "Breathtaking national park vistas with crystal reflections and mountain peaks.",
            exampleInput = "Snow-capped alpine peaks mirrored in a glassy turquoise glacial lake at sunrise",
            defaultOptions = mapOf("style" to "Realistic", "lighting" to "Golden Hour", "camera" to "Wide Shot", "quality" to "Ultra Detailed", "aspectRatio" to "16:9"),
            isTrending = false
        ),
        PromptTemplate(
            id = "img_social_poster",
            category = PromptCategory.IMAGE,
            subCategory = "Social Media Poster",
            title = "Social Media Poster",
            description = "Striking graphic illustration with bold color contrasts and negative space for text.",
            exampleInput = "Retro vaporwave synthwave music festival poster with palm tree silhouettes and grid horizon",
            defaultOptions = mapOf("style" to "3D", "lighting" to "Neon Light", "camera" to "Eye Level", "quality" to "High Quality", "aspectRatio" to "9:16"),
            isTrending = false
        ),

        // ==================== VIDEO TEMPLATES (10) ====================
        PromptTemplate(
            id = "vid_cinematic_short",
            category = PromptCategory.VIDEO,
            subCategory = "Cinematic Video",
            title = "Cinematic Short Video",
            description = "Immersive cinematic scene with continuous camera movement and atmospheric depth.",
            exampleInput = "A lone astronaut wandering across the red dunes of Mars as dust storm sweeps through",
            defaultOptions = mapOf("videoStyle" to "Cinematic", "movement" to "Tracking Shot", "duration" to "10 seconds", "lighting" to "Golden Hour", "aspectRatio" to "16:9"),
            isTrending = true
        ),
        PromptTemplate(
            id = "vid_viral_short",
            category = PromptCategory.VIDEO,
            subCategory = "YouTube Shorts",
            title = "Viral Short Video",
            description = "Fast-paced vertical video designed for TikTok, Reels, and Shorts algorithm retention.",
            exampleInput = "A barista crafting a glowing matcha latte with hypnotic 3D latte art swirl",
            defaultOptions = mapOf("videoStyle" to "Realistic", "movement" to "Slow Zoom In", "duration" to "15 seconds", "lighting" to "Studio", "aspectRatio" to "9:16"),
            isTrending = true
        ),
        PromptTemplate(
            id = "vid_product_commercial",
            category = PromptCategory.VIDEO,
            subCategory = "Product Advertisement",
            title = "Product Commercial",
            description = "360-degree orbital camera showcase highlighting product craftsmanship.",
            exampleInput = "A titanium luxury smart sports watch rotating smoothly while water droplets splash off the screen",
            defaultOptions = mapOf("videoStyle" to "Advertisement", "movement" to "Orbit Shot", "duration" to "10 seconds", "lighting" to "Studio", "aspectRatio" to "16:9"),
            isTrending = false
        ),
        PromptTemplate(
            id = "vid_travel_cinematic",
            category = PromptCategory.VIDEO,
            subCategory = "Travel Video",
            title = "Travel Video Reel",
            description = "High-flying drone and dynamic pan footage capturing exotic travel destinations.",
            exampleInput = "A vintage wooden boat cruising through emerald waters of Ha Long Bay limestone islands",
            defaultOptions = mapOf("videoStyle" to "Documentary", "movement" to "Drone Shot", "duration" to "15 seconds", "lighting" to "Natural", "aspectRatio" to "9:16"),
            isTrending = false
        ),
        PromptTemplate(
            id = "vid_character_scene",
            category = PromptCategory.VIDEO,
            subCategory = "Character Scene",
            title = "Character Scene",
            description = "Emotional storytelling moment focusing on character expressions and environmental shifts.",
            exampleInput = "A detective stepping out of a classic 1950s car into a rainy neon-lit alleyway",
            defaultOptions = mapOf("videoStyle" to "Cinematic", "movement" to "Tracking Shot", "duration" to "10 seconds", "lighting" to "Neon", "aspectRatio" to "16:9"),
            isTrending = false
        ),
        PromptTemplate(
            id = "vid_action_scene",
            category = PromptCategory.VIDEO,
            subCategory = "Action Scene",
            title = "Action Sequence",
            description = "High-adrenaline camera movements capturing kinetic speed and intense motion.",
            exampleInput = "A futuristic hovercraft weaving through subterranean crystal canyons at breakneck speed",
            defaultOptions = mapOf("videoStyle" to "Cinematic", "movement" to "Handheld", "duration" to "5 seconds", "lighting" to "Dramatic", "aspectRatio" to "16:9"),
            isTrending = false
        ),
        PromptTemplate(
            id = "vid_documentary",
            category = PromptCategory.VIDEO,
            subCategory = "Documentary",
            title = "Nature Documentary",
            description = "National Geographic style slow-motion wildlife capture with macro realism.",
            exampleInput = "A chameleon changing colors on a jungle branch as morning dew evaporates in the sun",
            defaultOptions = mapOf("videoStyle" to "Documentary", "movement" to "Slow Zoom In", "duration" to "15 seconds", "lighting" to "Natural", "aspectRatio" to "16:9"),
            isTrending = false
        ),
        PromptTemplate(
            id = "vid_anime_intro",
            category = PromptCategory.VIDEO,
            subCategory = "Anime",
            title = "Anime Intro Sequence",
            description = "Sakuga quality animated battle sequence with speed lines and magic particles.",
            exampleInput = "A sorceress unleashing an arcane dragon spirit that illuminates midnight clouds",
            defaultOptions = mapOf("videoStyle" to "Anime", "movement" to "Tilt Up", "duration" to "10 seconds", "lighting" to "Night", "aspectRatio" to "16:9"),
            isTrending = false
        ),
        PromptTemplate(
            id = "vid_music_video",
            category = PromptCategory.VIDEO,
            subCategory = "Music Video",
            title = "Music Video Visualizer",
            description = "Rhythmic psychedelic visuals syncable to beats with shifting color prisms.",
            exampleInput = "A dancer moving through liquid neon prisms that ripple with acoustic frequency waves",
            defaultOptions = mapOf("videoStyle" to "Music Video", "movement" to "Orbit Shot", "duration" to "30 seconds", "lighting" to "Neon", "aspectRatio" to "9:16"),
            isTrending = false
        ),
        PromptTemplate(
            id = "vid_product_showcase",
            category = PromptCategory.VIDEO,
            subCategory = "Product Showcase",
            title = "Product Showcase",
            description = "Exploded-view mechanical breakdown showing internal tech engineering.",
            exampleInput = "An electric hypercar motor dissolving into floating illuminated components and reassembling",
            defaultOptions = mapOf("videoStyle" to "Advertisement", "movement" to "Slow Zoom Out", "duration" to "15 seconds", "lighting" to "Studio", "aspectRatio" to "16:9"),
            isTrending = false
        ),

        // ==================== WRITING TEMPLATES (10) ====================
        PromptTemplate(
            id = "write_youtube_script",
            category = PromptCategory.WRITING,
            subCategory = "YouTube Script",
            title = "YouTube Video Script",
            description = "High-retention video script structure with a 3-second hook, body, and CTA.",
            exampleInput = "How artificial intelligence will change remote work jobs in the next 3 years",
            defaultOptions = mapOf("tone" to "Educational", "length" to "Medium", "audience" to "General"),
            isTrending = false
        ),
        PromptTemplate(
            id = "write_blog_article",
            category = PromptCategory.WRITING,
            subCategory = "Blog Article",
            title = "Comprehensive Blog Article",
            description = "SEO-optimized, value-dense article with subheadings, actionable takeaways, and FAQs.",
            exampleInput = "10 essential productivity habits for modern software developers and creators",
            defaultOptions = mapOf("tone" to "Professional", "length" to "Long", "audience" to "Professionals"),
            isTrending = false
        ),
        PromptTemplate(
            id = "write_social_content",
            category = PromptCategory.WRITING,
            subCategory = "Social Media Content",
            title = "Social Media Content",
            description = "Viral carousel or post format with thought-provoking openers and comment triggers.",
            exampleInput = "Why perfectionism is secretly killing your creative output and how to break free",
            defaultOptions = mapOf("tone" to "Persuasive", "length" to "Short", "audience" to "Social Media"),
            isTrending = true
        ),
        PromptTemplate(
            id = "write_ig_caption",
            category = PromptCategory.WRITING,
            subCategory = "Instagram Caption",
            title = "Instagram Caption & Hashtags",
            description = "Engaging storytelling caption with relatable line breaks, emojis, and targeted tags.",
            exampleInput = "Behind-the-scenes building an indie app from scratch as a solo founder",
            defaultOptions = mapOf("tone" to "Casual", "length" to "Medium", "audience" to "Social Media"),
            isTrending = false
        ),
        PromptTemplate(
            id = "write_story",
            category = PromptCategory.WRITING,
            subCategory = "Story",
            title = "Creative Fiction Story",
            description = "Rich narrative with character arcs, sensory world-building, and suspenseful plot twists.",
            exampleInput = "A time traveler who arrives in 2026 to deliver an urgent message to their younger self",
            defaultOptions = mapOf("tone" to "Emotional", "length" to "Long", "audience" to "General"),
            isTrending = false
        ),
        PromptTemplate(
            id = "write_email",
            category = PromptCategory.WRITING,
            subCategory = "Email",
            title = "High-Converting Cold Email",
            description = "Punchy, respectful B2B cold email emphasizing client pain points and low-friction CTA.",
            exampleInput = "Offering an AI-powered customer support audit to mid-market e-commerce stores",
            defaultOptions = mapOf("tone" to "Professional", "length" to "Short", "audience" to "Business"),
            isTrending = false
        ),
        PromptTemplate(
            id = "write_product_desc",
            category = PromptCategory.WRITING,
            subCategory = "Product Description",
            title = "Product Description",
            description = "Emotion-driven e-commerce copy blending technical specs with lifestyle benefits.",
            exampleInput = "An ergonomic memory foam travel pillow made with organic bamboo cooling fabrics",
            defaultOptions = mapOf("tone" to "Persuasive", "length" to "Medium", "audience" to "Customers"),
            isTrending = false
        ),
        PromptTemplate(
            id = "write_seo_content",
            category = PromptCategory.WRITING,
            subCategory = "SEO Content",
            title = "SEO Pillar Guide",
            description = "In-depth topical authority pillar page targeting semantic search intents and snippets.",
            exampleInput = "The complete guide to intermittent fasting for beginners: science, meals, and mistakes",
            defaultOptions = mapOf("tone" to "Educational", "length" to "Long", "audience" to "General"),
            isTrending = false
        ),
        PromptTemplate(
            id = "write_educational",
            category = PromptCategory.WRITING,
            subCategory = "Educational Content",
            title = "Course Lesson & Explainer",
            description = "Complex subject broken down using Feynman technique analogies and self-check quizzes.",
            exampleInput = "How quantum computing works explained to a high school student",
            defaultOptions = mapOf("tone" to "Educational", "length" to "Medium", "audience" to "Students"),
            isTrending = false
        ),
        PromptTemplate(
            id = "write_facebook_post",
            category = PromptCategory.WRITING,
            subCategory = "Facebook Post",
            title = "Community Facebook Post",
            description = "Conversational community post engineered for high comments and organic shares.",
            exampleInput = "Asking community members what one book changed their entire career outlook",
            defaultOptions = mapOf("tone" to "Friendly", "length" to "Short", "audience" to "Social Media"),
            isTrending = false
        ),

        // ==================== MARKETING TEMPLATES (10) ====================
        PromptTemplate(
            id = "mkt_fb_ad",
            category = PromptCategory.MARKETING,
            subCategory = "Facebook Advertisement",
            title = "Facebook Ad Campaign",
            description = "High-performing direct response Facebook feed ad copy with scroll-stopping lead.",
            exampleInput = "PromptCraft AI Mobile App",
            defaultOptions = mapOf(
                "targetAudience" to "Content creators, digital marketers, and AI enthusiasts",
                "mainBenefit" to "Generate studio-grade prompts in 3 seconds without prompt engineering skills",
                "offer" to "Free download with 50+ built-in pro templates",
                "callToAction" to "Install Now",
                "tone" to "Persuasive"
            ),
            isTrending = false
        ),
        PromptTemplate(
            id = "mkt_ig_ad",
            category = PromptCategory.MARKETING,
            subCategory = "Instagram Advertisement",
            title = "Instagram Story & Reel Ad",
            description = "Vibrant, aesthetic-driven mobile ad copy optimized for swipe-up click-throughs.",
            exampleInput = "Lumina Glow Skin Serum",
            defaultOptions = mapOf(
                "targetAudience" to "Skincare lovers aged 20-35 seeking radiant glass skin",
                "mainBenefit" to "Visible glow and hydration within 7 days",
                "offer" to "20% off your first bottle with code GLOW20",
                "callToAction" to "Shop Now",
                "tone" to "Emotional"
            ),
            isTrending = false
        ),
        PromptTemplate(
            id = "mkt_yt_ad",
            category = PromptCategory.MARKETING,
            subCategory = "YouTube Advertisement",
            title = "YouTube Video Ad Script",
            description = "5-second unskippable hook transitioning into demonstration and strong closing offer.",
            exampleInput = "CloudSync Business Backup",
            defaultOptions = mapOf(
                "targetAudience" to "Small business owners worried about data loss and ransomware",
                "mainBenefit" to "Automatic zero-downtime backups encrypted with bank-level security",
                "offer" to "30-day free trial no credit card required",
                "callToAction" to "Claim Free Trial",
                "tone" to "Professional"
            ),
            isTrending = false
        ),
        PromptTemplate(
            id = "mkt_sales_copy",
            category = PromptCategory.MARKETING,
            subCategory = "Sales Copy",
            title = "High-Converting Sales Copy",
            description = "PAS (Problem-Agitation-Solution) sales letter framework engineered for maximum conversion.",
            exampleInput = "Master Prompting Masterclass Video Course",
            defaultOptions = mapOf(
                "targetAudience" to "Freelancers looking to 10x their output with generative AI",
                "mainBenefit" to "Save 15+ hours weekly while producing higher tier creative assets",
                "offer" to "Lifetime access at 50% early bird discount",
                "callToAction" to "Enroll Today",
                "tone" to "Persuasive"
            ),
            isTrending = false
        ),
        PromptTemplate(
            id = "mkt_landing_page",
            category = PromptCategory.MARKETING,
            subCategory = "Landing Page",
            title = "SaaS Landing Page Copy",
            description = "Complete landing page blueprint including hero headline, features, social proof, and FAQs.",
            exampleInput = "NextGen AI Audio Editor",
            defaultOptions = mapOf(
                "targetAudience" to "Podcasters, voice actors, and video editors",
                "mainBenefit" to "Clean audio noise, remove filler words, and balance EQ with one click",
                "offer" to "Get started free for up to 3 projects",
                "callToAction" to "Start Free Today",
                "tone" to "Professional"
            ),
            isTrending = false
        ),
        PromptTemplate(
            id = "mkt_promo_poster",
            category = PromptCategory.MARKETING,
            subCategory = "Promotional Poster",
            title = "Promotional Event Poster",
            description = "Punchy typography-focused promotional copy for prints, flyers, and digital posters.",
            exampleInput = "Tech Innovators Summit 2026",
            defaultOptions = mapOf(
                "targetAudience" to "Tech founders, developers, and investors",
                "mainBenefit" to "Network with 500+ top industry pioneers and keynote speakers",
                "offer" to "Early bird ticket sale ends this Friday",
                "callToAction" to "Reserve Your Pass",
                "tone" to "Exciting"
            ),
            isTrending = false
        ),
        PromptTemplate(
            id = "mkt_promo_banner",
            category = PromptCategory.MARKETING,
            subCategory = "Promotional Banner",
            title = "Promotional Web Banner",
            description = "Crisp headline and subtext designed for website hero banners and Google display ads.",
            exampleInput = "Cyber Weekend Mega Sale",
            defaultOptions = mapOf(
                "targetAudience" to "Online shoppers looking for seasonal electronics deals",
                "mainBenefit" to "Up to 70% off flagship smart gear with free worldwide delivery",
                "offer" to "Extra 10% instant checkout voucher",
                "callToAction" to "Explore Deals",
                "tone" to "Urgent"
            ),
            isTrending = false
        ),
        PromptTemplate(
            id = "mkt_business_promo",
            category = PromptCategory.MARKETING,
            subCategory = "Business Promotion",
            title = "Local Business Promotion",
            description = "Community-focused local service announcement with trust badges and warm invitation.",
            exampleInput = "Artisan Roast Coffee Roastery & Cafe",
            defaultOptions = mapOf(
                "targetAudience" to "Local neighborhood residents and specialty coffee lovers",
                "mainBenefit" to "Freshly roasted single-origin Ethiopian and Colombian beans daily",
                "offer" to "Complimentary pastry with any signature cold brew",
                "callToAction" to "Visit Us Today",
                "tone" to "Friendly"
            ),
            isTrending = false
        ),
        PromptTemplate(
            id = "mkt_strategy",
            category = PromptCategory.MARKETING,
            subCategory = "Marketing Strategy",
            title = "30-Day Marketing Strategy",
            description = "Comprehensive multi-channel go-to-market plan with KPIs, funnel stages, and budget split.",
            exampleInput = "Launching an eco-friendly reusable water bottle brand",
            defaultOptions = mapOf(
                "targetAudience" to "Fitness and sustainability conscious urban professionals",
                "mainBenefit" to "Zero plastic pollution with lifetime insulated durability",
                "offer" to "Kickstarter backer tier with personalized laser engraving",
                "callToAction" to "Back on Kickstarter",
                "tone" to "Professional"
            ),
            isTrending = false
        ),
        PromptTemplate(
            id = "mkt_product_advertisement",
            category = PromptCategory.MARKETING,
            subCategory = "Product Advertisement",
            title = "Product Launch Announcement",
            description = "Press release style launch announcement highlighting breakthrough innovations.",
            exampleInput = "SmartAir Home Purifier Pro",
            defaultOptions = mapOf(
                "targetAudience" to "Homeowners with pets and allergy sufferers",
                "mainBenefit" to "Eliminates 99.97% of airborne allergens silently within 15 minutes",
                "offer" to "Free 2-year HEPA filter supply with pre-order",
                "callToAction" to "Pre-order Now",
                "tone" to "Professional"
            ),
            isTrending = false
        ),

        // ==================== AI TOOLS TEMPLATES (12) ====================
        // ChatGPT (5 subcategories: Business, Coding, Writing, Research, Learning)
        PromptTemplate(
            id = "tool_chatgpt_coding",
            category = PromptCategory.AI_TOOLS,
            subCategory = "Coding",
            title = "ChatGPT Code Architect & Debugger",
            description = "Full stack code generation with architectural patterns, error handling, and comments.",
            exampleInput = "Build a thread-safe caching service with Kotlin Coroutines and Mutex",
            defaultOptions = mapOf("tool" to "ChatGPT", "subCategory" to "Coding", "complexity" to "Advanced"),
            targetTool = "ChatGPT",
            isTrending = false
        ),
        PromptTemplate(
            id = "tool_chatgpt_business",
            category = PromptCategory.AI_TOOLS,
            subCategory = "Business",
            title = "ChatGPT Business Model Canvas",
            description = "Comprehensive business strategy analysis including monetization, cost structure, and USP.",
            exampleInput = "A subscription-based meal kit service for vegan athletes",
            defaultOptions = mapOf("tool" to "ChatGPT", "subCategory" to "Business", "complexity" to "Standard"),
            targetTool = "ChatGPT",
            isTrending = false
        ),
        PromptTemplate(
            id = "tool_chatgpt_research",
            category = PromptCategory.AI_TOOLS,
            subCategory = "Research",
            title = "ChatGPT Deep Research Synthesizer",
            description = "Structured literature review with citations, counterarguments, and synthesized findings.",
            exampleInput = "Recent developments in solid-state lithium battery commercialization",
            defaultOptions = mapOf("tool" to "ChatGPT", "subCategory" to "Research", "complexity" to "Deep"),
            targetTool = "ChatGPT",
            isTrending = false
        ),

        // Gemini (4 subcategories: Research, Writing, Analysis, Creative)
        PromptTemplate(
            id = "tool_gemini_analysis",
            category = PromptCategory.AI_TOOLS,
            subCategory = "Analysis",
            title = "Gemini Multi-Modal Data Analysis",
            description = "Complex multimodal analysis extracting patterns, anomalies, and strategic actions.",
            exampleInput = "Analyze quarterly SaaS metrics including churn, ARR growth, and CAC payback",
            defaultOptions = mapOf("tool" to "Gemini", "subCategory" to "Analysis", "complexity" to "Advanced"),
            targetTool = "Gemini",
            isTrending = false
        ),
        PromptTemplate(
            id = "tool_gemini_research",
            category = PromptCategory.AI_TOOLS,
            subCategory = "Research",
            title = "Gemini Comparative Technology Review",
            description = "Structured pros/cons comparison matrix evaluating competing frameworks.",
            exampleInput = "Jetpack Compose vs Flutter for production enterprise mobile apps in 2026",
            defaultOptions = mapOf("tool" to "Gemini", "subCategory" to "Research", "complexity" to "Detailed"),
            targetTool = "Gemini",
            isTrending = false
        ),

        // Claude (Writing, Analysis, Coding)
        PromptTemplate(
            id = "tool_claude_writing",
            category = PromptCategory.AI_TOOLS,
            subCategory = "Writing",
            title = "Claude Long-Form Thought Leadership",
            description = "Nuanced, human-like editorial writing with vivid vocabulary and rigorous logic.",
            exampleInput = "The ethical implications of autonomous decision systems in medical diagnostics",
            defaultOptions = mapOf("tool" to "Claude", "subCategory" to "Writing", "complexity" to "Master"),
            targetTool = "Claude",
            isTrending = false
        ),
        PromptTemplate(
            id = "tool_claude_analysis",
            category = PromptCategory.AI_TOOLS,
            subCategory = "Analysis",
            title = "Claude Contract & Policy Breakdown",
            description = "Meticulous legal and technical clause breakdown highlighting liabilities and traps.",
            exampleInput = "Reviewing an enterprise SaaS vendor SLA agreement for liability caps",
            defaultOptions = mapOf("tool" to "Claude", "subCategory" to "Analysis", "complexity" to "Rigorous"),
            targetTool = "Claude",
            isTrending = false
        ),

        // Midjourney (Portrait, Product, Architecture, Fantasy)
        PromptTemplate(
            id = "tool_midjourney_portrait",
            category = PromptCategory.AI_TOOLS,
            subCategory = "Portrait",
            title = "Midjourney v6 Masterpiece Portrait",
            description = "Hyper-detailed Midjourney prompt with camera parameters, lighting cues, and stylized weights.",
            exampleInput = "A futuristic cybernetic geisha with delicate porcelain skin and golden holographic hair pins",
            defaultOptions = mapOf("tool" to "Midjourney", "subCategory" to "Portrait", "version" to "v6.1", "ar" to "--ar 9:16"),
            targetTool = "Midjourney",
            isTrending = false
        ),
        PromptTemplate(
            id = "tool_midjourney_architecture",
            category = PromptCategory.AI_TOOLS,
            subCategory = "Architecture",
            title = "Midjourney Parametric Architecture",
            description = "Zaha Hadid inspired parametric fluid architecture rendered in photorealistic Octane render.",
            exampleInput = "A biometric cultural museum curving over coastal cliff edges with glass atrium",
            defaultOptions = mapOf("tool" to "Midjourney", "subCategory" to "Architecture", "version" to "v6.1", "ar" to "--ar 16:9"),
            targetTool = "Midjourney",
            isTrending = false
        ),

        // Google Flow (Cinematic Video, Character, Camera Movement, Advertisement)
        PromptTemplate(
            id = "tool_google_flow_cinematic",
            category = PromptCategory.AI_TOOLS,
            subCategory = "Cinematic Video",
            title = "Google Flow Video Simulation",
            description = "Physical simulation prompt with realistic temporal consistency and motion vector controls.",
            exampleInput = "A liquid metal orb dropping onto a mirrored surface splashing into chrome fractals in slow motion",
            defaultOptions = mapOf("tool" to "Google Flow", "subCategory" to "Cinematic Video", "motion" to "Fluid Dynamics"),
            targetTool = "Google Flow",
            isTrending = false
        ),
        PromptTemplate(
            id = "tool_google_flow_camera",
            category = PromptCategory.AI_TOOLS,
            subCategory = "Camera Movement",
            title = "Google Flow Continuous Camera Flythrough",
            description = "Seamless continuous first-person camera flight through intricate 3D environments.",
            exampleInput = "A camera diving through storm clouds into a sun-drenched canyon with roaring rapids",
            defaultOptions = mapOf("tool" to "Google Flow", "subCategory" to "Camera Movement", "motion" to "High Velocity"),
            targetTool = "Google Flow",
            isTrending = false
        ),

        // ElevenLabs (Voiceover, Narration, Character Voice, Advertisement)
        PromptTemplate(
            id = "tool_elevenlabs_narration",
            category = PromptCategory.AI_TOOLS,
            subCategory = "Narration",
            title = "ElevenLabs Documentary Narration Prompt",
            description = "Voice profile instructions specifying timbre, pacing, pauses, and emotional resonance.",
            exampleInput = "Deep, resonant cinematic voice with warm British cadence for a space exploration documentary",
            defaultOptions = mapOf("tool" to "ElevenLabs", "subCategory" to "Narration", "stability" to "75%", "clarity" to "90%"),
            targetTool = "ElevenLabs",
            isTrending = false
        ),
        PromptTemplate(
            id = "tool_elevenlabs_voiceover",
            category = PromptCategory.AI_TOOLS,
            subCategory = "Voiceover",
            title = "ElevenLabs High-Energy Commercial VO",
            description = "Dynamic energetic voice direction with punchy cadence, breathing marks, and enthusiasm.",
            exampleInput = "A fast, confident, upbeat female voice for an energetic sports beverage commercial",
            defaultOptions = mapOf("tool" to "ElevenLabs", "subCategory" to "Voiceover", "stability" to "50%", "clarity" to "85%"),
            targetTool = "ElevenLabs",
            isTrending = false
        )
    )

    fun getTrendingTemplates(): List<PromptTemplate> {
        return allTemplates.filter { it.isTrending }
    }

    fun getTemplatesByCategory(category: PromptCategory): List<PromptTemplate> {
        return allTemplates.filter { it.category == category }
    }

    fun getTemplatesByTool(tool: String): List<PromptTemplate> {
        return allTemplates.filter { it.targetTool.equals(tool, ignoreCase = true) }
    }

    fun getTemplateById(id: String): PromptTemplate? {
        return allTemplates.find { it.id == id }
    }

    fun searchTemplates(query: String): List<PromptTemplate> {
        if (query.isBlank()) return allTemplates
        val q = query.trim().lowercase()
        return allTemplates.filter {
            it.title.lowercase().contains(q) ||
            it.description.lowercase().contains(q) ||
            it.subCategory.lowercase().contains(q) ||
            it.category.name.lowercase().contains(q) ||
            (it.targetTool?.lowercase()?.contains(q) == true)
        }
    }
}
