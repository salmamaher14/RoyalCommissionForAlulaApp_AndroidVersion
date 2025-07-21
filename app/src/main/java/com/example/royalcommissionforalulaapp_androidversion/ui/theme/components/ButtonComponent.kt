package com.example.royalcommissionforalulaapp_androidversion.ui.theme.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.royalcommissionforalulaapp_androidversion.R
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.login.view.AppTitle
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.login.view.Login


@Composable
fun ButtonComponent(
    title: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        enabled = true,
        colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.main_color)),
        shape = RoundedCornerShape(10.dp)

    ) {
        Text(
            title, fontSize = 18.sp ,
            modifier = Modifier.padding(10.dp),
            textAlign = TextAlign.Center,
            fontFamily = FontFamily(Font(R.font.text_bold))
        )
    }
}



@Preview(showBackground = true)
@Composable
fun ButtonComponentPreview() {
    ButtonComponent(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
        ,
        title = "Login",
        onClick = {}
    )
}

@Preview(showBackground = true)
@Composable
fun TextFieldComponentPreview() {
    var userInput by remember { mutableStateOf("")}

    TextFieldComponent(
        userInput = userInput,
        placeholder = "username",

        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .background(Color.White.copy(0.6F)),
        onWrite = {
            userInput = it
        },

        trailingIcon = {

        }
    )
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    Login()
}


@Preview(showBackground = true)
@Composable
fun AppTitlePreview() {
    AppTitle()
}
@Preview(showBackground = true)
@Composable
fun UserDataPreview(modifier: Modifier = Modifier) {
    
}


