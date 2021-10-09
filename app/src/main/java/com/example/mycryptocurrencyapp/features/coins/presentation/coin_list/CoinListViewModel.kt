package com.example.mycryptocurrencyapp.features.coins.presentation.coin_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycryptocurrencyapp.common.Resource
import com.example.mycryptocurrencyapp.features.coins.domain.use_case.get_coins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<CoinListState>(CoinListState.IsLoading())
    val state : StateFlow<CoinListState> = _state

     init {
         viewModelScope.launch {
             getCoins()
         }
    }

    private suspend fun getCoins() {
        getCoinsUseCase().onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = CoinListState.Success(result.data!!)
                }
                is Resource.Error -> {
                    _state.value = CoinListState.Error(result.message ?: "")
                }
                is Resource.Loading -> {
                    _state.value = CoinListState.IsLoading(true)
                }
            }
        }.launchIn(viewModelScope)
    }
}