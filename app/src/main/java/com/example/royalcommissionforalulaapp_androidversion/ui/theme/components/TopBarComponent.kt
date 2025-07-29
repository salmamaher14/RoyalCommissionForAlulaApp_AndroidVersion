package com.example.royalcommissionforalulaapp_androidversion.ui.theme.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.royalcommissionforalulaapp_androidversion.R


@Composable
fun TopBarComponent(
    modifier: Modifier = Modifier,

    ) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(colorResource(R.color.main_color))
            .padding(12.dp)
        ,
        contentAlignment = Alignment.CenterStart,

        ){
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            ImageViewerComponent(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                ,
                image = painterResource(R.drawable.user_profile),
            )

            ImageViewerComponent(
                image = painterResource(R.drawable.app_logo),
            )
        }


    }

}