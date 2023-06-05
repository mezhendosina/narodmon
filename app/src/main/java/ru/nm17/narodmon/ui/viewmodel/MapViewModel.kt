package ru.nm17.narodmon.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import ovh.plrapps.mapcompose.api.addLayer
import ovh.plrapps.mapcompose.core.TileStreamProvider
import ovh.plrapps.mapcompose.ui.state.MapState
import java.net.URL

class MapViewModel : ViewModel() {
    private val tileStreamProvider = TileStreamProvider { row, col, zoom ->
        URL("https://tile.openstreetmap.org/${zoom}/${row}/${col}.png").openStream()
        //URL("https://tile2.maps.2gis.com/poi?x=${row}&y=${col}&z=${zoom}&v=1&ts=online_sd").openStream()
    }

    val state: MapState by mutableStateOf(
        MapState(4, 4, 4096).apply {
            addLayer(tileStreamProvider)
        }
    )
}