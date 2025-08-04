package com.example.royalcommissionforalulaapp_androidversion.ui.theme.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.royalcommissionforalulaapp_androidversion.repo.Repository
import com.example.royalcommissionforalulaapp_androidversion.repo.RepositoryImpl


class LoginViewModelFactory(private val repo: Repository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewmodel(repository = repo) as T
    }

}