package ru.nm17.narodmon.appNarodMonApiClient.types

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class VipStatus(var value: Int) {
    IsVip(1),
    NotVip(0)
}
@Serializable
data class SensorTypes(
    @SerialName("type")
    var typeCode: Long,
    var name: String,
    var types: String
)

@Serializable
data class AppInitResponse(
    var latest: String?,
    var url: String?,
    var login: String,
    var vip: Boolean, // TODO: Change this if it doesn't work
    var lat: Long,
    var long: Double,
    var addr: Double,
    /**
     * Timestamp в секундах спустя Epoch по часовой зоне пользователя
     */
    var timestamp: Long,
    var types: Array<SensorTypes>,
    var favorites: Array<Long>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AppInitResponse

        if (latest != other.latest) return false
        if (url != other.url) return false
        if (login != other.login) return false
        if (vip != other.vip) return false
        if (lat != other.lat) return false
        if (long != other.long) return false
        if (addr != other.addr) return false
        if (timestamp != other.timestamp) return false
        if (!types.contentEquals(other.types)) return false
        return favorites.contentEquals(other.favorites)
    }

    override fun hashCode(): Int {
        var result = latest.hashCode()
        result = 31 * result + url.hashCode()
        result = 31 * result + login.hashCode()
        result = 31 * result + vip.hashCode()
        result = 31 * result + lat.hashCode()
        result = 31 * result + long.hashCode()
        result = 31 * result + addr.hashCode()
        result = 31 * result + timestamp.hashCode()
        result = 31 * result + types.contentHashCode()
        result = 31 * result + favorites.contentHashCode()
        return result
    }
}
