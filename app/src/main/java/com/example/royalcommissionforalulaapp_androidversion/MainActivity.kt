package com.example.royalcommissionforalulaapp_androidversion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.esri.arcgisruntime.ArcGISRuntimeEnvironment
import com.example.royalcommissionforalulaapp_androidversion.constants.Constants

import com.example.royalcommissionforalulaapp_androidversion.ui.theme.RoyalCommissionForAlulaApp_AndroidVersionTheme
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.home.view.HomeScreen
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.home.viewmodel.HomeViewModel
import com.example.royalcommissionforalulaapp_androidversion.factory.ViewModelFactory

import com.example.royalcommissionforalulaapp_androidversion.ui.theme.login.view.LoginScreen
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.login.viewmodel.LoginViewmodel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ArcGISRuntimeEnvironment.setApiKey(Constants.MAP_KEY)

        val app = (application as App)
        val repo = app.repository


        enableEdgeToEdge()

        setContent {

            val navController = rememberNavController()

            RoyalCommissionForAlulaApp_AndroidVersionTheme {

                NavHost(
                    navController = navController,
                    startDestination = app.checkIfUserLogged()
                ) {

                    composable("login_screen") {
                        val viewmodel: LoginViewmodel = viewModel(factory = ViewModelFactory(repo))
                        LoginScreen(navController = navController, viewmodel= viewmodel)
                    }

                    composable(route = "home_screen"){
                        val viewmodel: HomeViewModel = viewModel(factory = ViewModelFactory(repo))
                        HomeScreen(navController = navController, viewModel = viewmodel)
                    }


                }


            }
        }
    }
}





