package com.example.task82

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type


class Repository {

    private var jsonString: String? = null
    private val type: Type = object : TypeToken<ArrayList<Hero?>?>() {}.type
    private val sharedPreferences: SharedPreferences =
        App.INSTANCE.applicationContext.getSharedPreferences("json", Context.MODE_PRIVATE)
    private val editor = sharedPreferences.edit()

    fun getAllHeroes(): ArrayList<Hero>? {

        return if (sharedPreferences.contains("heroes")) {
            jsonString = sharedPreferences.getString("heroes", "")
            Gson().fromJson(jsonString, type)
        } else {
            updateHeroes()
        }
    }

    fun updateHeroes(): ArrayList<Hero>? {
        val response = requestToApi() ?: return null
        saveToSP(response)
        return response
    }

    private fun requestToApi(): ArrayList<Hero>? {
        return try {
            val httpClient = OkHttpClient.Builder()
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("https://akabab.github.io/superhero-api/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build()
            val apiService: ApiService = retrofit.create(ApiService::class.java)

            val call: Call<ArrayList<Hero>>? = apiService.loadHero("all.json")
            call!!.execute().body()!!
        } catch (e: Exception) {
            null
        }
    }

    fun saveToSP(response: ArrayList<Hero>?) {
        editor.putString("heroes", Gson().toJson(response).toString())
        editor.apply()
    }
}