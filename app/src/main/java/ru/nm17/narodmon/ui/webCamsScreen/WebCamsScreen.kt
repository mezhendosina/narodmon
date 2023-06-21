package ru.nm17.narodmon.ui.webCamsScreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ru.nm17.narodmon.ui.theme.NarodMonTheme

@Composable
fun WebCamsScreen(navController: NavController) {

    var webCams by remember {
        mutableStateOf(
            listOf(
                WebCamUiEntity(
                    1,
                    "Крутая камера",
                    1,
                    "Улица Пушкина, дом Калатушкина, кватира под номером 5",
                    "12:45",
                    "https://images-webcams.windy.com/51/1559159251/current/preview/1559159251.jpg?1686320054"
                ),
                WebCamUiEntity(
                    2,
                    "Крутая камера 2",
                    2,
                    "Улица Пушкина, дом Калатушкина, кватира под номером 5",
                    "12:45",
                    "https://images-webcams.windy.com/51/1559159251/current/preview/1559159251.jpg?1686320054"
                ),
                WebCamUiEntity(
                    3,
                    "Крутая камера 3",
                    3,
                    "Улица Пушкина, дом Калатушкина, кватира под номером 5",
                    "12:45",
                    "https://images-webcams.windy.com/51/1559159251/current/preview/1559159251.jpg?1686320054"
                )
            )
        )
    } // TODO источник камер

    LazyColumn() {
        items(webCams) {
            WebCamItem(webCamEntity = it)
        }
    }
}

@Preview
@Composable
fun PreviewWebCams() {
    NarodMonTheme {
        WebCamsScreen(rememberNavController())
    }
}