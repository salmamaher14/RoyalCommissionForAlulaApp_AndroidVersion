package com.example.royalcommissionforalulaapp_androidversion.repo

import android.util.Log
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

     override suspend fun getProgress(): ProgressData? {
         val token = getStoredUserData().token

         return try {
              if (!token.isNullOrEmpty()){
                 apiService.getProgress(token)
             }else{
                 null
             }
         }catch (e: Exception){
             Log.d("getProgress", "getProgress: $e")
             null

         }

     }

    override suspend fun getBuilding(buildingId: String): BuildingData? {
        val token = getStoredUserData().token
        return if (!token.isNullOrEmpty()){
            apiService.getBuilding(buildingId, token)

        }else{
            null
        }

    }

    override fun getStoredUserData(): UserData {
        return  localService.getData()
    }





    override fun saveUserData(userData: UserData) {
        return localService.saveData(userData)
    }

    override fun clearUserData() {
        localService.clearData()
    }


}