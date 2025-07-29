package com.example.royalcommissionforalulaapp_androidversion.ui.theme.map.viewmodel

import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.ViewModel
import com.esri.arcgisruntime.layers.FeatureLayer
import com.esri.arcgisruntime.layers.GroupLayer
import com.esri.arcgisruntime.loadable.LoadStatus
import com.esri.arcgisruntime.mapping.ArcGISMap
import com.esri.arcgisruntime.mapping.view.MapView
import com.example.royalcommissionforalulaapp_androidversion.constants.Constants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MapViewModel: ViewModel() {
    private var _map = MutableStateFlow<ArcGISMap?>(null)
    val map: MutableStateFlow<ArcGISMap?> = _map

    private var _buildingLayer = MutableStateFlow<FeatureLayer?>(null)
    val buildingLayer: StateFlow<FeatureLayer?> = _buildingLayer

    fun getMap(): ArcGISMap{
        return ArcGISMap(Constants.webMapUrl)
    }

    fun setMapView(map: ArcGISMap) {
        _map.value = map
        Log.d("inFilter", "filterLayers: ${_map.value?.operationalLayers?.map { it.name }}")

    }

    fun filterLayers() {
        Log.d("inFilter", "filterLayers: ${map.value?.operationalLayers?.map { it.name }}")
        try {
            _map.value?.operationalLayers?.forEach { layer ->
                if (layer is GroupLayer){
                    Log.d("yes", "filterLayers: ")
                    val featureLayer = layer.layers.first { it.name == "Building" }
                    featureLayer?.let {
                        it.loadAsync()
                       _buildingLayer.value = featureLayer as FeatureLayer?

                        Log.d("layers", "filterLayers: ${_buildingLayer.value?.name}")
                    }


                }

            }
        }catch(e: Exception){
            Log.d("Map", "Exception in filterLayers: ${e.localizedMessage}: ")
        }

    }


}

/*
    func filterLayers() async throws {
        do{
            if let layers = self.map?.operationalLayers {
                for layer in layers {

                    if let group = layer as? GroupLayer {
                        if let featureLayer = group.layers.first (where: {$0.name == "Building"}) as? FeatureLayer {
                            try await featureLayer.load()

                            DispatchQueue.main.async{
                                self.buildingLayer = featureLayer

                            }
                            break
                        }
                    }
                }
            }
        }catch {
            print("filtered layer not found! : \(error)")
        }
    }
 */

/*
import Foundation
import ArcGIS

class MapViewmodel: ObservableObject {

    @Published var map: Map?
    @Published var buildingLayer: FeatureLayer?
    @Published var graphicsOverlay = GraphicsOverlay()
    @Published var buildingId: Int64?

     func loadWebMAp() async {
        do {
            let portal = Portal.arcGISOnline(connection: .anonymous)
            let portalItem = PortalItem(portal: portal, id: PortalItem.ID(Constants.webMapID)!)
            let map = Map(item: portalItem)

            try await map.load()
            map.minScale = 0
            map.maxScale = 71.3


           DispatchQueue.main.async{
               self.map = map
               Task{
                   try? await self.filterLayers()
               }

            }


        } catch {
            print("Failed to load map or layers: \(error)")
        }
    }

    func filterLayers() async throws {
        do{
            if let layers = self.map?.operationalLayers {
                for layer in layers {

                    if let group = layer as? GroupLayer {
                        if let featureLayer = group.layers.first (where: {$0.name == "Building"}) as? FeatureLayer {
                            try await featureLayer.load()

                            DispatchQueue.main.async{
                                self.buildingLayer = featureLayer

                            }
                            break
                        }
                    }
                }
            }
        }catch {
            print("filtered layer not found! : \(error)")
        }
    }




    @MainActor
    func identifyBuildingFeature(at screenPoint: CGPoint, using proxy: MapViewProxy) async {
        do {
            print("here in identifyBuildingFeature")
            let results = try await proxy.identifyLayers(
                screenPoint: screenPoint,
                tolerance: 0,
                returnPopupsOnly: false,
                maximumResultsPerLayer: 5
            )

            guard let buildingLayer = buildingLayer else {
                print(" Building layer is not available")
                return
            }

            print("here in identifyBuildingFeature2")

            for result in results {
                print("here in identifyBuildingFeature3")

                if let featureLayer = result.layerContent as? FeatureLayer,
                   featureLayer.name == buildingLayer.name,
                   let feature = result.geoElements.first {
                    print("here in identifyBuildingFeature4")

                    if let key = feature.attributes.keys.first(where: {$0 == "OBJECTID"}),
                       let value = feature.attributes[key]{
                        self.buildingId = value as? Int64

                    }

                    print("here in identifyBuildingFeature5")

                    if let geometry = feature.geometry {
                        let outline = SimpleLineSymbol(style: .solid, color: .blue, width: 4)
                        let symbol = SimpleFillSymbol(style: .noFill, color: .blue, outline: outline)
                        let graphic = Graphic(geometry: geometry, symbol: symbol)
                        graphicsOverlay.removeAllGraphics()
                        graphicsOverlay.addGraphic(graphic)
                    }

                    return
                }
            }

        } catch {
            print(" Identify error: \(error)")
        }
    }


}




 */