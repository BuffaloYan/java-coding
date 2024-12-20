class Heap:
    def __init__(self, is_min_heap=True):
        self.heap = []
        self.is_min_heap = is_min_heap

    def _compare(self, parent, child):
        if self.is_min_heap:
            return parent > child
        return parent < child

    def push(self, value):
        """Insert a value into the heap."""
        self.heap.append(value)
        self._heapify_up(len(self.heap) - 1)

    def pop(self):
        """Remove and return the top value from the heap."""
        if len(self.heap) == 0:
            return None
        if len(self.heap) == 1:
            return self.heap.pop()

        top = self.heap[0]
        self.heap[0] = self.heap.pop()
        self._heapify_down(0)
        return top

    def peek(self):
        """Return the top value from the heap without removing it."""
        if len(self.heap) == 0:
            return None
        return self.heap[0]

    def _heapify_up(self, index):
        """Ensure the heap property is maintained after insertion."""
        parent_index = (index - 1) // 2
        while index > 0 and self._compare(self.heap[parent_index], self.heap[index]):
            self.heap[parent_index], self.heap[index] = self.heap[index], self.heap[parent_index]
            index = parent_index
            parent_index = (index - 1) // 2

    def _heapify_down(self, index):
        """Ensure the heap property is maintained after deletion."""
        size = len(self.heap)
        while True:
            smallest_or_largest = index
            left = 2 * index + 1
            right = 2 * index + 2

            if left < size and self._compare(self.heap[smallest_or_largest], self.heap[left]):
                smallest_or_largest = left
            if right < size and self._compare(self.heap[smallest_or_largest], self.heap[right]):
                smallest_or_largest = right

            if smallest_or_largest == index:
                break

            self.heap[index], self.heap[smallest_or_largest] = (
                self.heap[smallest_or_largest],
                self.heap[index],
            )
            index = smallest_or_largest

    def __len__(self):
        """Return the number of elements in the heap."""
        return len(self.heap)

    def __bool__(self):
        """Return True if the heap is not empty."""
        return bool(self.heap)

    def __str__(self):
        """Return the string representation of the heap."""
        return str(self.heap)

    def delete(self, value):
        """Delete a specific value from the heap."""

        try:
            index = self.heap.index(value)
            if index == len(self.heap) - 1:
                # If the element is the last element, remove it and return
                self.heap.pop()
                return

            # Replace the element with the last element
            self.heap[index] = self.heap.pop()
            # Re-balance the heap
            self._heapify_down(index)

        except ValueError:
            pass

# Example Usage
if __name__ == "__main__":
    # Min-heap
    min_heap = Heap()
    min_heap.push(5)
    min_heap.push(3)
    min_heap.push(8)
    min_heap.delete(3)
    print("Min-heap:", min_heap)  # Output: [3, 5, 8]
    print("Min:", min_heap.pop())  # Output: 5

    # Max-heap
    max_heap = Heap(is_min_heap=False)
    max_heap.push(5)
    max_heap.push(3)
    max_heap.push(8)
    print("Max-heap:", max_heap)  # Output: [8, 3, 5]
    print("Max:", max_heap.pop())  # Output: 8