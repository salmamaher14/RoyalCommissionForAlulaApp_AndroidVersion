package com.example.royalcommissionforalulaapp_androidversion.ui.theme.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.royalcommissionforalulaapp_androidversion.R


@Composable
fun TextFieldComponent(
    modifier: Modifier = Modifier,
    userInput: String,
    placeholder: String = "",
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    trailingIcon: @Composable (() -> Unit)? = null,
    onWrite: (String) -> Unit

) {
    TextField(
        value = userInput,
        modifier = modifier.height(52.dp),
        onValueChange = {
            onWrite(it)
            print("value changed")
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White.copy(0.6F),
            unfocusedContainerColor = Color.White.copy(0.6F),
            disabledContainerColor = Color.White.copy(0.6F),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = colorResource(R.color.main_color)
        ),
        shape = RoundedCornerShape(10.dp),

        placeholder = {
            Text(placeholder,
                modifier = Modifier.fillMaxWidth() ,
                textAlign = TextAlign.Left,
                color = colorResource(R.color.dark_black),
                fontSize = 14.sp
            )

        }
    )
}