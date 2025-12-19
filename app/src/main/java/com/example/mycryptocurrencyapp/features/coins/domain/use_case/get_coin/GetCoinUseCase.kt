package com.example.mycryptocurrencyapp.features.coins.domain.use_case.get_coin

import com.example.mycryptocurrencyapp.common.Resource
import com.example.mycryptocurrencyapp.features.coins.domain.model.CoinDetail
import com.example.mycryptocurrencyapp.features.coins.domain.repository.CoinRepository
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository
) {

    suspend operator fun invoke(id: String): Resource<CoinDetail> = repository.getCoin(id)

}