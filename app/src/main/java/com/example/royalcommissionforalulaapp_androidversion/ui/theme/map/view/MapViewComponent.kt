package com.example.royalcommissionforalulaapp_androidversion.ui.theme.map.view

import android.util.Log
import android.view.MotionEvent
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf

import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView


import com.esri.arcgisruntime.mapping.view.DefaultMapViewOnTouchListener
import com.esri.arcgisruntime.mapping.view.MapView
import com.example.royalcommissionforalulaapp_androidversion.R
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.components.ReusableTextComponent
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.map.viewmodel.MapViewModel
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.sheets.view.AllSheets



@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MapViewComponent(
    modifier: Modifier = Modifier,
    viewmodel: MapViewModel
) {
    val context = LocalContext.current

    val selectedBuildingId by viewmodel.selectedBuildingId.collectAsState()
    val buildingPages by viewmodel.buildingPages.collectAsState()
    val isLoading by viewmodel.isLoading.collectAsState()
    val isMapLoaded = remember { mutableStateOf(false) }
    val alertMessage by viewmodel.alertMessage.collectAsState()

    val sheetsBringIntoViewRequester = remember { BringIntoViewRequester() }

    LaunchedEffect(selectedBuildingId) {
        selectedBuildingId?.let { viewmodel.getBuildingFiles(it) }
    }

    LaunchedEffect(buildingPages) {
        if (!buildingPages.isNullOrEmpty()) {
            sheetsBringIntoViewRequester.bringIntoView()
        }
    }

    val mapView = remember {
        isMapLoaded.value = true

        MapView(context).apply {
            map = viewmodel.getMap()

            if(!graphicsOverlays.contains(viewmodel.graphicsOverlay)){
                graphicsOverlays.add(viewmodel.graphicsOverlay)
            }

            map.addDoneLoadingListener {
                isMapLoaded.value = false
                Log.d("MapViewComponent", "MapViewComponent: ${isMapLoaded.value}")
                viewmodel.setMapView(map)
            }
        }
    }


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 30.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        ReusableTextComponent(
            text = "General Location:",
            fontSize = 22.sp,
            fontFamily = FontFamily(Font(R.font.text_semi_bold)),
            textAlign = TextAlign.Start,
        )

        Box {
            AndroidView(
                factory = {
                    mapView.setOnTouchListener(object : DefaultMapViewOnTouchListener(context, mapView) {
                        override fun onSingleTapConfirmed(e: MotionEvent): Boolean {

                            val screenPoint = android.graphics.Point(e.x.toInt(), e.y.toInt())
                            viewmodel.identifyLayer(screenPoint, mapView)
                            return super.onSingleTapConfirmed(e)
                        }
                    })
                    mapView
                },
                modifier = modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .clip(RoundedCornerShape(15.dp))
            )

            if (isLoading){
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.LightGray
                )
            }

            if (isMapLoaded.value){
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.LightGray
                )
            }

            if (!alertMessage.isNullOrEmpty()){
               Toast.makeText(context, alertMessage, Toast.LENGTH_SHORT).show()
                viewmodel.clearAlertMessage()
            }
        }

        buildingPages?.let {
            Column(
                modifier = Modifier
                    .bringIntoViewRequester(sheetsBringIntoViewRequester)
            ) {
                AllSheets(pages = it)
            }
        }
    }
}



