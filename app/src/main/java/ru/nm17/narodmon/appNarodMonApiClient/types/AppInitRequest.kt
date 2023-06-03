@file:OptIn(ExperimentalSerializationApi::class)

package ru.nm17.narodmon.appNarodMonApiClient.types

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNames
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.encodeToJsonElement
import kotlinx.serialization.json.jsonObject

@Serializable
data class AppInitRequest  (
    @JsonNames("version")
    val appVersion: String,
    val platform: String,
    val model: String,
    val width: Int,
    val utc: Int
)

