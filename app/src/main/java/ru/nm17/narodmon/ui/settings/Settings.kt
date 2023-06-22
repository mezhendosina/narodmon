package ru.nm17.narodmon.ui.settings

import androidx.annotation.StringRes
import ru.nm17.narodmon.R

sealed class Settings(val route: String, @StringRes val resourceId: Int) {

    object Main : Settings("settings_main", R.string.settings)
    object AboutApp : Settings("settings_about_app", R.string.about_app)

    object Debug : Settings("settings_debug_menu", R.string.debug_menu)
}