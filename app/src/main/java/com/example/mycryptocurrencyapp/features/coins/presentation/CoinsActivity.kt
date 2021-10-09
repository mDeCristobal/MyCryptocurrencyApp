package com.example.mycryptocurrencyapp.features.coins.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mycryptocurrencyapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoinsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}