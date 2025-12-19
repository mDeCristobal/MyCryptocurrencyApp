package com.example.mycryptocurrencyapp.common.ui

import kotlinx.serialization.Serializable

@Serializable
sealed class NavDestination {
    @Serializable
    data object CoinList

    @Serializable
    data class CoinDetail(
        val coinId: String
    )

}
