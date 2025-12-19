package com.example.mycryptocurrencyapp.features.coins.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mycryptocurrencyapp.common.ui.NavDestination
import com.example.mycryptocurrencyapp.common.ui.theme.MyCryptocurrencyAppTheme
import com.example.mycryptocurrencyapp.features.coins.presentation.coin_detail.CoinDetailScreen
import com.example.mycryptocurrencyapp.features.coins.presentation.coin_list.CoinListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoinsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyCryptocurrencyAppTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = NavDestination.CoinList,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable<NavDestination.CoinList> {
                            CoinListScreen(
                                onCoinClick = { coin ->
                                    navController.navigate(NavDestination.CoinDetail(coinId = coin.id))
                                }
                            )
                        }
                        composable<NavDestination.CoinDetail> {
                            CoinDetailScreen()
                        }
                    }
                }
            }
        }
    }
}
