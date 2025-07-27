package com.example.royalcommissionforalulaapp_androidversion.repo

import com.example.royalcommissionforalulaapp_androidversion.ui.theme.login.model.LoginRequest
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.login.model.LoginResponse

interface Repository {
    suspend fun login(userData: LoginRequest): LoginResponse
}