package com.example.royalcommissionforalulaapp_androidversion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.ui.tooling.preview.Preview
import com.esri.arcgisruntime.ArcGISRuntimeEnvironment
import com.example.royalcommissionforalulaapp_androidversion.constants.Constants
import com.example.royalcommissionforalulaapp_androidversion.db.UserPreferencesImpl
import com.example.royalcommissionforalulaapp_androidversion.network.retrofit.RetrofitProviderImpl
import com.example.royalcommissionforalulaapp_androidversion.repo.RepositoryImpl
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.RoyalCommissionForAlulaApp_AndroidVersionTheme
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.home.view.HomeScreen
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.home.viewmodel.HomeViewModel

import com.example.royalcommissionforalulaapp_androidversion.ui.theme.map.view.MapViewComponent
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.map.viewmodel.MapViewModel
import com.example.royalcommissionforalulaapp_androidversion.utilities.Utilities

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ArcGISRuntimeEnvironment.setApiKey(Constants.arcGisMapKey)
        enableEdgeToEdge()
        setContent {
            RoyalCommissionForAlulaApp_AndroidVersionTheme {
                Utilities.getFileFormat("uiehdixhdhihdiu - img")
                HomeScreen(
                    viewModel = HomeViewModel(repo = RepositoryImpl(
                        RetrofitProviderImpl().getApiService(),
                        localService = UserPreferencesImpl.getInstance(applicationContext)
                    ))
                )

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