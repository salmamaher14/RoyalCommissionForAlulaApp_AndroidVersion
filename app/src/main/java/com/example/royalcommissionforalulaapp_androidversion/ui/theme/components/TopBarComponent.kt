package com.example.royalcommissionforalulaapp_androidversion.ui.theme.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.royalcommissionforalulaapp_androidversion.R
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.home.viewmodel.HomeViewModel


@Composable
fun UserProfileView(
    userName: String,
    onLogout: () -> Unit

) {
    Column(
        modifier = Modifier

            .padding(horizontal = 12.dp)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(10.dp)
            ),
        verticalArrangement = Arrangement.spacedBy(2.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        ReusableTextComponent(
            text = userName,
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.text_bold)),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
            )

        Divider(
            modifier = Modifier
                .padding(horizontal = 8.dp)
        )

        TextButton(
            onClick = {
                onLogout()
            }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),

                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ){
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = "Logout",
                    tint = Color.LightGray

                )
                ReusableTextComponent(
                    text = "Logout",
                    textAlign = TextAlign.Center,
                    textColor = Color.LightGray,
                    fontFamily = FontFamily(Font(R.font.text_bold))
                )
            }
        }
    }
}


@Composable
fun TopBar(
    navController: NavController,
    viewModel: HomeViewModel
) {
    var showProfile by remember { mutableStateOf(false) }

        Row(
            modifier = Modifier
                .background(colorResource(R.color.main_color))
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
               .padding(bottom = 10.dp)
            ,

            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier =
                     Modifier
                    .width(160.dp),

                verticalArrangement = Arrangement.spacedBy(20.dp),

            ) {
                Image(

                    painter = painterResource(R.drawable.user_profile),
                    contentDescription = "User profile",
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.White, CircleShape)
                        .clickable {
                            showProfile = !showProfile
                        }
                )

                if (showProfile) {
                    UserProfileView(
                        userName = viewModel.getUserName() ?: "",
                        onLogout = {
                        viewModel.clearUserData()
                        navController.navigate("login_screen")

                    })
                }

            }

            ImageViewerComponent(

                image = painterResource(R.drawable.app_logo),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            )

        }

}

