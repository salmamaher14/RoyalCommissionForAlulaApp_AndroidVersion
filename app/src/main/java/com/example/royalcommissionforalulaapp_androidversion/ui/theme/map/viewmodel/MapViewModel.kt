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




class MapViewModel(private val repo: Repository) : ViewModel() {
    private var _map = MutableStateFlow<ArcGISMap?>(null)
    val map: MutableStateFlow<ArcGISMap?> = _map

    private var _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private var _alertMessage = MutableStateFlow<String?>(null)
    val alertMessage: StateFlow<String?> = _alertMessage

    private var _selectedBuildingId = MutableStateFlow<Long?>(null)
    val selectedBuildingId: StateFlow<Long?> = _selectedBuildingId

    private val _buildingPages = MutableStateFlow<List<Page>?>(null)
    val buildingPages: StateFlow<List<Page>?> = _buildingPages

    val graphicsOverlay = GraphicsOverlay()

  /*  fun getMap(): ArcGISMap {
        val map = ArcGISMap(Constants.WEB_MAP_URL)
        map.minScale = 0.0
        map.maxScale = 71.3
        return map
    }*/

    fun getMap(): ArcGISMap {
        val map = ArcGISMap(Constants.WEB_MAP_URL)


        // Allow zooming out more (e.g., to 1:1,000,000 scale)
        map.minScale = 100000.0  // Zoomed out
        map.maxScale = 500.0      // Zoomed in

        return map
    }


    fun setMapView(map: ArcGISMap) {
        _map.value = map
    }

    fun identifyLayer(screenPoint: android.graphics.Point, mapView: MapView) {
        try {
            _isLoading.value = true

            val mapPoint = mapView.screenToLocation(screenPoint)
            Log.d("IdentifyTap", "Tapped at: ${mapPoint.x}, ${mapPoint.y}")

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
                   // _alertMessage.value = null
                    val results = identifyFuture.get()
                    val targetLayerName = "Building"

                    results.forEach {
                        Log.d("IdentifyLayer", "Layer: ${it.layerContent.name}, Elements: ${it.elements.size}")
                    }

                    val matchingResult = results.firstOrNull {
                        it.layerContent is FeatureLayer && it.layerContent.name == targetLayerName
                    }

                    val geoElement = matchingResult?.elements?.firstOrNull()
                    val feature = geoElement as? Feature

                    if (feature != null) {
                        setBuildingId(feature)
                        handleIdentifiedFeature(feature)
                    } else {
                        _isLoading.value = false
                        _alertMessage.value = "No building found at the selected location!"
                    }



                } catch (e: Exception) {
                    Log.e("IdentifyLayer", "Error during identify result parsing", e)
                    _isLoading.value = false
                }
            }

        } catch (e: Exception) {
            Log.e("IdentifyLayer", "Failed to perform identify", e)
            _isLoading.value = false
        }
    }

    private fun handleIdentifiedFeature(feature: Feature) {
        feature.geometry?.let { geometry ->
            val outline = SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, Color.BLUE, 4f)

            // For debugging: light transparent fill
            val fillSymbol = SimpleFillSymbol(
                SimpleFillSymbol.Style.SOLID,
                Color.argb(80, 0, 0, 255),
                outline
            )

            val graphic = Graphic(geometry, fillSymbol)

            graphicsOverlay.graphics.clear()
            graphicsOverlay.graphics.add(graphic)
        }
    }

    private fun setBuildingId(feature: Feature) {
        val objectId = feature.attributes["OBJECTID"]
        _selectedBuildingId.value = objectId as? Long
        _isLoading.value = false
    }

    suspend fun getBuildingFiles(buildingId: Long) {
        val response = repo.getBuilding(buildingId = buildingId.toString())
        _buildingPages.value = response?.pages
    }

    fun clearAlertMessage(){
        _alertMessage.value = null
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

/*
class MapViewModel(private val repo: Repository): ViewModel() {
    private var _map = MutableStateFlow<ArcGISMap?>(null)
    val map: MutableStateFlow<ArcGISMap?> = _map

    private  var  _isLoading = MutableStateFlow(false)
    var isLoading: StateFlow<Boolean> = _isLoading

    private var _alertMessage = MutableStateFlow<String?>(null)
    var alertMessage: StateFlow<String?> = _alertMessage

    private var _selectedBuildingId = MutableStateFlow<Long?>(null)
    var selectedBuildingId: StateFlow<Long?> = _selectedBuildingId

    private val _buildingPages = MutableStateFlow<List<Page>?>(null)
    val buildingPages: StateFlow<List<Page>?> = _buildingPages
    val  graphicsOverlay = GraphicsOverlay()


    fun getMap(): ArcGISMap {
        val map = ArcGISMap(Constants.WEB_MAP_URL)
        map.minScale = 0.0
        map.maxScale = 71.3

        return map
    }


    fun setMapView(map: ArcGISMap) {
        _map.value = map

    }


    fun identifyLayer(screenPoint: android.graphics.Point, mapView: MapView){
        try {
            _isLoading.value = true

            val mapPoint = mapView.screenToLocation(screenPoint)
            Log.d("IdentifyTap", "Tapped at: ${mapPoint.x}, ${mapPoint.y}")


            val tolerance = 10.0

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
                    _alertMessage.value = null

                    val results = identifyFuture.get()
                    val targetLayerName = "Building"

                    val matchingResult = results.firstOrNull {
                        it.layerContent is FeatureLayer && it.layerContent.name == targetLayerName
                    }

                    val geoElement = matchingResult?.elements?.firstOrNull()
                    val feature = geoElement as? Feature

                    if (feature != null) {
                        setBuildingId(feature)
                        handleIdentifiedFeature(feature)
                    } else {
                        _isLoading.value = false
                        _alertMessage.value = "no building found at the selected location!"
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
        _isLoading.value = false
    }

    suspend fun  getBuildingFiles(buildingId: Long){
        val response = repo.getBuilding(buildingId = buildingId.toString())
        _buildingPages.value = response?.pages

    }


}*/