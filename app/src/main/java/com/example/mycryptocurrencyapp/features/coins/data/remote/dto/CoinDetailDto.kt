package com.example.mycryptocurrencyapp.features.coins.data.remote.dto


import com.example.mycryptocurrencyapp.features.coins.domain.model.CoinDetail
import com.google.gson.annotations.SerializedName

data class CoinDetailDto(
    @SerializedName("description")
    val description: String?,
    @SerializedName("development_status")
    val developmentStatus: String,
    @SerializedName("first_data_at")
    val firstDataAt: String,
    @SerializedName("hardware_wallet")
    val hardwareWallet: Boolean,
    @SerializedName("hash_algorithm")
    val hashAlgorithm: String,
    @SerializedName("id")
    val id: String?,
    @SerializedName("is_active")
    val isActive: Boolean,
    @SerializedName("is_new")
    val isNew: Boolean,
    @SerializedName("last_data_at")
    val lastDataAt: kotlin.String,
    @SerializedName("links")
    val links: Links,
    @SerializedName("links_extended")
    val linksExtended: List<Any>,
    @SerializedName("message")
    val message: kotlin.String,
    @SerializedName("name")
    val name: kotlin.String,
    @SerializedName("open_source")
    val openSource: Boolean,
    @SerializedName("org_structure")
    val orgStructure: kotlin.String,
    @SerializedName("proof_type")
    val proofType: kotlin.String,
    @SerializedName("rank")
    val rank: Int,
    @SerializedName("started_at")
    val startedAt: String?,
    @SerializedName("symbol")
    val symbol: String?,
    @SerializedName("tags")
    val tags: List<Tag>?,
    @SerializedName("team")
    val team: List<TeamMember>?,
    @SerializedName("type")
    val type: String,
    @SerializedName("whitepaper")
    val whitepaper: Whitepaper
)

fun CoinDetailDto.toCoinDetail() =
    CoinDetail(
        id = id ?: "",
        name = name ?: "",
        description = description ?: "",
        isActive = isActive?: false,
        rank = rank ?: 0,
        symbol = symbol ?: "",
        tags = tags?.let { it.map { it.name } } ?: emptyList(),
        team = team ?: emptyList()
    )
