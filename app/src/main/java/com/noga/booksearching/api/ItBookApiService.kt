package com.noga.booksearching.api

import com.noga.booksearching.models.BookApiResponse
import com.noga.booksearching.models.ItBookDetail
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ItBookApiService {

    @GET("search/{query}/")
    suspend fun searchBooks(@Path("query") query: String): BookApiResponse

    @GET("new")
    suspend fun getNewBooks(): BookApiResponse

    @GET("books/{isbn13}")
    suspend fun getBookDetail(@Path("isbn13") isbn13: String): ItBookDetail

}