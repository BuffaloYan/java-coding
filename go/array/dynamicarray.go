package array

// DynamicArray simulates a dynamic array with sequence list operations
func DynamicArray(n int, queries [][]int) []int {
    // Initialize sequences
    sequences := make([][]int, n)
    for i := range sequences {
        sequences[i] = make([]int, 0)
    }
    
    lastAnswer := 0
    answers := make([]int, 0)
    
    for _, query := range queries {
        queryType := query[0]
        x := query[1]
        y := query[2]
        seqIdx := (x ^ lastAnswer) % n
        
        if queryType == 1 {
            // Append y to sequence at seqIdx
            sequences[seqIdx] = append(sequences[seqIdx], y)
        } else { // queryType == 2
            // Get value at index y % size in sequence at seqIdx
            size := len(sequences[seqIdx])
            lastAnswer = sequences[seqIdx][y%size]
            answers = append(answers, lastAnswer)
        }
    }
    
    return answers
}