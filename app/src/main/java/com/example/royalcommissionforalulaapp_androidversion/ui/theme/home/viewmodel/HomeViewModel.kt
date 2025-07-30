package com.example.royalcommissionforalulaapp_androidversion.ui.theme.home.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.royalcommissionforalulaapp_androidversion.repo.Repository
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.home.model.Step
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.sheets.model.Page
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

class HomeViewModel(val repo: Repository): ViewModel() {
    private val _progressSteps = MutableStateFlow<List<Step>?>(null)
    val progressSteps: StateFlow<List<Step>?> = _progressSteps

    private val _buildingFiles = MutableStateFlow<List<Page>?>(null)
    val buildingFiles: StateFlow<List<Page>?> = _buildingFiles


    private val _totalOfBuildings = MutableStateFlow<Int?>(0)
    val totalOfBuildings: StateFlow<Int?> = _totalOfBuildings


    suspend fun getProgress(){
        val response = repo.getProgress(token = "h5wJeT2/BqAMYdWINCoj4IUj0iG8XketPidZrjD7EWD7RkvrZQsr7o51Om9U74IfgwNUGnE/0Pg=")
        _progressSteps.value = response.steps
        _totalOfBuildings.value = response.steps.first().total
    }

    suspend fun getBuilding(){
        val response = repo.getBuilding(buildingId = "16", token = "h5wJeT2/BqAMYdWINCoj4IUj0iG8XketPidZrjD7EWD7RkvrZQsr7o51Om9U74IfgwNUGnE/0Pg=")
        _buildingFiles.value = response.pages

    }

    suspend fun downloadFile(context: Context) {
        val response = repo.downloadFile(fileUrl = "https://itqan-consultant.com/s3/Rcu/2025/4/30/638815977779536959.pdf")
        val inputStream = response.body()?.byteStream()
        inputStream?.let { stream ->
            saveFileLocally(context, inputStream, fileName = "aser")
        }
        Log.d("downloadFile", "downloadFile: ${response.isSuccessful}")
    }


    private fun saveFileLocally(context: Context, inputStream: InputStream, fileName: String) {
        val file = File(context.cacheDir, fileName)

        inputStream.use { input ->
            FileOutputStream(file).use { output ->
                input.copyTo(output)
            }
        }

        Log.d("downloadFile", "File saved to ${file.absolutePath}")
    }

}


