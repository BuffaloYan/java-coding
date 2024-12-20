class Heap:
    def __init__(self):
        self.heap = []

    def parent(self, i):
        return (i - 1) // 2

    def left_child(self, i):
        return 2 * i + 1

    def right_child(self, i):
        return 2 * i + 2

    def swap(self, i, j):
        self.heap[i], self.heap[j] = self.heap[j], self.heap[i]

    def push(self, key):
        self.heap.append(key)
        self._heapify_up(len(self.heap) - 1)

    def _heapify_up(self, i):
        parent = self.parent(i)
        if i > 0 and self.heap[i] < self.heap[parent]:
            self.swap(i, parent)
            self._heapify_up(parent)

    def pop(self):
        if len(self.heap) == 0:
            return None
        if len(self.heap) == 1:
            return self.heap.pop()

        min_value = self.heap[0]
        self.heap[0] = self.heap.pop()
        self._heapify_down(0)
        return min_value

    def _heapify_down(self, i):
        min_index = i
        left = self.left_child(i)
        right = self.right_child(i)

        if left < len(self.heap) and self.heap[left] < self.heap[min_index]:
            min_index = left

        if right < len(self.heap) and self.heap[right] < self.heap[min_index]:
            min_index = right

        if min_index != i:
            self.swap(i, min_index)
            self._heapify_down(min_index)

    def peek(self):
        return self.heap[0] if self.heap else None

    def delete(self, key):
        try:
            i = self.heap.index(key)
        except ValueError:
            # Key not found in the heap
            return False

        # If the key is not the last element, move the last element to this position
        if i != len(self.heap) - 1:
            self.heap[i] = self.heap[-1]
            self.heap.pop()

            # Heapify from the position where we moved the last element
            parent = self.parent(i)
            if i > 0 and self.heap[i] < self.heap[parent]:
                self._heapify_up(i)
            else:
                self._heapify_down(i)
        else:
            # If the key is the last element, simply remove it
            self.heap.pop()

        return True

    def __str__(self):
        return str(self.heap)