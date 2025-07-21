package com.example.royalcommissionforalulaapp_androidversion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.ui.tooling.preview.Preview
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.RoyalCommissionForAlulaApp_AndroidVersionTheme

import com.example.royalcommissionforalulaapp_androidversion.ui.theme.login.view.Login

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
          //  window.statusBarColor = colorResource(R.color.white).toArgb()

            RoyalCommissionForAlulaApp_AndroidVersionTheme {
               Login()
            }
        }
    }
}
// change 1

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