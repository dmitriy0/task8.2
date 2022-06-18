package com.example.task82

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url


interface ApiService {
    @GET
    fun loadHero(@Url route: String): Call<ArrayList<Hero>>?
}