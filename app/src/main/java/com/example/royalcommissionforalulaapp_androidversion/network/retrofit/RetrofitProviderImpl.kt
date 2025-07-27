package com.example.royalcommissionforalulaapp_androidversion.network.retrofit

import android.util.Log
import com.example.royalcommissionforalulaapp_androidversion.constants.Constants
import com.example.royalcommissionforalulaapp_androidversion.network.ApiService
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.login.model.LoginRequest
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitProviderImpl: RetrofitProvider {
    override fun getApiService(): ApiService {
         return Retrofit
            .Builder()
            .baseUrl(Constants.base_api)
            .addConverterFactory(GsonConverterFactory.create())
             .build()
             .create(ApiService::class.java)
    }


}

class  TestRetrofitProviderImpl{
    suspend fun postUser(){
        val response =RetrofitProviderImpl().getApiService().post(userInfo = LoginRequest(username = "admin@rcu.com" , password = "admin@123"))
        Log.d("post", "postUser: ${response.name}")
        println("response: ${response.name} and response isOk: ${response.isOk} and role: ${response.role}")

    }
}