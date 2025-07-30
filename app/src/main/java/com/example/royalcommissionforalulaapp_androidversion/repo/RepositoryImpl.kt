package com.example.royalcommissionforalulaapp_androidversion.repo

import com.example.royalcommissionforalulaapp_androidversion.db.UserPreferences
import com.example.royalcommissionforalulaapp_androidversion.network.ApiService
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.home.model.BuildingData
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.home.model.ProgressData
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.login.model.LoginRequest
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.login.model.LoginResponse
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.login.model.UserData
import okhttp3.ResponseBody
import retrofit2.Response

class RepositoryImpl(private val apiService: ApiService, private val localService: UserPreferences): Repository {

    override suspend fun login(userData: LoginRequest): LoginResponse {
        return apiService.post(userData)
    }

     override suspend fun getProgress(token: String): ProgressData {
         return apiService.getProgress(token)
     }

    override suspend fun getBuilding(buildingId: String, token: String): BuildingData {
        return apiService.getBuilding(buildingId, token)
    }

    override fun getStoredUserData(): UserData {
        return  localService.getData()
    }

    override suspend fun downloadFile(fileUrl: String): Response<ResponseBody> {
        return apiService.downloadFile(fileUrl)
    }

    override fun saveUserData(userData: UserData) {
        return localService.saveData(userData)
    }


}