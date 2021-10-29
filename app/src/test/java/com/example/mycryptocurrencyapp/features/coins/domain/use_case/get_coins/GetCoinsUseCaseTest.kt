package com.example.mycryptocurrencyapp.features.coins.domain.use_case.get_coins


import com.example.mycryptocurrencyapp.features.coins.domain.repository.CoinRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetCoinsUseCaseTest {

    /**
     * SUT
     */
    private lateinit var getCoinsUseCase: GetCoinsUseCase

    //Dependencies
    private val coinRepository: CoinRepository = mock()

    @Before
    fun setup() {
        getCoinsUseCase = GetCoinsUseCase(coinRepository)
    }

    @Test
    fun getCoin_Success() = runBlockingTest {
        getCoinsUseCase()
        verify(coinRepository).getCoins()
    }

}