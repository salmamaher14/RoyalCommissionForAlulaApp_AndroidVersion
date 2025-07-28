package com.example.royalcommissionforalulaapp_androidversion.ui.theme.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.material3.Text
import com.example.royalcommissionforalulaapp_androidversion.R
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp


@Composable
fun ReusableTextComponent(
    modifier: Modifier = Modifier,
    text: String,
    fontFamily: FontFamily = FontFamily(Font(R.font.text_regular)),
    fontSize: TextUnit = 14.sp,
    textAlign: TextAlign = TextAlign.Left
) {
    Text(
        text = text,
        modifier = modifier,
        fontSize = fontSize,
        fontFamily = fontFamily,
        textAlign = textAlign
    )
}


