package com.example.royalcommissionforalulaapp_androidversion.repo

import com.example.royalcommissionforalulaapp_androidversion.ui.theme.home.model.ProgressData
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.login.model.LoginRequest
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.login.model.LoginResponse
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.login.model.UserData

interface Repository {
    suspend fun login(userData: LoginRequest): LoginResponse
    suspend fun getProgress(token: String): ProgressData
    fun saveUserData(userData: UserData)
    fun getStoredUserData(): UserData

}