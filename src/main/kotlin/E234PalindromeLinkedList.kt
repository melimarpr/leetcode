import kotlin.math.ceil
import kotlin.math.floor

fun main() {
    testCases.entries.forEach {
        val isPalindrome = isPalindrome(it.key)
        println("For Sequence ${it.key}")
        println("Expected: ${it.value}, Actual: $isPalindrome ")
    }

    println("Full Node Test 1,2,2,1")

    val n1 = ListNode(1)
    val n2 = ListNode(2)
    val n3 = ListNode(2)
    val n4 = ListNode(1)
    n1.next = n2
    n2.next = n3
    n3.next = n4

    println(println("Expected: true, Actual: ${isPalindrome(n1)}"))
}

private class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

private val testCases = mapOf(
    listOf(1) to true,
    listOf(1, 2) to false,
    listOf(1, 2, 1) to true,
    listOf(3, 2, 1) to false,
    listOf(1, 1, 1, 1) to true,
    listOf(1, 2, 2, 1) to true,
    listOf(1, 2, 3, 1) to false,
    listOf(1, 2, 3, 2, 1) to true,
    listOf(1, 2, 3, 4, 5) to false,

    )

private fun isPalindrome(head: ListNode?): Boolean {
    if (head == null) {
        return false
    }

    val indexLinkedListFromNodes = mutableListOf<Int>()

    //Get Size of Palindrome First
    indexLinkedListFromNodes.add(head.`val`)
    var nextStep = head.next

    while (nextStep != null) {
        indexLinkedListFromNodes.add(nextStep.`val`)
        nextStep = nextStep.next
    }

    return isPalindrome(indexLinkedListFromNodes)
}

private fun isPalindrome(indexLinkedListFromNodes: List<Int>): Boolean {
    //Divide i
    val size = indexLinkedListFromNodes.size

    if (size == 1) {
        return true
    }

    var leftIndex: Int
    var rightIndex: Int

    //Check if odd or even
    if (size % 2 == 0) { //Even
        leftIndex = size / 2 - 1
        rightIndex = size / 2
    } else { //Odd
        leftIndex = floor(size / 2.0).toInt() - 1
        rightIndex = ceil(size / 2.0).toInt()
    }

    for (i in 1..floor(size / 2.0).toInt()) {
        if (indexLinkedListFromNodes[leftIndex] != indexLinkedListFromNodes[rightIndex]) {
            return false
        }
        leftIndex -= 1
        rightIndex += 1
    }
    return true
}