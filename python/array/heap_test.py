import unittest
# from heap_copilot_ms import Heap
# from heap_chatgpt import Heap
# from heap_gemini import Heap
# from heap_github import Heap
from heap_grok import Heap


class TestHeap(unittest.TestCase):
    def test_min_heap(self):
        heap = Heap()
        heap.push(10)
        heap.push(5)
        heap.push(20)
        heap.push(1)
        self.assertEqual(heap.pop(), 1)
        self.assertEqual(heap.pop(), 5)
        self.assertEqual(heap.pop(), 10)
        self.assertEqual(heap.pop(), 20)

    def test_peek(self):
        heap = Heap()
        heap.push(10)
        heap.push(5)
        heap.push(20)
        self.assertEqual(heap.peek(), 5)  # Min value

    def test_delete_min_heap(self):
        heap = Heap()
        heap.push(3)
        heap.push(65)
        heap.delete(65)
        self.assertEqual(heap.peek(), 3)

        heap.delete(3)
        heap.push(7)
        self.assertEqual(heap.peek(), 7)

        heap.push(-1)
        self.assertEqual(heap.peek(), -1)

        heap.delete(-1)
        self.assertEqual(heap.peek(), 7)
        heap.delete(7)

    def test_delete_not_found(self):
        heap = Heap()
        heap.push(10)
        heap.delete(5)

    def test_empty_pop(self):
        heap = Heap()
        self.assertIsNone(heap.pop())

    def test_empty_peek(self):
        heap = Heap()
        self.assertIsNone(heap.peek())

    def test_empty_delete(self):
        heap = Heap()
        heap.delete(10)


if __name__ == "__main__":
    unittest.main()