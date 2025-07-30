package com.example.royalcommissionforalulaapp_androidversion.ui.theme.map.view

import android.view.MotionEvent

import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect

import androidx.compose.runtime.remember

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner

import com.esri.arcgisruntime.mapping.view.DefaultMapViewOnTouchListener
import com.esri.arcgisruntime.mapping.view.MapView
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.map.viewmodel.MapViewModel


@Composable
fun MapViewComponent(
    modifier: Modifier = Modifier,
    viewmodel: MapViewModel
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val mapView = remember {
        MapView(context).apply {
            map = viewmodel.getMap()

            if(!graphicsOverlays.contains(viewmodel.graphicsOverlay)){
                graphicsOverlays.add(viewmodel.graphicsOverlay)
            }

            map.addDoneLoadingListener {
                viewmodel.setMapView(map)
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
        modifier = modifier.fillMaxSize()
    )
}

