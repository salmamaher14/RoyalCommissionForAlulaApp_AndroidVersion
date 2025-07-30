package com.example.royalcommissionforalulaapp_androidversion.ui.theme.map.viewmodel

import android.graphics.Color
import android.system.Int64Ref
import android.util.Log
import androidx.lifecycle.ViewModel
import com.esri.arcgisruntime.data.Feature
import com.esri.arcgisruntime.layers.FeatureLayer
import com.esri.arcgisruntime.layers.GroupLayer
import com.esri.arcgisruntime.mapping.ArcGISMap
import com.esri.arcgisruntime.mapping.view.Graphic
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay
import com.esri.arcgisruntime.mapping.view.MapView
import com.esri.arcgisruntime.symbology.SimpleFillSymbol
import com.esri.arcgisruntime.symbology.SimpleLineSymbol
import com.example.royalcommissionforalulaapp_androidversion.constants.Constants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MapViewModel: ViewModel() {
    private var _map = MutableStateFlow<ArcGISMap?>(null)
    val map: MutableStateFlow<ArcGISMap?> = _map

    private var _buildingLayer = MutableStateFlow<FeatureLayer?>(null)
    val buildingLayer: StateFlow<FeatureLayer?> = _buildingLayer

    private var _selectedBuildingId = MutableStateFlow<Long?>(null)

    var selectedBuildingId: StateFlow<Long?> = _selectedBuildingId

    val  graphicsOverlay = GraphicsOverlay()


    fun getMap(): ArcGISMap{
        Log.d("MapViewComponent", "getMap: ")
        return ArcGISMap(Constants.webMapUrl)
    }

    fun setMapView(map: ArcGISMap) {
        Log.d("MapViewComponent", "MapViewComponent: here in setMapView")

        _map.value = map
    }

    fun identifyLayer(screenPoint: android.graphics.Point, mapView: MapView){
        try {
            val tolerance = 0.0
            val returnPopupsOnly = false
            val maxResults = 5

            val identifyFuture = mapView.identifyLayersAsync(
                screenPoint,
                tolerance,
                returnPopupsOnly,
                maxResults
            )

            identifyFuture.addDoneListener {
                try {
                    val results = identifyFuture.get()

                    val targetLayerName = "Building"

                    results.firstOrNull { it.layerContent is FeatureLayer && it.layerContent.name == targetLayerName }
                        ?.elements
                        ?.firstOrNull()
                        ?.let { geoElement ->
                            val feature = geoElement as? Feature
                            feature?.let {
                                handleIdentifiedFeature(it)
                            }
                        }

                } catch (e: Exception) {
                    Log.e("IdentifyLayer", "Error during identify result parsing", e)
                }
            }

        } catch (e: Exception) {
            Log.e("IdentifyLayer", "Failed to perform identify", e)
        }
    }

    private fun handleIdentifiedFeature(feature: Feature) {
        val objectId = feature.attributes["OBJECTID"]
        Log.d("IdentifyLayer", "Selected OBJECTID: $objectId")

        _selectedBuildingId.value = objectId as Long?

        feature.geometry?.let { geometry ->
            val outline = SimpleLineSymbol(
                SimpleLineSymbol.Style.SOLID,
                Color.BLUE,
                4f
            )

            val fillSymbol = SimpleFillSymbol(
                SimpleFillSymbol.Style.NULL,
                Color.TRANSPARENT,
                outline
            )

            val graphic = Graphic(geometry, fillSymbol)

            graphicsOverlay.graphics.clear()
            Log.d("MapViewComponent", "MapViewComponent: adding graphics overlays")
            graphicsOverlay.graphics.add(graphic)

        }
    }


}


/*
    fun filterLayers() {
        Log.d("MapViewComponent", "MapViewComponent: here in filterLayers")

        try {
            _map.value?.operationalLayers?.filterIsInstance<GroupLayer>()?.forEach { groupLayer ->
                val buildingLayer = groupLayer.layers.firstOrNull { it.name == "Building" }

                if(buildingLayer is FeatureLayer){
                    buildingLayer.loadAsync()
                }

            }
        }catch(e: Exception){
            Log.d("Map", "Exception in filterLayers: ${e.localizedMessage}: ")
        }

    }
 */