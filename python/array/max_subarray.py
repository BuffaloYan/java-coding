def max_subarray(nums):
    """
    Find the contiguous subarray with the largest sum
    
    Args:
        nums: List[int] - array of integers
    
    Returns:
        int - maximum sum of any contiguous subarray
    """
    if not nums:
        return 0
        
    max_sum = nums[0]
    current_sum = nums[0]
    
    for num in nums[1:]:
        current_sum = max(num, current_sum + num)
        max_sum = max(max_sum, current_sum)
    
    return max_sum

# Example usage:
if __name__ == "__main__":
    test_nums = [-2, 1, -3, 4, -1, 2, 1, -5, 4]
    result = max_subarray(test_nums)
    print(f"Maximum subarray sum: {result}")  # Expected output: 6