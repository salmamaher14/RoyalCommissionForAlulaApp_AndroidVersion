package com.example.royalcommissionforalulaapp_androidversion.ui.theme.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.royalcommissionforalulaapp_androidversion.repo.Repository
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.login.model.LoginRequest
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.login.model.LoginResponse
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.login.model.UserData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewmodel(private val repository: Repository): ViewModel(){
    private  val _loginResult =  MutableStateFlow<Result<LoginResponse>?>(null)
            val loginResult : StateFlow<Result<LoginResponse>?> = _loginResult

    fun login(username: String, password: String) {
        viewModelScope.launch {
            try {
                val response = repository.login(LoginRequest(username, password))
                if (response.token != null) {
                    _loginResult.value = Result.success(response)
                    repository.saveUserData(UserData(token = response.token, name = response.name, role = response.role))

                } else {
                    _loginResult.value = Result.failure(Exception(response.message ?: "Unknown error"))
                }
            } catch (e: Exception) {
                _loginResult.value = Result.failure(e)
            }
        }
    }

    fun checkUserData(username: String, password: String): Boolean {
        return username.isEmpty() || password.isEmpty()
    }

}

