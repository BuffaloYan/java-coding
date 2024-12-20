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