package com.example.currencytracker.api

import com.example.currencytracker.models.MarketModel
import retrofit2.http.GET
import retrofit2.Response


interface ApiInterface {
    @GET("data-api/v3/cryptocurrency/listing?start=1&limit=500")
    suspend fun getMarketData(): Response<MarketModel>
}