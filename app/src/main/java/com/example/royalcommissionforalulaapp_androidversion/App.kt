package com.example.royalcommissionforalulaapp_androidversion

import android.app.Application
import com.example.royalcommissionforalulaapp_androidversion.db.UserPreferencesImpl
import com.example.royalcommissionforalulaapp_androidversion.network.retrofit.RetrofitProviderImpl
import com.example.royalcommissionforalulaapp_androidversion.repo.Repository
import com.example.royalcommissionforalulaapp_androidversion.repo.RepositoryImpl

class App: Application() {
    lateinit var repository: Repository

    override fun onCreate() {
        super.onCreate()
        val apiService = RetrofitProviderImpl().getApiService()
        val localService = UserPreferencesImpl.getInstance(applicationContext)

        repository = RepositoryImpl(apiService,localService)
    }


    fun checkIfUserLogged(): String {
        return if(!repository.getStoredUserData().token.isNullOrEmpty()){
            "home_screen"
        }else{
            "login_screen"
        }
    }


}

