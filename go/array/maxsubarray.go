package array

// MaxSubArray finds the contiguous subarray with the largest sum
func MaxSubArray(nums []int) int {
    if len(nums) == 0 {
        return 0
    }
    
    maxSum := nums[0]
    currentSum := nums[0]
    
    for i := 1; i < len(nums); i++ {
        // Either start a new subarray at current element,
        // or continue the previous subarray
        if currentSum+nums[i] > nums[i] {
            currentSum += nums[i]
        } else {
            currentSum = nums[i]
        }
        
        // Update maximum sum if current sum is larger
        if currentSum > maxSum {
            maxSum = currentSum
        }
    }
    
    return maxSum
}