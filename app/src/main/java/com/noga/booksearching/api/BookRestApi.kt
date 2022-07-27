package com.noga.booksearching.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BookRestApi{

    const val BASE_URL = "https://api.itbook.store/1.0/"

    //Creating retrofit builder
    val BookRestApi: Retrofit.Builder by lazy{
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
    }

    //Initializing apiService
    val apiService: ItBookApiService by lazy {
        BookRestApi.build().create(ItBookApiService::class.java)
    }
}