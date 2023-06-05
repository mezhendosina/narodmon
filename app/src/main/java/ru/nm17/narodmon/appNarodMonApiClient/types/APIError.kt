package ru.nm17.narodmon.appNarodMonApiClient.types

data class APIError(
    var errno: Int,
    var error: String
)
