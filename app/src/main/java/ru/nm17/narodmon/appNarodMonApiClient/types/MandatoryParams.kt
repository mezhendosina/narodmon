package ru.nm17.narodmon.appNarodMonApiClient.types

import kotlinx.serialization.Serializable

@Serializable
enum class NarodMonLanguages(val value: String) {
    Russian("ru"),
    English("en"),
    Ukrainian("uk")
}

@Serializable
data class MandatoryParams(
    var cmd: String?,
    var lang: String,
    var uuid: String,
    var apiKey: String?,
)
