package sort

// MergeSort sorts an integer slice using the MergeSort algorithm
func MergeSort(data []int) {
    if len(data) == 0 {
        return
    }
    mergeSort(data, 0, len(data)-1)
}

func mergeSort(data []int, left, right int) {
    if left >= right {
        return
    }

    // Find middle point
    mid := (left + right) / 2

    // Sort first and second halves
    mergeSort(data, left, mid)
    mergeSort(data, mid+1, right)

    // Merge the sorted halves
    merge(data, left, mid, right)
}

func merge(data []int, left, mid, right int) {
    // Create temp arrays
    L := make([]int, mid-left+1)
    R := make([]int, right-mid)

    // Copy data to temp arrays L[] and R[]
    for i := 0; i < len(L); i++ {
        L[i] = data[left+i]
    }
    for j := 0; j < len(R); j++ {
        R[j] = data[mid+1+j]
    }

    // Initial indexes
    i := 0 // Initial index of first subarray
    j := 0 // Initial index of second subarray
    k := left // Initial index of merged subarray

    // Merge temp arrays back into data[left..right]
    for i < len(L) && j < len(R) {
        if L[i] <= R[j] {
            data[k] = L[i]
            i++
        } else {
            data[k] = R[j]
            j++
        }
        k++
    }

    // Copy remaining elements of L[] if any
    for i < len(L) {
        data[k] = L[i]
        i++
        k++
    }

    // Copy remaining elements of R[] if any
    for j < len(R) {
        data[k] = R[j]
        j++
        k++
    }
}