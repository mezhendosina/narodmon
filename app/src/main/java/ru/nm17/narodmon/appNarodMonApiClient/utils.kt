package ru.nm17.narodmon.appNarodMonApiClient

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.encodeToJsonElement
import kotlinx.serialization.json.jsonObject
import ru.nm17.narodmon.appNarodMonApiClient.types.MandatoryParams

fun mergeJsonObjects(json1: JsonObject, json2: JsonObject): JsonObject {
    val result = mutableMapOf<String, kotlinx.serialization.json.JsonElement>()
    result.putAll(json1)
    json2.forEach { (key, value) ->
        result[key] = value
    }
    return JsonObject(result)
}

