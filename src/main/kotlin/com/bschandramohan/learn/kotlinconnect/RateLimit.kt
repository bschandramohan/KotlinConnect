package com.bschandramohan.learn.kotlinconnect

import java.time.LocalDateTime

class Tuple {
    var maxSeconds: Int = 0
    var numberRequests = 0
}
var cache = HashMap<String, Tuple>()

fun rateLimit(key: String, intervalInSecs: Int, maxLimit: Int): Boolean {
    // Fixed window counter solution
    // 1. Get the current bucket, see if it has expired, if "yes", create one
    // 2. See if the bucket is already full, if "yes", return "true",
    // 3. if the bucket is not full, increment the bucket counter and return "false"
    val currTimeSecs = LocalDateTime.now().toLocalTime().toSecondOfDay()
    val allowedSecs = currTimeSecs + intervalInSecs
    return if (cache.containsKey(key)) {
        val tuple = cache[key]!!
        if (currTimeSecs <= allowedSecs) {
            // Bucket found for this period
            if (tuple.numberRequests >= maxLimit) {
                // Block
                return true
            }
            tuple.numberRequests += 1
            false
        } else {
            // The bucket has expired, replace with a new one
            cache[key] = createTuple(allowedSecs)
            // Do not block
            false
        }
    } else {
        // Bucket not found, create a new one
        cache[key] = createTuple(allowedSecs)

        // Do not block
        false
    }
}

private fun createTuple(scaleSeconds: Int) =
    Tuple().apply {
        this.maxSeconds = scaleSeconds
        this.numberRequests = 1
    }

fun main(args: Array<String>) {
    println(rateLimit("device_info", 3, 3))
    Thread.sleep(5)
    println(rateLimit("device_info", 3, 3))
    println(rateLimit("device_info", 3, 3))
    println(rateLimit("device_info", 3, 3))
    println(rateLimit("device_info", 3, 3))
    println(rateLimit("device_info", 3, 3))
}
