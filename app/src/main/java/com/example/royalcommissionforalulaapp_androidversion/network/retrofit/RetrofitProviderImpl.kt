package com.example.royalcommissionforalulaapp_androidversion.network.retrofit

import com.example.royalcommissionforalulaapp_androidversion.constants.Constants
import com.example.royalcommissionforalulaapp_androidversion.network.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitProviderImpl: RetrofitProvider {
    override fun getApiService(): ApiService {
         return Retrofit
            .Builder()
            .baseUrl(Constants.BASE_API)
            .addConverterFactory(GsonConverterFactory.create())
             .build()
             .create(ApiService::class.java)
    }

}

