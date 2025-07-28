package com.example.royalcommissionforalulaapp_androidversion.repo

import com.example.royalcommissionforalulaapp_androidversion.network.ApiService
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.home.model.ProgressData
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.login.model.LoginRequest
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.login.model.LoginResponse

 class RepositoryImpl(private val apiService: ApiService): Repository {

    override suspend fun login(userData: LoginRequest): LoginResponse {
        return apiService.post(userData)
    }

     override suspend fun getProgress(token: String): ProgressData {
         return apiService.get(token)
     }


}