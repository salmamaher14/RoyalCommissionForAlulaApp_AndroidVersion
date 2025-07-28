package com.example.royalcommissionforalulaapp_androidversion.db

import android.content.Context
import android.content.SharedPreferences

import com.example.royalcommissionforalulaapp_androidversion.ui.theme.login.model.UserData
import androidx.core.content.edit


interface UserPreferences {
    fun saveData(userData: UserData)
    fun getData(): UserData
    fun clearData()
}

class UserPreferencesImpl private constructor(context: Context): UserPreferences {

    companion object {
        @Volatile
        private var instance: UserPreferences? = null


        fun getInstance(context: Context): UserPreferences {
            return instance ?: synchronized(this) {
                instance ?: UserPreferencesImpl(context.applicationContext).also { instance = it }
            }
        }

        private const val PREFS_NAME = "user_prefs"
        private const val KEY_TOKEN = "user_token"
        private const val KEY_NAME = "user_name"
        private const val KEY_ROLE = "user_role"
    }

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    override fun saveData(userData: UserData) {
        sharedPreferences.edit().apply {
            putString(KEY_TOKEN, userData.token)
            putString(KEY_NAME, userData.name)
            putString(KEY_ROLE, userData.role)
            apply()
        }
    }

    override fun getData(): UserData {
        val token = sharedPreferences.getString(KEY_TOKEN, null)
        val name = sharedPreferences.getString(KEY_NAME, null)
        val role = sharedPreferences.getString(KEY_ROLE, null)

        return UserData(token, name, role)
    }

    override fun clearData() {
        sharedPreferences.edit { clear() }
    }


}

