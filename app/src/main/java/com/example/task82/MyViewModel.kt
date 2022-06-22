package com.example.task82

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*


class MyViewModel : ViewModel() {

    val heroesLiveData = MutableLiveData<ArrayList<Hero>>()
    private lateinit var job: Job

    fun onCreate() {
        job = viewModelScope.launch(Dispatchers.IO) {
            heroesLiveData.postValue(Repository().getAllHeroes())
        }
    }

    fun onUpdate() {
        job = viewModelScope.launch(Dispatchers.IO) {
            heroesLiveData.postValue(Repository().updateHeroes())
        }
    }
}