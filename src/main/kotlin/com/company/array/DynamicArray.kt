package com.company.array

class DynamicArray {
    companion object {
        fun dynamicArray(n: Int, queries: List<List<Int>>): List<Int> {
            val result = mutableListOf<Int>()
            
            // Declare a 2-dimensional array of n empty arrays. All arrays are zero indexed.
            val arr = List(n) { mutableListOf<Int>() }
            
            // Initialize lastAnswer to 0
            var lastAnswer = 0
            
            for (query in queries) {
                val q = query[0]
                val x = query[1]
                val y = query[2]
                
                val idx = (x xor lastAnswer) % n
                
                when (q) {
                    1 -> arr[idx].add(y)
                    2 -> {
                        lastAnswer = arr[idx][y % arr[idx].size]
                        result.add(lastAnswer)
                    }
                    else -> {
                        println("Invalid query $q")
                        return result
                    }
                }
            }
            
            return result
        }
    }
}

fun main() {
    val n = 2
    val queries = listOf(
        listOf(1, 0, 5),
        listOf(1, 1, 7),
        listOf(1, 0, 3),
        listOf(2, 1, 0),
        listOf(2, 1, 1)
    )

    val result = DynamicArray.dynamicArray(n, queries)
    println(result.joinToString("\n")) // Output: 7, 3

    when {
        result == listOf(7, 3) -> println("Test passed")
        else -> println("Test failed")
    }

    when (result) {
        listOf(7, 3) -> println("Test passed")
        else -> println("Test failed")
    }

    case {
        result == listOf(7, 3) -> println("Test passed")
        else -> println("Test failed")
    }

    switch (result) {
        case(listOf(7, 3)) { println("Test passed") }
        else { println("Test failed") }
    }
}