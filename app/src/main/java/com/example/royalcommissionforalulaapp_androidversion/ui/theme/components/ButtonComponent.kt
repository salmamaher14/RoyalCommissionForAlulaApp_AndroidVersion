package com.example.royalcommissionforalulaapp_androidversion.ui.theme.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.royalcommissionforalulaapp_androidversion.R


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
        Text(title, fontSize = 20.sp ,
            fontFamily = FontFamily(Font(R.font.text_bold)))
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonComponentPreview() {
    ButtonComponent(
        modifier = Modifier.fillMaxWidth()
            .padding(12.dp),
        title = "Login",
        onClick = {}
    )
}




/*

@Composable
fun PrimaryButton(
    title: String,
    isEnabled: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit = {
        Text(title, fontSize = 20.sp ,
            fontFamily = FontFamily(Font(R.font.regular_font)),
        )
    }
) {
    Button(
        onClick = onClick,
        enabled = isEnabled,
        modifier = modifier
            .height(50.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.primary_color))
    ) {
        content()
    }
}

 */