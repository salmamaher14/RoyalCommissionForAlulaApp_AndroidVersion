package com.example.royalcommissionforalulaapp_androidversion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.royalcommissionforalulaapp_androidversion.ui.theme.RoyalCommissionForAlulaApp_AndroidVersionTheme
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.home.view.HomeScreen
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.home.viewmodel.HomeViewModel
import com.example.royalcommissionforalulaapp_androidversion.factory.ViewModelFactory

import com.example.royalcommissionforalulaapp_androidversion.ui.theme.login.view.LoginScreen
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.login.viewmodel.LoginViewmodel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repo = (application as App).repository


        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            RoyalCommissionForAlulaApp_AndroidVersionTheme {

                NavHost(
                    navController = navController,
                    startDestination = "login_screen"
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

//  //ArcGISRuntimeEnvironment.setApiKey(Constants.arcGisMapKey)

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