package com.example.royalcommissionforalulaapp_androidversion.ui.theme.home.view

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.royalcommissionforalulaapp_androidversion.R
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.components.CircularProgressView
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.components.ReusableTextComponent
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.home.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel
) {

    LaunchedEffect(Unit) {
        viewModel.getProgress()
        Log.d("getProgress", "HomeScreen: ${viewModel.progressSteps.value?.map {it.name}}")
    }
    ScopeOfWork(viewModel)

}


@Composable
fun ScopeOfWork(
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier,
) {
    val totalOfBuildings by viewModel.totalOfBuildings.collectAsState(0)

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(10.dp)

    ) {
        ReusableTextComponent(
            text = "Scope Of Work:",
            fontSize = 22.sp,
            fontFamily = FontFamily(Font(R.font.text_semi_bold)),
            textAlign = TextAlign.Start
        )

        if(totalOfBuildings != 0){

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {

                ReusableTextComponent(
                    text = "$totalOfBuildings",
                    fontSize = 40.sp,
                    textColor = colorResource(R.color.main_color)

                )

                ReusableTextComponent(
                    text = "Buildings",
                    fontSize = 20.sp
                )
            }
        }else {
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally)
            )
        }

    }
}



