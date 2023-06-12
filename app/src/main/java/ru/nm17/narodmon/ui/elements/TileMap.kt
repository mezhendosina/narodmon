package ru.nm17.narodmon.ui.elements

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsChannel
import io.ktor.utils.io.jvm.javaio.toInputStream
import ovh.plrapps.mapcompose.api.addLayer
import ovh.plrapps.mapcompose.api.onTouchDown
import ovh.plrapps.mapcompose.api.scale
import ovh.plrapps.mapcompose.api.setScroll
import ovh.plrapps.mapcompose.core.TileStreamProvider
import ovh.plrapps.mapcompose.ui.MapUI
import ovh.plrapps.mapcompose.ui.state.MapState
import java.io.InputStream

const val mapSize = 32768

val client = HttpClient(OkHttp)
val tileStreamProvider = TileStreamProvider { row, col, zoom ->
    requestTile(row, col, zoom)
}

suspend fun requestTile(row: Int, col: Int, zoom: Int): InputStream {
    val response = client.get("https://tile.openstreetmap.org/${zoom}/${col}/${row}.png")
    return response.bodyAsChannel().toInputStream()
}

@Composable
fun TileMap(modifier: Modifier = Modifier, onTap: () -> Unit) {
    val state by remember {
        mutableStateOf(
            MapState(
                levelCount = 8,
                fullWidth = mapSize,
                fullHeight = mapSize,
                workerCount = 16,
            ).apply {
                addLayer(tileStreamProvider)
                onTouchDown {
                    onTap.invoke()
                }
            }
        )
    }

    LaunchedEffect(state) {
        // TODO: Подгружать сохранённую позицию
        state.setScroll(Offset(28702.6F, 14787.6F))
        state.scale = 1.4658884F
    }

    MapUI(modifier = modifier, state = state)
}