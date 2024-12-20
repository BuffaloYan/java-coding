package array

// BinarySearch performs binary search on a sorted slice to find insert position for a value
func BinarySearch(arr []int, value int) int {
    left := 0
    right := len(arr)

    for left < right {
        mid := (left + right) / 2
        if arr[mid] == value {
            return mid
        } else if arr[mid] < value {
            left = mid + 1
        } else {
            right = mid
        }
    }

    return left
}