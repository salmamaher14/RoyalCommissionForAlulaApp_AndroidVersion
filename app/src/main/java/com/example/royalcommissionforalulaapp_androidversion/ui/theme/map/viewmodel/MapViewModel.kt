package com.example.royalcommissionforalulaapp_androidversion.ui.theme.map.viewmodel

import android.graphics.Color
import android.util.Log
import androidx.lifecycle.ViewModel
import com.esri.arcgisruntime.data.Feature
import com.esri.arcgisruntime.layers.FeatureLayer
import com.esri.arcgisruntime.mapping.ArcGISMap
import com.esri.arcgisruntime.mapping.view.Graphic
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay
import com.esri.arcgisruntime.mapping.view.MapView
import com.esri.arcgisruntime.symbology.SimpleFillSymbol
import com.esri.arcgisruntime.symbology.SimpleLineSymbol
import com.example.royalcommissionforalulaapp_androidversion.constants.Constants
import com.example.royalcommissionforalulaapp_androidversion.repo.Repository
import com.example.royalcommissionforalulaapp_androidversion.ui.theme.sheets.model.Page
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MapViewModel(private val repo: Repository): ViewModel() {
    private var _map = MutableStateFlow<ArcGISMap?>(null)
    val map: MutableStateFlow<ArcGISMap?> = _map



    private var _selectedBuildingId = MutableStateFlow<Long?>(null)
    var selectedBuildingId: StateFlow<Long?> = _selectedBuildingId

    private val _buildingPages = MutableStateFlow<List<Page>?>(null)
    val buildinPages: StateFlow<List<Page>?> = _buildingPages

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
                                setBuildingId(feature)
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
            graphicsOverlay.graphics.add(graphic)

        }
    }

    private fun setBuildingId(feature: Feature){
        val objectId = feature.attributes["OBJECTID"]
        _selectedBuildingId.value = objectId as Long?
    }

    suspend fun  getBuildingFiles(buildingId: Long){
        val response = repo.getBuilding(buildingId = buildingId.toString(), token = "h5wJeT2/BqAMYdWINCoj4IUj0iG8XketPidZrjD7EWD7RkvrZQsr7o51Om9U74IfgwNUGnE/0Pg=")
        _buildingPages.value = response.pages

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