package com.example.mycryptocurrencyapp.features.coins.domain.use_case.get_coin

import com.example.mycryptocurrencyapp.features.coins.domain.repository.CoinRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class GetCoinUseCaseTest {

    /**
     * SUT
     */
    private lateinit var getCoinUseCase: GetCoinUseCase

    //Dependencies
    private val coinRepository: CoinRepository = mock()

    //Data Stubs
    private val id = "123"

    @Before
    fun setup() {
        getCoinUseCase = GetCoinUseCase(coinRepository)
    }

    @Test
    fun getCoin_Success() = runBlockingTest {
        getCoinUseCase(id)
        verify(coinRepository).getCoin(id)
    }

}