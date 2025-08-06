package com.example.royalcommissionforalulaapp_androidversion.ui.theme.sheets.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.royalcommissionforalulaapp_androidversion.R
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.components.FileCard
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.components.ReusableTextComponent
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.sheets.model.Page


@Composable
fun AllSheets(
    modifier: Modifier = Modifier,
    pages: List<Page>

) {
    ReusableTextComponent(
        text = "Sheets: ",
        fontSize = 22.sp,
        fontFamily = FontFamily(Font(R.font.text_bold))
    )

    LazyColumn(
        modifier = Modifier
            .height(300.dp)
            .padding(12.dp)
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        items(pages.size){ index ->
            FileCard(pages[index])
        }

    }
}
