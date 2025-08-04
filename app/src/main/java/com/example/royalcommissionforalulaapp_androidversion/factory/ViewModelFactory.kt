package com.example.royalcommissionforalulaapp_androidversion.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.royalcommissionforalulaapp_androidversion.repo.Repository
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.home.viewmodel.HomeViewModel
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.login.viewmodel.LoginViewmodel

class ViewModelFactory(private  val repository: Repository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(LoginViewmodel::class.java) -> LoginViewmodel(repository = repository) as T
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(repo = repository) as T

            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }

}

