package com.example.royalcommissionforalulaapp_androidversion.ui.theme.home.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.royalcommissionforalulaapp_androidversion.repo.Repository
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.home.model.Step
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel(val repo: Repository): ViewModel() {
    private val _progressSteps = MutableStateFlow<List<Step>?>(null)
    val progressSteps: StateFlow<List<Step>?> = _progressSteps

    private val _totalOfBuildings = MutableStateFlow<Int?>(0)
    val totalOfBuildings: StateFlow<Int?> = _totalOfBuildings


    suspend fun getProgress(){
        val response = repo.getProgress(token = "h5wJeT2/BqAMYdWINCoj4IUj0iG8XketPidZrjD7EWD7RkvrZQsr7o51Om9U74IfgwNUGnE/0Pg=")
        _progressSteps.value = response.steps
        _totalOfBuildings.value = response.steps.first().total
        Log.d("total", "getProgress: ${totalOfBuildings.value}")

    }
}


