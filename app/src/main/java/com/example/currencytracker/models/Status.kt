package com.example.currencytracker.models

data class Status(
    val credit_count: Int,
    val elapsed: String,
    val error_code: String,
    val error_message: String,
    val timestamp: String
)