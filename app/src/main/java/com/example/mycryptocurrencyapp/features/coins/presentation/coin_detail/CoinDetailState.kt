package com.example.mycryptocurrencyapp.features.coins.presentation.coin_detail

import com.example.mycryptocurrencyapp.features.coins.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val error: String = ""
)

sealed class CoinDetailUserEvent {
    object OnRetry: CoinDetailUserEvent()
}
