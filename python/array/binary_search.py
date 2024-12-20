def binary_search(arr, value):
    """
    Performs binary search on a sorted list to find insert position for a value
    
    Args:
        arr: List[int] - sorted list of integers
        value: int - value to find insert position for
    
    Returns:
        int - position where value should be inserted to maintain sort order
    """
    left = 0
    right = len(arr)

    while left < right:
        mid = (left + right) // 2
        if arr[mid] == value:
            return mid
        elif arr[mid] < value:
            left = mid + 1
        else:
            right = mid

    return left

# Example usage:
if __name__ == "__main__":
    test_arr = [1, 3, 5, 6]
    test_value = 5
    position = binary_search(test_arr, test_value)
    print(f"Insert position for {test_value}: {position}")