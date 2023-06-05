@file:OptIn(ExperimentalSerializationApi::class)

package ru.nm17.narodmon.appNarodMonApiClient.types

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

@Serializable
data class AppInitRequest  (
    @JsonNames("version")
    val appVersion: String,
    val platform: String,
    val model: String,
    val width: Int,
    val utc: Int
)

