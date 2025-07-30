package com.example.royalcommissionforalulaapp_androidversion.network

import com.example.royalcommissionforalulaapp_androidversion.ui.theme.home.model.BuildingData
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.home.model.ProgressData
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.login.model.LoginRequest
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.login.model.LoginResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.Url


interface ApiService {
    @POST("DoLogin")
    suspend fun post(@Body userInfo: LoginRequest): LoginResponse

    @GET("GetProgress")
    suspend fun getProgress(@Header("Authorization") token: String): ProgressData

    @GET("GetBuilding")
    suspend fun getBuilding(@Query("gisId") buildingId: String, @Header("Authorization") token: String): BuildingData

    @GET
    suspend fun downloadFile(@Url fileUrl: String): Response<ResponseBody>

}

