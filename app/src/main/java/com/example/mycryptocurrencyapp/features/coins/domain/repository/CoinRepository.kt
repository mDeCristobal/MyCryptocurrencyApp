package com.example.mycryptocurrencyapp.features.coins.domain.repository

import com.example.mycryptocurrencyapp.common.Resource
import com.example.mycryptocurrencyapp.features.coins.domain.model.Coin
import com.example.mycryptocurrencyapp.features.coins.domain.model.CoinDetail
import kotlinx.coroutines.flow.Flow

interface CoinRepository {
    suspend fun getCoins() : Flow<Resource<List<Coin>>>
    suspend fun getCoin(id: String) : Flow<Resource<CoinDetail>>
}