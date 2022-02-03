package com.bschandramohan.learn.kotlinconnect.coroutines

import kotlinx.coroutines.*
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import kotlin.random.Random
import kotlin.random.nextInt
import kotlin.system.measureTimeMillis

class DispatcherIOSample {
    private val httpClient: HttpClient = HttpClient.newHttpClient()
    val charPool : List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')

    private fun randomIdGenerator(): String = (1..6)
        .map { Random.nextInt(0, charPool.size) }
        .map { i -> charPool[i] }
        .joinToString("")

    suspend fun execute(dispatcherType: CoroutineDispatcher? = null) {
        println("\n\nExecuting for dispatcherType=${dispatcherType}")
        val timeTaken = measureTimeMillis {
            val differedResults = mutableListOf<Deferred<String>>()
            runBlocking {
                (1..3).forEach { _ ->
                    if (dispatcherType == null) {
                        differedResults.add(
                            async {
                                serviceCall(randomIdGenerator())
                            }
                        )
                    } else {
                        differedResults.add(
                            async(dispatcherType) {
                                serviceCall(randomIdGenerator())
                            }
                        )
                    }
                }

                differedResults.awaitAll()
            }
        }

        println("Total timeTaken=$timeTaken\n")
    }

    private fun serviceCall(id: String) : String {
        println("Started ServiceCall with id=$id")
        val uri = URI("https://httpbin.org/delay/5?id=$id")
        var response = "ERROR"

        val timeTaken = measureTimeMillis {
            response = httpClient.send(HttpRequest.newBuilder(uri).build(), HttpResponse.BodyHandlers.ofString()).body()
                ?: "ERROR"
        }
        println("Ended ServiceCall id=$id timeTaken=$timeTaken")

        return response
    }
}

fun main() = runBlocking {
    DispatcherIOSample().execute()
    DispatcherIOSample().execute(Dispatchers.Default)
    DispatcherIOSample().execute(Dispatchers.IO)
}
