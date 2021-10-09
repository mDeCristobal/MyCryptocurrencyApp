package com.example.mycryptocurrencyapp.features.coins.presentation.coin_list

import com.example.mycryptocurrencyapp.features.coins.domain.model.Coin

//data class CoinListState(
//     val isLoading: Boolean = false,
//     val coins: List<Coin> = emptyList(),
//     val error: String = ""
//)

sealed class CoinListState {
    data class IsLoading(val boolean: Boolean = false) : CoinListState()
    data class Success(val coinList: List<Coin>) : CoinListState()
    data class Error(val message: String = "") : CoinListState()
}
