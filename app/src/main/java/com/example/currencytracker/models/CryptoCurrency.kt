package com.example.currencytracker.models

import java.io.Serializable

data class CryptoCurrency(
    val auditInfoList: List<AuditInfo>,
    val badges: List<Double>,
    val circulatingSupply: Double,
    val cmcRank: Double,
    val dateAdded: String,
    val id: Int,
    val isActive: Double,
    val isAudited: Boolean,
    val lastUpdated: String,
    val marketPairCount: Double,
    val maxSupply: Double,
    val name: String,
    val platform: Platform,
    val quotes: List<Quote>,
    val selfReportedCirculatingSupply: Double,
    val slug: String,
    val symbol: String,
    val tags: List<String>,
    val totalSupply: Double
): Serializable{ //Application crash ho raha tha to chat gpt ne bola, ye function implement karne ke liye
    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    override fun toString(): String {
        return super.toString()
    }
}