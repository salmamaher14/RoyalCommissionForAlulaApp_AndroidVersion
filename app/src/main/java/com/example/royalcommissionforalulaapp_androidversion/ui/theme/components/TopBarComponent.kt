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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.unit.dp
import com.example.royalcommissionforalulaapp_androidversion.R


@Composable
fun UserProfileView(

) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(vertical = 8.dp)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(10.dp)
            ),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row {
            Text(
                text =  "Omar",
            )
        }

        Divider()

        TextButton(

            onClick = {}
        ) {
            Row {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Logout"
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Logout")
            }
        }
    }
}


@Composable
fun TopBar(
) {
    var showProfile by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxWidth()
            .background(colorResource(R.color.main_color)),

        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(vertical = 40.dp)
            ,

            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .width(160.dp)
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

                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            ImageViewerComponent(
                image = painterResource(R.drawable.app_logo),
            )
        }
    }
}


/*
@Composable
fun TopBarComponent(
    modifier: Modifier = Modifier,
    )
{
    var showMenu by remember { mutableStateOf(false) }
    Box(
        modifier = modifier
            .height(150.dp)
            .background(colorResource(R.color.main_color))
            .padding(horizontal = 12.dp)
        ,
        contentAlignment = Alignment.CenterStart,

        ){
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            Column(
                horizontalAlignment = Alignment.Start
            ) {
                ImageViewerComponent(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .clickable {
                            showMenu = true
                            Log.d("clicking", "TopBarComponent: ")
                        },
                    image = painterResource(R.drawable.user_profile),
                )



                if(showMenu){
                    UserProfileView {  }
                }
            }

            ImageViewerComponent(
                image = painterResource(R.drawable.app_logo),
            )



        }




    }

}



@Composable
fun UserProfileView(
    modifier: Modifier = Modifier,
    onLogout: () -> Unit
) {
    Column(
        modifier = modifier
            .width(100.dp)
          //  .padding(horizontal = 12.dp)
            .background(Color.White, RoundedCornerShape(10.dp)),

        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Text(
            text = "salma",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )

        Divider()

        TextButton(onClick = onLogout) {
            Icon(
                imageVector = Icons.Default.ExitToApp,
                contentDescription = "Logout",
                tint = Color.Gray
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Logout",
                color = Color.Gray
            )
        }
    }
}

*/