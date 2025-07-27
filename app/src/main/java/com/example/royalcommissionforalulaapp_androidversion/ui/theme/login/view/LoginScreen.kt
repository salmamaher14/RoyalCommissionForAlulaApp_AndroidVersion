package com.example.royalcommissionforalulaapp_androidversion.ui.theme.login.view

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.royalcommissionforalulaapp_androidversion.R
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.components.ButtonComponent
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.components.TextFieldComponent
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.login.viewmodel.LoginViewmodel


@Composable
fun Login(viewmodel: LoginViewmodel) {
    val loginResult by viewmodel.loginResult.collectAsState()




    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(),

        contentAlignment = Alignment.Center
    ) {

        Image(
            painter = painterResource(R.drawable.mob),
            contentDescription = "login background",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )

        MainBox(viewmodel)

        CopyRightsLogo(
            modifier = Modifier
                .align(Alignment.BottomCenter)
        )
    }
}

/*
    loginResult?.onSuccess {
        Log.d("result", "Login: ${it.name}")
        Text("Success: ${it.name}")
    }?.onFailure {
        Text("fialure: ${it.message}")
    }
 */





@Composable
fun MainBox(viewmodel: LoginViewmodel) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .background(Color.Black.copy(0.3F))
    ) {

        Column {
            LogoWithTitle()
            UserData(viewmodel)
        }

    }

}


@Composable
fun LogoWithTitle() {
    Column {

        AppLogo()
        AppTitle()
    }
}

@Composable
fun AppLogo () {
    Image(
        painter = painterResource(R.drawable.app_logo),
        contentDescription = "app_logo",
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .height(50.dp)
        ,
        alignment = Alignment.Center
    )
}

@Composable
fun CopyRightsLogo(modifier: Modifier) {
    Image(
        painter = painterResource(R.drawable.rights),
        contentDescription = "rights_logo",
        modifier = modifier
            .size(width = 200.dp, height = 200.dp)
            .padding(vertical = 12.dp)
        ,

        alignment = Alignment.Center
    )
}

@Composable
fun AppTitle() {
    Text( "Project of Old Town Building Registration Documentation",
        textAlign = TextAlign.Center,
        fontSize = 20.sp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
        ,
        color = colorResource(R.color.main_color),
        fontFamily = FontFamily(
            Font(R.font.text_bold)
        )
    )

}

@Composable
fun UserData(viewmodel: LoginViewmodel) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val showDialog by remember { mutableStateOf(true) }
    val loginResult by viewmodel.loginResult.collectAsState()

    Column(
        modifier = Modifier.padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ) {
        TextFieldComponent(userInput = username ,
            placeholder = "username"
        ) {
            username = it
        }

        TextFieldComponent(userInput = password,
            placeholder = "password"
        ) {
            password = it
        }


        LoginButton(
            modifier = Modifier
                .padding(vertical = 20.dp),
            onClick = {
                viewmodel.login(username, password)
            }
        )

    }

    if (showDialog){

    }



}

@Composable
fun LoginButton(modifier: Modifier, onClick: () -> Unit) {
    ButtonComponent(
        modifier = modifier
            .fillMaxWidth(),

        title = "Login",
        onClick = onClick
    )
}