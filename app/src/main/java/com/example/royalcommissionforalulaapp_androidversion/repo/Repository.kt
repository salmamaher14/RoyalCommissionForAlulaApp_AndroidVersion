package com.example.royalcommissionforalulaapp_androidversion.repo

import com.example.royalcommissionforalulaapp_androidversion.ui.theme.home.model.BuildingData
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.home.model.ProgressData
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.login.model.LoginRequest
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.login.model.LoginResponse
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.login.model.UserData
import okhttp3.ResponseBody
import retrofit2.Response

interface Repository {
    suspend fun login(userData: LoginRequest): LoginResponse
    suspend fun getProgress(): ProgressData?
    suspend fun getBuilding(buildingId: String): BuildingData?
    fun saveUserData(userData: UserData)
    fun getStoredUserData(): UserData
    fun clearUserData()
}