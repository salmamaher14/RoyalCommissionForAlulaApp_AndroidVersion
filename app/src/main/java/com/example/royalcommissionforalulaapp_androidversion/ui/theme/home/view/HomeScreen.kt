package com.example.royalcommissionforalulaapp_androidversion.ui.theme.home.view

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.navigation.NavController
import com.example.royalcommissionforalulaapp_androidversion.R
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.components.CardView
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.components.ReusableTextComponent
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.home.viewmodel.HomeViewModel

import com.example.royalcommissionforalulaapp_androidversion.db.UserPreferencesImpl
import com.example.royalcommissionforalulaapp_androidversion.network.retrofit.RetrofitProviderImpl
import com.example.royalcommissionforalulaapp_androidversion.repo.RepositoryImpl
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.components.TopBar
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.map.view.MapViewComponent
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.map.viewmodel.MapViewModel



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: HomeViewModel
) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.getProgress()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    ReusableTextComponent(
                        text = "Dashboard",
                        fontSize = 28.sp,
                        textColor = colorResource(R.color.secondary_color),
                        fontFamily = FontFamily(Font(R.font.text_bold))
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(R.color.main_color)
                )
            )
        }
    ) { innerPadding ->

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            TopBar(navController, viewModel)

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
}


@Composable
fun ScopeOfWork(
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier,
) {
    val totalOfBuildings by viewModel.totalOfBuildings.collectAsState(null)

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

        )

        if(totalOfBuildings != null){
            Log.d("ScopeOfWork", "ScopeOfWork: yes")

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
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
            Log.d("ScopeOfWork", "ScopeOfWork: no")

            CircularProgressIndicator(
                color = Color.LightGray,
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

        if(steps != null){
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
        }else{
            CircularProgressIndicator(
                color = Color.LightGray,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally)
            )
        }



    }
}



@Preview
@Composable
fun TobBarPreview(modifier: Modifier = Modifier) {
    //TopBarComponent()
}




