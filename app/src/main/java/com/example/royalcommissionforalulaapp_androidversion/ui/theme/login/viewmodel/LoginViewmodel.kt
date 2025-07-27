package com.example.royalcommissionforalulaapp_androidversion.ui.theme.login.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.royalcommissionforalulaapp_androidversion.repo.Repository
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.login.model.LoginRequest
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.login.model.LoginResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewmodel(private val repository: Repository): ViewModel(){
    private  val _loginResult =  MutableStateFlow<Result<LoginResponse>?>(null)
            val loginResult : StateFlow<Result<LoginResponse>?> = _loginResult

    fun login(username: String, password: String){
      viewModelScope.launch {
          try {
              val response = repository.login(LoginRequest(username,password))
              Log.d("response", "login: ${response.name}")
              _loginResult.value = Result.success(response)
          } catch (e: Exception){
              _loginResult.value = Result.failure(e)
          }
      }
    }
}

