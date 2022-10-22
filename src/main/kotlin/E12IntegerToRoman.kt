import kotlin.math.abs
import kotlin.math.pow
import kotlin.text.digitToInt

fun main() {
    val realSolution = solutions.entries.associateBy({ it.value }) { it.key }
    /*
        realSolution.forEach{
            println("Number is ${it.key}")
            println("Real Result is ${it.value}")
            println("Computed Result is ${E12.intToRoman(it.key)}")
        }*/

    val e12solver = E12()
    println(e12solver.intToRoman(1904))
}

private val solutions = mapOf<String, Int>(
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

private class E12 {

    private val symbols = mapOf(
        1 to "I",
        5 to "V",
        10 to "X",
        50 to "L",
        100 to "C",
        500 to "D",
        1000 to "M"
    )


    private val romanValueStack = mutableListOf<String>()

    fun intToRoman(num: Int): String {
        romanValueStack.clear()

        //1st. Split into it's individual exponents
        val numStr = num.toString()
        val individualIntegersWithExponents = mutableListOf<ScientificNotation>()
        var counterMultiplicator = 10.toDouble().pow(numStr.length - 1).toInt()

        numStr.forEach {
            individualIntegersWithExponents.add(
                ScientificNotation(
                    it.toString().toInt(),
                    counterMultiplicator
                )
            )
            counterMultiplicator /= 10
        }

        individualIntegersWithExponents.filter { it.num > 0 }.forEach {
            intToRomanRecursiveForExponentForms(it)
        }


        return romanValueStack.joinToString(separator = "")
    }

    private fun intToRomanRecursiveForExponentForms(num: ScientificNotation) {
        val closesSymbol = symbols[num.toFullRepresentation()]
        if (closesSymbol != null) {
            romanValueStack.add(closesSymbol)
            return
        } else if (num.num == 9 || num.num == 4) {
            romanValueStack.add(return9or4EdgeCase(num))
            return

        } else {
            val romanNumber = findClosesRomanNumber(num)
            val newNum = ScientificNotation(
                num = (num.num - (romanNumber.arabicNum / num.exponentFull)),
                exponentFull = num.exponentFull
            )
            romanValueStack.add(romanNumber.romanString)
            println("Roman Number$romanNumber")
            println("New Number$newNum")
            intToRomanRecursiveForExponentForms(newNum)
        }
    }

    private fun return9or4EdgeCase(scientificNotation: ScientificNotation): String {
        return if (scientificNotation.num == 4 && scientificNotation.exponentFull == 1) {
            "IV"
        } else if (scientificNotation.num == 9 && scientificNotation.exponentFull == 1) {
            "IX"
        } else if (scientificNotation.num == 4 && scientificNotation.exponentFull == 10) {
            "XL"
        } else if (scientificNotation.num == 9 && scientificNotation.exponentFull == 10) {
            "XC"
        } else if (scientificNotation.num == 4 && scientificNotation.exponentFull == 100) {
            "CD"
        } else if (scientificNotation.num == 9 && scientificNotation.exponentFull == 100) {
            "CM"
        } else {
            throw IllegalStateException("Error")
        }
    }

    private fun findClosesRomanNumber(scientificNotation: ScientificNotation): RomanNumber {
        val listOfSymbols = symbols.entries.toList().filter { it.key <= scientificNotation.toFullRepresentation() }

        var start = 0
        var end: Int = listOfSymbols.size - 1
        while (start + 1 < end) {
            val mid = start + (end - start) / 2
            if (listOfSymbols[mid].key <= scientificNotation.toFullRepresentation()) {
                start = mid
            } else {
                end = mid
            }
        }
        val left: Int = abs(listOfSymbols[start].key - scientificNotation.toFullRepresentation())
        val right: Int = abs(listOfSymbols[end].key - scientificNotation.toFullRepresentation())
        val index = if (left <= right) {
            start
        } else end

        return RomanNumber(
            romanString = listOfSymbols[index].value,
            arabicNum = listOfSymbols[index].key
        )

    }

    private data class ScientificNotation(
        val num: Int,
        val exponentFull: Int //10, 100, 1000
    ) {
        fun toFullRepresentation() = num * exponentFull
    }

    private data class RomanNumber(
        val romanString: String,
        val arabicNum: Int
    )

}

