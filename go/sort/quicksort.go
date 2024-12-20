package sort

// QuickSort sorts an integer slice using the QuickSort algorithm
func QuickSort(data []int) {
    if len(data) == 0 {
        return
    }
    quickSort(data, 0, len(data)-1)
}

func quickSort(data []int, left, right int) {
    if left >= right {
        return
    }

    // Choose pivot as the rightmost element
    pivot := data[right]
    i := left - 1

    // Partition
    for j := left; j < right; j++ {
        if data[j] <= pivot {
            i++
            data[i], data[j] = data[j], data[i]
        }
    }

    // Place pivot in correct position
    data[i+1], data[right] = data[right], data[i+1]

    // Recursively sort subarrays
    quickSort(data, left, i)
    quickSort(data, i+2, right)
}