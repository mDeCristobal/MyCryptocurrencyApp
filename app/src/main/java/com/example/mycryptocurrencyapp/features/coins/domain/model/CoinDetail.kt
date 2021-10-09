package com.example.mycryptocurrencyapp.features.coins.domain.model

import com.example.mycryptocurrencyapp.features.coins.data.remote.dto.TeamMember


data class CoinDetail(
    val id: String,
    val isActive: Boolean,
    val description: String,
    val name: String,
    val rank: Int,
    val symbol: String,
    val tags: List<String>,
    val team: List<TeamMember>
    )
