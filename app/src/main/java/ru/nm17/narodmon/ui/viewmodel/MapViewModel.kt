package ru.nm17.narodmon.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsChannel
import io.ktor.utils.io.jvm.javaio.toInputStream
import ovh.plrapps.mapcompose.api.addLayer
import ovh.plrapps.mapcompose.core.TileStreamProvider
import ovh.plrapps.mapcompose.ui.state.MapState
import java.io.InputStream
import java.net.URL

class MapViewModel : ViewModel() {
    private val client = HttpClient(CIO)

    private val tileStreamProvider = TileStreamProvider { row, col, zoom ->
        requestTile(row, col, zoom)
    }

    val state: MapState by mutableStateOf(
        MapState(4, 4096, 4096).apply {
            addLayer(tileStreamProvider)
        }
    )

    private suspend fun requestTile(row: Int, col: Int, zoom: Int): InputStream {
        val response = client.get("https://tile.openstreetmap.org/${zoom}/${col}/${row}.png")
        return response.bodyAsChannel().toInputStream()
    }
}