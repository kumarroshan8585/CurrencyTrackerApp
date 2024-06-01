package com.example.currencytracker.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiUtilities {
//In Kotlin, we often define the Retrofit builder in an object to
    // leverage the benefits of the Singleton pattern.
    // Here are the main reasons for doing so:
    //1)Singleton Pattern: Using an object in Kotlin automatically makes the
    // Retrofit instance a singleton.
    // This means there is only one instance of the Retrofit
    // object throughout the application,
    // ensuring efficient use of resources and consistent state management

    //2) Defining the Retrofit builder in an object makes it globally accessible
    fun getInstance(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(" https://api.coinmarketcap.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}