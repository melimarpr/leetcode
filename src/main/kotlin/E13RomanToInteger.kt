import java.lang.IllegalStateException

fun main() {
    E13RomanToInteger.solutions.forEach {
        print("Key: ${it.key} - ")
        print("Actual: ${it.value} - ")
        println("Generated: ${E13RomanToInteger.romanToInt(it.key)}")

        E13RomanToInteger.romanToInt("VI")
    }
}

object E13RomanToInteger {
    val symbols = mapOf(
        'I' to 1,
        'V' to 5,
        'X' to 10,
        'L' to 50,
        'C' to 100,
        'D' to 500,
        'M' to 1000
    )

    val solutions = mapOf<String, Int>(
        "I" to 1,
        "II" to 2,
        "III" to 3,
        "IV" to 4,
        "V" to 5,
        "VI" to 6,
        "VII" to 7,
        "VIII" to 8,
        "IX" to 9,
        "X" to 10,
        "XI" to 11,
        "XII" to 12,
        "XIII" to 13,
        "XIV" to 14,
        "XV" to 15,
        "XVI" to 16,
        "XVII" to 17,
        "XVIII" to 18,
        "XIX" to 19,
        "XX" to 20,
        "L" to 50,
        "C" to 100,
        "D" to 500,
        "MCMXCIV" to 1994
    )

    fun romanToInt(s: String): Int {
        if (s.isBlank()) {
            throw IllegalStateException("String is Blank")
        }
        var sum = 0
        var index = 0
        while (index < s.length) {
            val romanChar = s[index]
            val nextRomanChar = try {
                s[index + 1]
            } catch (_: Exception) {
                null
            }
            if (romanChar == 'I') {
                sum += when (nextRomanChar) {
                    'V' -> {
                        index++
                        4
                    }

                    'X' -> {
                        index++
                        9
                    }

                    else -> {
                        symbols[romanChar] ?: 0
                    }
                }
            } else if (romanChar == 'X') {
                sum += when (nextRomanChar) {
                    'L' -> {
                        index++
                        40
                    }

                    'C' -> {
                        index++
                        90
                    }

                    else -> {
                        symbols[romanChar] ?: 0
                    }
                }
            } else if (romanChar == 'C') {
                sum += when (nextRomanChar) {
                    'D' -> {
                        index++
                        400
                    }

                    'M' -> {
                        index++
                        900
                    }

                    else -> {
                        symbols[romanChar] ?: 0
                    }
                }
            } else {
                sum += symbols[romanChar] ?: 0
            }

            index++
        }
        return sum

    }
}

