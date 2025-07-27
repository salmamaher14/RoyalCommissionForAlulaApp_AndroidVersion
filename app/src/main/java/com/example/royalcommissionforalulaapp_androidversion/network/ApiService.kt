package com.example.royalcommissionforalulaapp_androidversion.network

import com.example.royalcommissionforalulaapp_androidversion.ui.theme.login.model.LoginRequest
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.login.model.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("DoLogin")
    suspend fun post(@Body userInfo: LoginRequest): LoginResponse
}

