@file:JvmName("Utils")
@file:JvmMultifileClass

package edu.singaporetech.madata

import kotlin.random.Random

/**
 * Noninstantiable utility class
 * @author jeannie on 2/2/20.
 * Based on the recommendations in "Effective Java" ported to Kotlin
 * Arguments against utility classes are also valid based on
 * testability, but we'll leave the arguments for another day
 */

class Utils private constructor() {

    companion object {
        @JvmStatic
        fun generateRandomFourDigitNumber(): Int {
            return Random.nextInt(1000, 9999)
        }
    }

}
