package ir.borjali.animechan.data.remote

import ir.borjali.animechan.domain.model.Quote
import retrofit2.Response
import retrofit2.http.GET

interface AnimeApi {

    @GET("/api/quotes")
    suspend fun getRandomQuote(): Response<List<Quote>>
}