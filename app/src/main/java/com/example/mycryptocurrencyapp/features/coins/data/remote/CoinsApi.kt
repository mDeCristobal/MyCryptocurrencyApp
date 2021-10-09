package com.example.mycryptocurrencyapp.features.coins.data.remote

import com.example.mycryptocurrencyapp.features.coins.data.remote.dto.CoinDetailDto
import com.example.mycryptocurrencyapp.features.coins.data.remote.dto.CoinDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinsApi {

    @GET("v1/coins")
    suspend fun getCoins() : List<CoinDto>

    @GET("v1/coins/{id}")
    suspend fun getCoin(@Path("id") id: String) : CoinDetailDto

}