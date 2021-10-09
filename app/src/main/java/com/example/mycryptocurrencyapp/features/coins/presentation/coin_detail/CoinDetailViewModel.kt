package com.example.mycryptocurrencyapp.features.coins.presentation.coin_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycryptocurrencyapp.common.CoinDetailExtras.COIN_ID
import com.example.mycryptocurrencyapp.common.Resource
import com.example.mycryptocurrencyapp.features.coins.domain.use_case.get_coin.GetCoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinDetailUseCase: GetCoinUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow<CoinDetailState>(CoinDetailState.IsLoading())
    val state : StateFlow<CoinDetailState> = _state

    init {
        savedStateHandle.get<String>(COIN_ID)?.let { coinId ->
            viewModelScope.launch {
                getCoinDetail(coinId)
            }
        }
    }

    private suspend fun getCoinDetail(id: String) {
        getCoinDetailUseCase(id).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = CoinDetailState.Success(result.data!!)
                }
                is Resource.Error -> {
                    _state.value = CoinDetailState.Error(result.message ?: "")
                }
                is Resource.Loading -> {
                    _state.value = CoinDetailState.IsLoading(true)
                }
            }
        }.launchIn(viewModelScope)
    }

}