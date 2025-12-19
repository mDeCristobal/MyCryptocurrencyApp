package com.example.mycryptocurrencyapp.features.coins.presentation.coin_list

import com.example.mycryptocurrencyapp.features.coins.domain.model.Coin

data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String = ""
)

sealed class CoinListUserEvent {
    data class OnCoinClick(val coin: Coin): CoinListUserEvent()
    object OnRefresh: CoinListUserEvent()
}
