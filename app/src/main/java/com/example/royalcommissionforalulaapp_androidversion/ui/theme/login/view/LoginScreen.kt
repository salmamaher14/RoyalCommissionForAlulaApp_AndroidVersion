package com.example.royalcommissionforalulaapp_androidversion.ui.theme.login.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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


@Composable
fun Login() {

    Box(
        modifier = Modifier.
        fillMaxSize()
            .padding()
        ,

        contentAlignment = Alignment.Center
    ){
        Image(
            painter = painterResource(R.drawable.mob),
            contentDescription = "login background",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )

        MainBox()

        CopyRightsLogo(
            modifier = Modifier
                .align(Alignment.BottomCenter)
            // .padding(bottom = 16.dp)
        )
    }

}


@Composable
fun MainBox() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .background(Color.Black.copy(0.3F))
    ) {

        Column() {
            LogoWithTitle()
            UserData()
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
            .fillMaxWidth()
            .padding(12.dp)
            .padding(vertical = 30.dp),


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
            .padding( 12.dp)
        ,
        color = colorResource(R.color.main_color),
        fontFamily = FontFamily(
            Font(R.font.text_bold)
        )
    )

}

@Composable
fun UserData() {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

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
                .padding(vertical = 20.dp)
        )

    }

}

@Composable
fun LoginButton(modifier: Modifier) {
    ButtonComponent(
        modifier = modifier
            .fillMaxWidth(),

        title = "Login",
        onClick = {}
    )
}