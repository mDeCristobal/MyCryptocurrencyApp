package com.example.mycryptocurrencyapp.features.coins.data.repository

import com.example.mycryptocurrencyapp.common.CustomErrorsEnum
import com.example.mycryptocurrencyapp.common.Resource
import com.example.mycryptocurrencyapp.features.coins.data.remote.CoinsApi
import com.example.mycryptocurrencyapp.features.coins.data.remote.dto.toCoin
import com.example.mycryptocurrencyapp.features.coins.data.remote.dto.toCoinDetail
import com.example.mycryptocurrencyapp.features.coins.domain.model.Coin
import com.example.mycryptocurrencyapp.features.coins.domain.model.CoinDetail
import com.example.mycryptocurrencyapp.features.coins.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val coinsApi: CoinsApi
) : CoinRepository {

    override suspend fun getCoins(): Resource<List<Coin>>  {
        return try {
            val coins = coinsApi.getCoins().map { it.toCoin() }
            Resource.Success(coins)
        } catch (e: HttpException) {
            Resource.Error(e.localizedMessage ?: "An unexpected error ocurred")
        } catch (e: IOException) {
            Resource.Error(CustomErrorsEnum.NO_INTERNET.name)
        }
    }


    override suspend fun getCoin(id: String): Resource<CoinDetail>  {
       return try {
            val coin = coinsApi.getCoin(id).toCoinDetail()
            Resource.Success(coin)
        } catch (e: HttpException) {
            Resource.Error(e.localizedMessage ?: "An unexpected error ocurred")
        } catch (e: IOException) {
            Resource.Error(CustomErrorsEnum.NO_INTERNET.name)
        }
    }
}