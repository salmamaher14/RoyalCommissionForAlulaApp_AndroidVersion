package com.example.royalcommissionforalulaapp_androidversion.ui.theme.home.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.royalcommissionforalulaapp_androidversion.R
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.components.CardView
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.components.ReusableTextComponent
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.components.TopBarComponent
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.home.viewmodel.HomeViewModel

import com.example.royalcommissionforalulaapp_androidversion.db.UserPreferencesImpl
import com.example.royalcommissionforalulaapp_androidversion.network.retrofit.RetrofitProviderImpl
import com.example.royalcommissionforalulaapp_androidversion.repo.RepositoryImpl
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.map.view.MapViewComponent
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.map.viewmodel.MapViewModel




@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel
) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.getProgress()
    }


    Column(modifier = Modifier.fillMaxSize()) {
        TopBarComponent()
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            ScopeOfWork(viewModel)

            ProgressCards(viewModel)

            MapViewComponent(
                viewmodel = MapViewModel(
                    repo = RepositoryImpl(
                        RetrofitProviderImpl().getApiService(),
                        localService = UserPreferencesImpl.getInstance(context)
                    )
                )
            )
        }
    }

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
        verticalArrangement = Arrangement.spacedBy(8.dp)

    ) {
        ReusableTextComponent(
            text = "Scope Of Work:",
            fontSize = 22.sp,
            fontFamily = FontFamily(Font(R.font.text_semi_bold)),
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(0.dp)
                .background(Color.Red)
        )

        if(totalOfBuildings != 0){

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
               // modifier = Modifier.background(Color.Gray)
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


@Composable
fun ProgressCards(
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier
) {
    val steps by viewModel.progressSteps.collectAsState()

    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = modifier.fillMaxWidth()

    ) {
        ReusableTextComponent(
            text = "Stages: ",
            fontSize = 22.sp,
            fontFamily = FontFamily(Font(R.font.text_bold))
        )

        if(steps?.isEmpty() == true){
            Column(modifier = Modifier.fillMaxWidth()) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Loading Stages...",
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }else {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                steps?.let { stepList ->
                    items(stepList.size) { index ->
                        CardView(
                            name = stepList[index].name ?: "",
                            total = stepList[index].total ?: 0,
                            available = stepList[index].available ?: 0,
                            perc1 = stepList[index].perc1 ?: 0.0,
                            perc2 = stepList[index].perc2 ?: 0.0
                        )
                    }
                }
            }

        }
    }
}



@Preview
@Composable
fun TobBarPreview(modifier: Modifier = Modifier) {
    TopBarComponent()
}




