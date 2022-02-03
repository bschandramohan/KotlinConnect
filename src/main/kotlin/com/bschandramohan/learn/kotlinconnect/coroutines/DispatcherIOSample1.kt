package com.bschandramohan.learn.kotlinconnect.coroutines

import kotlinx.coroutines.*
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import kotlin.system.measureTimeMillis

class DispatcherIOSample1 {
    private val httpClient: HttpClient = HttpClient.newHttpClient()

    private fun randomIdGenerator() = (Math.random() * 100000).toLong()

    suspend fun execute() = coroutineScope {
        val timeTaken = measureTimeMillis {
            val differedResults = mutableListOf<Deferred<String>>()
                (1..3).forEach { _ ->
                    differedResults.add(
                        async(Dispatchers.IO) {
                            // serviceCall(randomIdGenerator())
                            val uri = URI("https://httpbin.org/delay/5?id=${randomIdGenerator()}")
                            println("Service call started")
                            var resultOfClient = ""
                            val timeTaken = measureTimeMillis {
                                resultOfClient = httpClient.send(
                                    HttpRequest.newBuilder(uri).build(),
                                    HttpResponse.BodyHandlers.ofString()
                                ).body()
                                    ?: "ERROR"
                            }
                            println("ServiceCall ended timeTaken=$timeTaken")
                            resultOfClient
                        }
                    )
                }


            differedResults.awaitAll()
        }


        println("Total timeTaken=$timeTaken\n")
    }
}

fun main() = runBlocking {
    DispatcherIOSample1().execute()
}
