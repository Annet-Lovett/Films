package com.practicum.films

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface FilmsApi {
    @GET("/en/API/SearchMovie/k_zcuw1ytf/{expression}")
    fun findFilm(@Path("expression") expression: String)
            : Call<FilmsResponse>
}