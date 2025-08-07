package com.example.royalcommissionforalulaapp_androidversion.ui.theme.home.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.royalcommissionforalulaapp_androidversion.repo.Repository
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.home.model.Step
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow



class HomeViewModel(private val repo: Repository): ViewModel() {
    private val _progressSteps = MutableStateFlow<List<Step>?>(null)
    val progressSteps: StateFlow<List<Step>?> = _progressSteps

    private val _totalOfBuildings = MutableStateFlow<Int?>(null)
    val totalOfBuildings: StateFlow<Int?> = _totalOfBuildings


    suspend fun getProgress(){
        val response = repo.getProgress()
        _progressSteps.value = response?.steps
        _totalOfBuildings.value = response?.steps?.first()?.total
    }


    fun clearUserData(){
        repo.clearUserData()
    }
    fun getUserName(): String?{
        return repo.getStoredUserData().name
    }




}


