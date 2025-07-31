package com.example.royalcommissionforalulaapp_androidversion.ui.theme.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.royalcommissionforalulaapp_androidversion.R
import com.example.royalcommissionforalulaapp_androidversion.constants.Constants
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.sheets.model.FileType
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.sheets.model.Page
import com.example.royalcommissionforalulaapp_androidversion.utilities.Utilities
import kotlinx.coroutines.launch


@Composable
fun FileCard(
    page:Page,
    modifier: Modifier = Modifier
) {
    var showModal by remember { mutableStateOf(false) }
    var fileType by remember { mutableStateOf<FileType?>(FileType.FILE) }
    var fileUrl by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(colorResource(R.color.secondary_color), shape = RoundedCornerShape(15.dp)),

    ) {
        page.name?.trim()?.let {
             ReusableTextComponent(
                 text = it,
                 fontSize = 18.sp,
                 fontFamily = FontFamily(Font(R.font.text_bold)),
                 modifier = Modifier
                     .padding(8.dp)

             )
         }

        page.files?.forEach { file ->

                FileRow(
                    fileName = file.name ?: "unknown file",
                    fileUrl = file.url,

                    onClick = {

                        fileType = Utilities.getFileFormat(name = file.name ?: "")
                        fileUrl = file.url


                        if (fileType != null && fileUrl?.isNotEmpty() == true){
                            Log.d("FileRow", "FileRow: yes")
                            showModal = true
                        }else{
                            Log.d("FileRow", "FileRow: no")

                        }

                    }
                )

        }

        if (showModal){
            CustomModalBottomSheet(
                onDismissRequest = {
                    showModal = false
                }
            ) {
                when(fileType?.rawValue){
                    "Image" -> fileUrl?.let { ImageViewer(imageUrl = Constants.base_file_url + it) }

                    else -> fileUrl?.let { PdfViewer(pdfUrl = Constants.base_file_url + it) }
                }
            }
        }
    }
}



@Composable
fun FileRow(
    modifier: Modifier = Modifier,
    fileName: String,
    fileUrl: String?,
    onClick: () -> Unit = {}
    ) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                onClick()
                Log.d("FileRow", "FileRow: clicked ")
            },

        verticalAlignment = Alignment.CenterVertically,

        ){
        Icon(
            imageVector = Icons.Filled.Description,
            contentDescription = "Document icon",
            modifier = Modifier.size(24.dp),
            tint = colorResource(id = R.color.main_color)
        )

        ReusableTextComponent(
            text = fileName.trim(),
            fontFamily = FontFamily(Font(R.font.text_regular)),
            textColor = if (fileUrl.isNullOrEmpty()) Color.LightGray else colorResource(R.color.dark_black)
        )

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomModalBottomSheet(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    content: @Composable () -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    val coroutineScope = rememberCoroutineScope()

    ModalBottomSheet(
        onDismissRequest = {
            coroutineScope.launch {
                sheetState.hide()
                onDismissRequest()
            }
        },
        sheetState = sheetState,
        containerColor = colorResource(R.color.secondary_color),
        modifier = modifier
            .padding(top = 24.dp)
    ) {
        content()
    }
}


@Preview
@Composable
fun FileCardPreview(modifier: Modifier = Modifier) {
    //FileCard()
}
@Preview
@Composable
fun FileRowPreview(modifier: Modifier = Modifier) {
    //FileRow()
}



