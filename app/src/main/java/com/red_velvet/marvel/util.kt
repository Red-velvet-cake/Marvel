package com.red_velvet.marvel

import kotlin.random.Random

fun isPrime(number: Int): Boolean {
    if (number <= 1) {
        return false
    }

    for (i in 2 until number) {
        if (number % i == 0) {
            return false
        }
    }

    return true
}

fun chooseCoprime(n: Int): Int {
    while (true) {
        val e = Random.nextInt(2, n)
        if (gcd(e, n) == 1) {
            return e
        }
    }
}

fun gcd(a: Int, b: Int): Int {
    if (b == 0) {
        return a
    }
    return gcd(b, a % b)
}