package com.example.mycryptocurrencyapp.features.coins.presentation.coin_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.mycryptocurrencyapp.common.Resource
import com.example.mycryptocurrencyapp.common.ui.NavDestination
import com.example.mycryptocurrencyapp.features.coins.domain.use_case.get_coin.GetCoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinDetailUseCase: GetCoinUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(CoinDetailState())
    val state: StateFlow<CoinDetailState> = _state

    private val coinId: String = savedStateHandle.toRoute<NavDestination.CoinDetail>().coinId

    init {
        getCoinDetail(coinId)
    }

    private fun getCoinDetail(id: String) {
        viewModelScope.launch {
            when (val result = getCoinDetailUseCase(id)) {
                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            coin = result.data,
                            isLoading = false,
                            error = ""
                        )
                    }
                }

                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            error = result.message ?: "An unexpected error occurred",
                            isLoading = false
                        )
                    }
                }

                is Resource.Loading -> {
                    _state.update { it.copy(isLoading = true) }
                }
            }
        }
    }
}
