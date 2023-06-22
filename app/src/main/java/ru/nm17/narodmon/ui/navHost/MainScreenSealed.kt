package ru.nm17.narodmon.ui.navHost

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import ru.nm17.narodmon.R

sealed class MainScreenSealed(
    val route: String,
    @StringRes val resourceId: Int,
    @DrawableRes val iconId: Int
) {
    object Sensors : MainScreenSealed("sensors", R.string.sensors_page_title, R.drawable.ic_home)
    object Webcams : MainScreenSealed("webcams", R.string.webcams, R.drawable.ic_webcam)

    object Messages : MainScreenSealed("messages", R.string.messages, R.drawable.ic_message)

    object Settings : MainScreenSealed("settings", R.string.settings, R.drawable.ic_settings)
}