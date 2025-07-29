package com.example.royalcommissionforalulaapp_androidversion.ui.theme.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.royalcommissionforalulaapp_androidversion.R


@Composable
fun FileRow(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth(),

        verticalAlignment = Alignment.CenterVertically,

    ){
        Icon(
            imageVector = Icons.Filled.Description,
            contentDescription = "Document icon",
            modifier = Modifier.size(24.dp),
            tint = colorResource(id = R.color.main_color)
        )

        ReusableTextComponent(
            text = "file",
            fontFamily = FontFamily(Font(R.font.text_regular))
        )
    }
    
}

@Composable
fun FileCard(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(colorResource(R.color.secondary_color), shape = RoundedCornerShape(15.dp)),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
         ReusableTextComponent(
             text = "Available as built",
             fontSize = 18.sp,
             fontFamily = FontFamily(Font(R.font.text_bold)),
             modifier = Modifier
                 .padding(horizontal = 12.dp)

         )

        FileRow(modifier = Modifier.padding(horizontal = 12.dp))
        FileRow(modifier = Modifier.padding(horizontal = 12.dp))
        FileRow(modifier = Modifier.padding(horizontal = 12.dp))

    }
}

@Preview
@Composable
fun FileCardPreview(modifier: Modifier = Modifier) {
    FileCard()
}
@Preview
@Composable
fun FileRowPreview(modifier: Modifier = Modifier) {
    FileRow()
}










