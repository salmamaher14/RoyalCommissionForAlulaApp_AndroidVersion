package com.example.royalcommissionforalulaapp_androidversion.repo

import com.example.royalcommissionforalulaapp_androidversion.network.ApiService
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.login.model.LoginRequest
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.login.model.LoginResponse

class RepositoryImpl(private val apiService: ApiService): Repository {
    override suspend fun login(userData: LoginRequest): LoginResponse {
        return apiService.post(userData)
    }
}