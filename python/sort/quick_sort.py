def quick_sort(data):
    """
    Sort the input array using QuickSort algorithm
    
    Args:
        data: List of integers to sort
    """
    if not data:
        return
    _sort(data, 0, len(data) - 1)

def _sort(data, left, right):
    if left >= right:
        return
        
    # Choose pivot as the rightmost element
    pivot = data[right]
    i = left - 1
    
    # Partition
    for j in range(left, right):
        if data[j] <= pivot:
            i += 1
            data[i], data[j] = data[j], data[i]
    
    # Place pivot in correct position
    data[i + 1], data[right] = data[right], data[i + 1]
    
    # Recursively sort subarrays
    _sort(data, left, i)
    _sort(data, i + 2, right)

# Example usage:
if __name__ == "__main__":
    test_data = [64, 34, 25, 12, 22, 11, 90]
    quick_sort(test_data)
    print(f"Sorted array: {test_data}")