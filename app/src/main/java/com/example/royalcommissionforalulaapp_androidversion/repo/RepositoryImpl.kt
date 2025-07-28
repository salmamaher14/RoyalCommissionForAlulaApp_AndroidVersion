package com.example.royalcommissionforalulaapp_androidversion.repo

import com.example.royalcommissionforalulaapp_androidversion.db.UserPreferences
import com.example.royalcommissionforalulaapp_androidversion.network.ApiService
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.home.model.ProgressData
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.login.model.LoginRequest
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.login.model.LoginResponse
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.login.model.UserData

class RepositoryImpl(private val apiService: ApiService, private val localService: UserPreferences): Repository {

    override suspend fun login(userData: LoginRequest): LoginResponse {
        return apiService.post(userData)
    }

     override suspend fun getProgress(token: String): ProgressData {
         return apiService.get(token)
     }

    override fun getStoredUserData(): UserData {
        return  localService.getData()
    }

    override fun saveUserData(userData: UserData) {
        return localService.saveData(userData)
    }


}