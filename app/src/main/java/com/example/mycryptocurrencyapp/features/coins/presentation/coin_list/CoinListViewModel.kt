package com.example.mycryptocurrencyapp.features.coins.presentation.coin_list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycryptocurrencyapp.common.Resource
import com.example.mycryptocurrencyapp.features.coins.domain.use_case.get_coins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CoinListState())
    val state: StateFlow<CoinListState> = _state

    init {
        getCoins()
    }

    fun onEvent(event: CoinListUserEvent) {
        when (event) {
            is CoinListUserEvent.OnRefresh -> {
                getCoins()
            }
            is CoinListUserEvent.OnCoinClick -> {
                // Aquí manejaríamos la navegación más adelante
            }
        }
    }

    private fun getCoins() {
        viewModelScope.launch {
            getCoinsUseCase().onEach { result ->
                Log.d(TAG, "getCoins result: $result")
                when (result) {
                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                coins = result.data ?: emptyList(),
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

    companion object {
        const val TAG = "CoinListViewModel"
    }
}
