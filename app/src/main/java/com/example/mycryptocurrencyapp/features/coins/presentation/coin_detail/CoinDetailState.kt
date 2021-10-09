package com.example.mycryptocurrencyapp.features.coins.presentation.coin_detail

import com.example.mycryptocurrencyapp.features.coins.domain.model.CoinDetail

sealed class CoinDetailState {
     data class IsLoading(val boolean: Boolean = false) : CoinDetailState()
     data class Success(val coinDetail: CoinDetail) : CoinDetailState()
     data class Error(val message: String = "") : CoinDetailState()
}
