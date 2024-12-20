def merge_sort(data):
    """
    Sort the input array using MergeSort algorithm
    
    Args:
        data: List of integers to sort
    """
    if not data:
        return
    _sort(data, 0, len(data) - 1)

def _sort(data, left, right):
    if left >= right:
        return
        
    # Find middle point
    mid = (left + right) // 2
    
    # Sort first and second halves
    _sort(data, left, mid)
    _sort(data, mid + 1, right)
    
    # Merge the sorted halves
    merge(data, left, mid, right)

def merge(data, left, mid, right):
    # Create temp arrays
    L = data[left:mid + 1]
    R = data[mid + 1:right + 1]
    
    # Initial indexes
    i = 0  # Initial index of first subarray
    j = 0  # Initial index of second subarray
    k = left  # Initial index of merged subarray
    
    # Merge temp arrays back into data[left..right]
    while i < len(L) and j < len(R):
        if L[i] <= R[j]:
            data[k] = L[i]
            i += 1
        else:
            data[k] = R[j]
            j += 1
        k += 1
    
    # Copy remaining elements of L[] if any
    while i < len(L):
        data[k] = L[i]
        i += 1
        k += 1
    
    # Copy remaining elements of R[] if any
    while j < len(R):
        data[k] = R[j]
        j += 1
        k += 1

# Example usage:
if __name__ == "__main__":
    test_data = [64, 34, 25, 12, 22, 11, 90]
    merge_sort(test_data)
    print(f"Sorted array: {test_data}")