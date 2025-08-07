package com.example.royalcommissionforalulaapp_androidversion.ui.theme.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.royalcommissionforalulaapp_androidversion.R


@Composable
fun AlertComponent(
    modifier: Modifier = Modifier,
    title: String,
    confirmButtonTitle: String,
    message: String,
    onDismiss: () -> Unit
) {
   AlertDialog(
       onDismissRequest = {
           onDismiss()
       },
       title = {
           AlertText(
               title = title,
               fontFamily = FontFamily(Font(R.font.text_bold)),
               fontSize = 16.sp
           )
       },
       text = {
           AlertText(
               title = message,
               fontFamily = FontFamily(Font(R.font.text_regular)),
               fontSize = 14.sp
           )

       },
       confirmButton = {
           Row{
               Text(

                   confirmButtonTitle ,
                   modifier = Modifier
                       .clickable {
                           onDismiss()
                       },
                   color = colorResource(R.color.dark_black)
               )
           }
       },

   )

}

@Composable
fun AlertText(
    modifier: Modifier = Modifier ,
    title: String,
    fontSize: TextUnit,
    fontFamily: FontFamily
    ) {
    Text(
        title ,
        modifier = modifier
            .fillMaxWidth(),

        textAlign = TextAlign.Center,

        fontFamily = fontFamily,

        fontSize = fontSize
    )

}

@Preview
@Composable
fun AlertDialogPreview(modifier: Modifier = Modifier) {
    var showdialog by remember { mutableStateOf(true) }
    if (showdialog){
        AlertComponent(
            title = "invalid data",
            confirmButtonTitle = "try again",
            message = "please enter correct username and password",
            onDismiss = {
                showdialog = false
                print("dismissed")
                Log.d("alert", "AlertDialogPreview: ")
            }

        )
    }

}








