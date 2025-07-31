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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.royalcommissionforalulaapp_androidversion.R
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.sheets.model.FileType
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.sheets.model.Page
import com.example.royalcommissionforalulaapp_androidversion.utilities.Utilities


@Composable
fun FileCard(
    page:Page,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(colorResource(R.color.secondary_color), shape = RoundedCornerShape(15.dp)),
       // verticalArrangement = Arrangement.spacedBy(10.dp)
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
            val fileType = file.name?.let { Utilities.getFileFormat(it) }
            val fileUrl = file.url

            if (fileType != null) {
                FileRow(
                    fileType = fileType,
                    fileName = file.name,
                    fileUrl = fileUrl
                )
            }

            /*if (fileType != null && fileType.rawValue.isNotEmpty() && !fileUrl.isNullOrEmpty()) {

            }*/
        }


    }
}

@Composable
fun FileRow(
    modifier: Modifier = Modifier,
    fileType: FileType,
    fileName: String,
    fileUrl: String?
    ) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),

        verticalAlignment = Alignment.CenterVertically,

        ){
        Icon(
            imageVector = Icons.Filled.Description,
            contentDescription = "Document icon",
            modifier = Modifier.size(24.dp),
            tint = colorResource(id = R.color.main_color)
        )

        ReusableTextComponent(
            text = fileName,
            fontFamily = FontFamily(Font(R.font.text_regular))
        )
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








