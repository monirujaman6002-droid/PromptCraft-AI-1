package com.example.ads

import android.content.Context
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object AdManager {

    // Test AdMob unit IDs provided by Google for development/testing
    const val TEST_BANNER_ID = "ca-app-pub-3940256099942544/6300978111"
    const val TEST_INTERSTITIAL_ID = "ca-app-pub-3940256099942544/1033173712"
    const val TEST_REWARDED_ID = "ca-app-pub-3940256099942544/5224354917"

    // Official Production IDs
    const val PROD_BANNER_ID = "ca-app-pub-2798984139685255/2163848034"
    const val PROD_INTERSTITIAL_ID = "ca-app-pub-2798984139685255/6689416407"
    const val PROD_REWARDED_ID = "ca-app-pub-2798984139685255/5719949667"

    // Production IDs can be swapped here or injected via BuildConfig
    var bannerAdUnitId: String = PROD_BANNER_ID
    var interstitialAdUnitId: String = PROD_INTERSTITIAL_ID
    var rewardedAdUnitId: String = PROD_REWARDED_ID

    // Threshold for interstitial ads (e.g., every 4 generations)
    private const val INTERSTITIAL_INTERVAL = 4
    private var generationSinceLastAd = 0

    private val _isShowingInterstitial = MutableStateFlow(false)
    val isShowingInterstitial: StateFlow<Boolean> = _isShowingInterstitial.asStateFlow()

    private val _isShowingRewarded = MutableStateFlow(false)
    val isShowingRewarded: StateFlow<Boolean> = _isShowingRewarded.asStateFlow()

    private val _adStatusMessage = MutableStateFlow<String?>(null)
    val adStatusMessage: StateFlow<String?> = _adStatusMessage.asStateFlow()

    fun initialize(context: Context) {
        // Ready for MobileAds.initialize(context) when Google Play services Ads SDK is bundled.
    }

    fun onPromptGenerated(onShowInterstitial: () -> Unit = {}) {
        generationSinceLastAd++
        if (generationSinceLastAd >= INTERSTITIAL_INTERVAL) {
            generationSinceLastAd = 0
            _isShowingInterstitial.value = true
            onShowInterstitial()
        }
    }

    fun dismissInterstitial() {
        _isShowingInterstitial.value = false
    }

    fun requestRewardedAd(onRewardGranted: () -> Unit) {
        _isShowingRewarded.value = true
    }

    fun completeRewardedAd(onRewardGranted: () -> Unit) {
        _isShowingRewarded.value = false
        onRewardGranted()
        _adStatusMessage.value = "Reward claimed! +5 bonus generations unlocked."
    }

    fun dismissRewardedAd() {
        _isShowingRewarded.value = false
    }

    fun clearStatusMessage() {
        _adStatusMessage.value = null
    }
}
