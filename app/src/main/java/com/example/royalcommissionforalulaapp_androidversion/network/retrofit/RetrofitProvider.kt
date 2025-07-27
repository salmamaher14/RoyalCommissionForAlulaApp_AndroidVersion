package com.example.royalcommissionforalulaapp_androidversion.network.retrofit

import com.example.royalcommissionforalulaapp_androidversion.network.ApiService

interface RetrofitProvider {
    fun getApiService(): ApiService
}