package com.example.royalcommissionforalulaapp_androidversion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.esri.arcgisruntime.ArcGISRuntimeEnvironment
import com.example.royalcommissionforalulaapp_androidversion.constants.Constants
import com.example.royalcommissionforalulaapp_androidversion.db.UserPreferencesImpl
import com.example.royalcommissionforalulaapp_androidversion.network.retrofit.RetrofitProviderImpl
import com.example.royalcommissionforalulaapp_androidversion.repo.RepositoryImpl
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.RoyalCommissionForAlulaApp_AndroidVersionTheme
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.home.view.HomeScreen
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.home.viewmodel.HomeViewModel
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.login.view.LoginScreen
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.login.viewmodel.LoginViewmodel

import com.example.royalcommissionforalulaapp_androidversion.ui.theme.map.view.MapViewComponent
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.map.viewmodel.MapViewModel
import com.example.royalcommissionforalulaapp_androidversion.utilities.Utilities

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ArcGISRuntimeEnvironment.setApiKey(Constants.arcGisMapKey)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val viewmodel = HomeViewModel(
                repo = RepositoryImpl(
                    RetrofitProviderImpl().getApiService(),
                    localService = UserPreferencesImpl.getInstance(applicationContext)
                )
            )

            RoyalCommissionForAlulaApp_AndroidVersionTheme {

                NavHost(
                    navController = navController,
                    startDestination = "login_screen"
                ) {
                    composable("login_screen") {
                        LoginScreen(navController = navController, viewmodel = LoginViewmodel(repository = RepositoryImpl(
                            RetrofitProviderImpl().getApiService(),
                            localService = UserPreferencesImpl.getInstance(applicationContext)
                        )))
                    }

                    composable("home_screen") {
                        HomeScreen(navController = navController, viewModel = viewmodel)
                    }
                }


            }
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RoyalCommissionForAlulaApp_AndroidVersionTheme {
        Greeting("Android")
    }
}

/*
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewmodel = YourViewModel() // or use hiltViewModel()

        setContent {
            val navController = rememberNavController()

            // Your app's root UI
            NavHost(
                navController = navController,
                startDestination = "login_screen"
            ) {
                composable("login_screen") {
                    LoginScreen(navController = navController, viewmodel = viewmodel)
                }

                composable("home_screen") {
                    HomeScreen(navController = navController)
                }
            }
        }
    }
}

 */