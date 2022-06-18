package com.example.task82

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type


class HeroesListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_heroes_list, container, false)

        val about = view.findViewById<Button>(R.id.buttonAbout)
        about.setOnClickListener {
            App.INSTANCE.router.navigateTo(Screens.about())
        }

        recyclerView = view.findViewById(R.id.recyclerview)
        swipeRefresh = view.findViewById(R.id.swipe_refresh)
        swipeRefresh.setOnRefreshListener {
            requestToApi()
        }

        val sharedPreferences = requireContext().getSharedPreferences("json", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()

        if (sharedPreferences.contains("heroes")) {
            val jsonString = sharedPreferences.getString("heroes", "")
            val type: Type = object : TypeToken<ArrayList<Hero?>?>() {}.type
            recyclerView.adapter = Adapter(Gson().fromJson(jsonString, type), requireActivity())
            Toast.makeText(requireContext(), "fromShared", Toast.LENGTH_LONG).show()

        } else {
            requestToApi()
            Toast.makeText(requireContext(), "fromApi", Toast.LENGTH_LONG).show()
        }
        return view
    }

    private fun requestToApi() {
        val httpClient = OkHttpClient.Builder()
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://akabab.github.io/superhero-api/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
        val apiService: ApiService = retrofit.create(ApiService::class.java)

        val call: Call<ArrayList<Hero>>? = apiService.loadHero("all.json")
        call!!.enqueue(object : Callback<ArrayList<Hero>> {

            override fun onResponse(
                call: Call<ArrayList<Hero>>,
                response: Response<ArrayList<Hero>>
            ) {
                requireActivity().runOnUiThread {
                    val responseBody = response.body()!!
                    editor.putString("heroes", Gson().toJson(responseBody).toString())
                    editor.apply()
                    recyclerView.adapter = Adapter(responseBody, requireActivity())
                    swipeRefresh.isRefreshing = false
                }
            }

            override fun onFailure(call: Call<ArrayList<Hero>>, t: Throwable) {
            }

        })
    }
}