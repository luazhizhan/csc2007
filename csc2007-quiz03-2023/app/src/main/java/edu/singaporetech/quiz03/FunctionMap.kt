package edu.singaporetech.quiz03

import android.util.Log
import kotlin.math.sqrt

object FunctionMap {

    fun fib(x: Int): Int = when (x) {
        0 -> 0
        1 -> 1
        else -> fib(x - 1) + fib(x - 2)
    }

    fun self(x: Int): Int {
        var y = x.toDouble()
        repeat(1000000000) {
            y = sqrt(y) * sqrt(y)
        }
        return y.toInt()
    }

    val map = mapOf<String, (Int) -> Int>(
        "fib" to ::fib,
        "self" to ::self
    )
}