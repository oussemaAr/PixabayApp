package io.studio.pixabayapp.common.data

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.DefaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel.BODY
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.http.ContentType.Application
import io.ktor.http.HttpHeaders
import kotlinx.serialization.json.Json
import timber.log.Timber

object NetworkClient {

    suspend inline fun <reified T> GET(query: String, perPage: Int = 50) =
        client.get<T>("https://pixabay.com/api/") {
            url {
                parameters.append("key", "34928131-83274bbc14b4c81303dc2babc")
                parameters.append("q", query)
                parameters.append("per_page", perPage.toString())
            }
        }

    val client = HttpClient(Android) {
        engine {
            connectTimeout = 30 * 1000
        }

        install(JsonFeature) {
            serializer = KotlinxSerializer(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }

        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Timber.v(message)
                }
            }
            level = BODY
        }

        install(DefaultRequest) {
            header(HttpHeaders.ContentType, Application.Json)
        }
    }
}