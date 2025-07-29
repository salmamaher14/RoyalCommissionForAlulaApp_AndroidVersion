package com.example.royalcommissionforalulaapp_androidversion.ui.theme.map.view

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.esri.arcgisruntime.layers.FeatureLayer
import com.esri.arcgisruntime.layers.GroupLayer
import com.esri.arcgisruntime.mapping.ArcGISMap
import com.esri.arcgisruntime.mapping.view.MapView
import com.example.royalcommissionforalulaapp_androidversion.constants.Constants
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.map.viewmodel.MapViewModel

@Composable
fun MapViewComponent(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val mapView = remember {
        MapView(context).apply {
            map = ArcGISMap(Constants.webMapUrl)

            map.addDoneLoadingListener {
                Log.d("MapLoaded", "Map done loading")
                val layers = map.operationalLayers
                Log.d("OperationalLayers", "Layers: ${layers.map { it.name }}")

                // Filter GroupLayer and find "Building" layer
                layers.filterIsInstance<GroupLayer>().forEach { groupLayer ->
                    val buildingLayer = groupLayer.layers.firstOrNull { it.name == "Building" }
                    if (buildingLayer is FeatureLayer) {
                        Log.d("LayerFound", "Loading 'Building' layer")
                        buildingLayer.loadAsync()
                    } else {
                        Log.d("LayerMissing", "'Building' layer not found in group")
                    }
                }
            }
        }
    }

    DisposableEffect(lifecycleOwner) {
        val lifecycleObserver = object : DefaultLifecycleObserver {
            override fun onResume(owner: LifecycleOwner) {
                mapView.resume()
            }

            override fun onPause(owner: LifecycleOwner) {
                mapView.pause()
            }

            override fun onDestroy(owner: LifecycleOwner) {
                mapView.dispose()
            }
        }

        lifecycleOwner.lifecycle.addObserver(lifecycleObserver)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(lifecycleObserver)
        }
    }

    AndroidView(
        factory = { mapView },
        modifier = modifier.fillMaxSize()
    )
}

/*
@Composable
fun MapViewComponent(
    modifier: Modifier = Modifier,
    viewModel: MapViewModel
) {


    val context = LocalContext.current

    val mapView = remember {
        MapView(context).apply {
            map = viewModel.getMap()
        }
    }

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val lifecycleObserver = object : DefaultLifecycleObserver {
            override fun onResume(owner: LifecycleOwner) {
                mapView.resume()
            }

            override fun onPause(owner: LifecycleOwner) {
                mapView.pause()
            }

            override fun onDestroy(owner: LifecycleOwner) {
                mapView.dispose()
            }
        }

        lifecycleOwner.lifecycle.addObserver(lifecycleObserver)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(lifecycleObserver)
        }
    }

    AndroidView(
        factory = { mapView },
        update = {
            Log.d("inFilter", "filterLayers: ${mapView.map?.operationalLayers?.map { it.name }}")


        },
        modifier = modifier.fillMaxSize()
    )
}*/


/*
@Composable
fun ArcGISMap(
    viewModel: MapViewModel,
    modifier: Modifier = Modifier,
    mapHeight: Dp = 400.dp,
    onCompletion: (MapView) -> Unit = {}
) {

    var mapView by remember { mutableStateOf<MapView?>(null) }


    Box(modifier = modifier) {
        if (viewModel.isLoading.value) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(48.dp)
            )
        }

        AndroidView(
                factory = { ctx ->
                    val createdMapView = MapView(ctx)
                    val configuredMapView = viewModel.setupMap(createdMapView)
                    mapView = configuredMapView

                    onCompletion(configuredMapView)

                    configuredMapView.setOnTouchListener(object : DefaultMapViewOnTouchListener(ctx, configuredMapView) {
                        override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
                            val screenPoint = android.graphics.Point(e.x.toInt(), e.y.toInt())
                            viewModel.identifyLayer(screenPoint, configuredMapView)
                            return super.onSingleTapConfirmed(e)
                        }

                    })


                    configuredMapView
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(mapHeight),

                onRelease = { mapView ->
                    mapView.dispose()
                    Log.d("releasinnnng", "ArcGISMap: ${mapView.map}")
                }
        )

    }

}
 */