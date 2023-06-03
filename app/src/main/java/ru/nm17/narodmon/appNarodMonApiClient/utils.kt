package ru.nm17.narodmon.appNarodMonApiClient

import kotlinx.serialization.json.JsonObject

fun mergeJsonObjects(json1: JsonObject, json2: JsonObject): JsonObject {
    val result = mutableMapOf<String, kotlinx.serialization.json.JsonElement>()
    result.putAll(json1)
    json2.forEach { (key, value) ->
        result[key] = value
    }
    return JsonObject(result)
}