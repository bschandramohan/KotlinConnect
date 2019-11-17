package com.bschandramohan.learn.kotlinconnect

enum class ReviewState {
    UNREVIEWED,
    REVIEWED
}

data class TripBasic(
    var distance: Long? = null,
    var reviewState: ReviewState? = null
)

data class TripWithLambdaExtensions(
    var distance: Long? = null,
    var reviewState: ReviewState? = null) {

    operator fun invoke(distance: Long?.() -> Unit) {
    }
//    operator fun invoke(reviewStateFun : ReviewState.() -> Unit) {
//    }
}

fun main() {
    val trip = TripBasic()
    trip.distance = 10L
    trip.reviewState = ReviewState.UNREVIEWED
    println(trip)

    val trip1 = TripWithLambdaExtensions()
    trip1 {
        10L
       // ReviewState.UNREVIEWED
    }
    println(trip1)
}
