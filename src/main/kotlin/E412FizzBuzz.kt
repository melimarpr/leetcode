fun main() {
    val solution = Solution()

    val collector = solution.fizzBuzz(15)
    print(collector)

}


private class Solution {
    fun fizzBuzz(n: Int): List<String> {
        val fizzBuzzCollector = mutableListOf<String>()
        for (i in 1..n) {
            val valueToAdd = if (i % 3 == 0 && i % 5 == 0) {
                "FizzBuzz"
            } else if (i % 3 == 0) {
                "Fizz"
            } else if (i % 5 == 0) {
                "Buzz"
            } else {
                i.toString()
            }
            fizzBuzzCollector.add(valueToAdd)
        }
        return fizzBuzzCollector
    }
}