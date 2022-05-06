package com.renatus.testingwithmockwebsever.models.services.contract

import retrofit2.http.GET
import com.renatus.testingwithmockwebsever.models.responses.MovieCharacterResponse
import com.renatus.testingwithmockwebsever.models.responses.MovieDetailResponse
import retrofit2.Call
import retrofit2.http.Path

interface APIService {
    @GET("people/{number}/")
    fun getMovieCharacterData(@Path("number") postId: Int): Call<MovieCharacterResponse?>?

    @GET("films/{number}/")
    fun getMovieDetails(@Path("number") postId: Int): Call<MovieDetailResponse?>?
}