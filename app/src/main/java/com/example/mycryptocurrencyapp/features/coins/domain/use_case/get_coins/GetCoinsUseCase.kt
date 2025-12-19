package com.example.mycryptocurrencyapp.features.coins.domain.use_case.get_coins

import com.example.mycryptocurrencyapp.common.Resource
import com.example.mycryptocurrencyapp.features.coins.domain.model.Coin
import com.example.mycryptocurrencyapp.features.coins.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: CoinRepository
) {

    suspend operator fun invoke() : Flow<Resource<List<Coin>>> = {
      return  repository.getCoins()
    }

}