package com.example.royalcommissionforalulaapp_androidversion.db

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit

class UserPreferences private constructor(context: Context) {

    companion object {
        @Volatile
        private var instance: UserPreferences? = null


        fun getInstance(context: Context): UserPreferences {
            return instance ?: synchronized(this) {
                instance ?: UserPreferences(context.applicationContext).also { instance = it }
            }
        }

        private const val PREFS_NAME = "user_prefs"
        private const val KEY_TOKEN = "user_token"
        private const val KEY_NAME = "user_name"
        private const val KEY_ROLE = "user_role"
    }

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun saveUserData(token: String, name: String, role: String) {
        sharedPreferences.edit().apply {
            putString(KEY_TOKEN, token)
            putString(KEY_NAME, name)
            putString(KEY_ROLE, role)
            apply()
        }
    }

    fun getUserToken(): String? = sharedPreferences.getString(KEY_TOKEN, null)
    fun getUserName(): String? = sharedPreferences.getString(KEY_NAME, null)
    fun getUserRole(): String? = sharedPreferences.getString(KEY_ROLE, null)

    fun clearUserData() {
        sharedPreferences.edit { clear() }
        Log.d("after clearing data", "clearUserData: ${getUserToken()}")
    }
}
