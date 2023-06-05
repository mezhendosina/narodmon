package ru.nm17.narodmon.appNarodMonApiClient

import dev.forkhandles.result4k.Failure
import dev.forkhandles.result4k.Result4k
import dev.forkhandles.result4k.Success
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.http.headers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.datetime.Clock
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import kotlinx.serialization.json.jsonObject
import ru.nm17.narodmon.appNarodMonApiClient.types.APIError
import ru.nm17.narodmon.appNarodMonApiClient.types.AppInitRequest
import ru.nm17.narodmon.appNarodMonApiClient.types.AppInitResponse
import ru.nm17.narodmon.appNarodMonApiClient.types.MandatoryParams
import ru.nm17.narodmon.db.AppDatabase
import ru.nm17.narodmon.db.entities.KVSetting
import java.util.Locale

const val allowedUuidChars = "abcdef0123456789"
const val uuidCharLength = 16

private val apiHttpClient = HttpClient(OkHttp) {
    headers {
        append(HttpHeaders.UserAgent, "IoTMonitor")
        append(HttpHeaders.AcceptEncoding, "br;q=1.0, gzip;q=0.6, deflate;q=0.6")
    }
    engine {
        // this: OkHttpConfig
        config {
            // this: OkHttpClient.Builder
            followRedirects(true)
        }
    }
}

class Client private constructor(
    private var uuid: String,
    private var _rawHttpClient: HttpClient
) {
    private val lang: String = Locale.getDefault().language

    val httpClient1Min = flow {
        while (true) {
            emit(_rawHttpClient)
            delay(1000)
        }
    }.flowOn(Dispatchers.Default)

    companion object {
        fun fromDb(db: AppDatabase): Flow<Client> {
            return flow {
                val uuid = db.kvDao().getByKey("current_user_uuid")?.value

                if (uuid == null) {
                    val newUuid =
                        List(uuidCharLength) { allowedUuidChars.random() }.joinToString("")
                    db.kvDao().setAll(KVSetting("current_user_uuid", newUuid))
                    db.kvDao().setAll(
                        KVSetting(
                            "current_user_uuid_generated_at_unix_ms_utc",
                            Json.encodeToString(Clock.System.now().epochSeconds)
                        )
                    )
                    emit(Client(newUuid, apiHttpClient))
                } else {
                    emit(Client(uuid, apiHttpClient))
                }
            }.flowOn(Dispatchers.IO)
        }
    }

    private fun getMandatoryParams(cmd: String): MandatoryParams {
        return MandatoryParams(
            cmd,
            this@Client.lang,
            this@Client.uuid,
            API_KEY
        )
    }

    private fun getJSONRequestBody(cmd: String, request: @Serializable Any) {
        mergeJsonObjects(
            Json.encodeToJsonElement(request).jsonObject,
            Json.encodeToJsonElement(
                getMandatoryParams(cmd)
            ).jsonObject
        )
    }

    private suspend inline fun <reified RT> deserializeResponse(resp: HttpResponse): Result4k<RT, APIError> {
        val body: String = resp.body()
        return try {
            Success(Json.decodeFromString(body))
        } catch (err: SerializationException) {
            Failure(Json.decodeFromString(body))
        } catch (err: Exception) {
            throw err
        }
    }

    public fun appInit(request: AppInitRequest): Flow<Result4k<AppInitResponse, APIError>> {
        val client = this.httpClient1Min
        return flow<Result4k<AppInitResponse, APIError>> {

            val resp = client.post("https://narodmon.ru/api") {
                contentType(ContentType.Application.Json)
                setBody(
                    getJSONRequestBody("appInit", request)
                )
            }

            emit(deserializeResponse(resp))

        }.flowOn(Dispatchers.IO)

    }
    public fun appInit(request: AppInitRequest): Flow<Result4k<AppInitResponse, APIError>> {
        val client = this.httpClient1Min
        return flow<Result4k<AppInitResponse, APIError>> {

            val resp = client.post("https://narodmon.ru/api") {
                contentType(ContentType.Application.Json)
                setBody(
                    getJSONRequestBody("appInit", request)
                )
            }

            emit(deserializeResponse(resp))

        }.flowOn(Dispatchers.IO)

    }
}