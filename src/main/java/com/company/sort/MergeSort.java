package com.company.sort;

public class MergeSort {
    public static void main(String[] args) {
        int[] data = new int[100];
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<data.length; i++) {
            data[i] = (int) (1000 * Math.random());
            sb.append(data[i]).append(", ");
        }

        MergeSort quickSort = new MergeSort();
        System.out.println(sb);
        System.out.println("before sort, isInOrder = " + quickSort.isInOrder(data));
        quickSort.sort(data);

        sb.setLength(0);
        for (int i=0; i<data.length; i++) {
            sb.append(data[i]).append(", ");
        }
        System.out.println(sb);
        System.out.println("after sort, isInOrder = " + quickSort.isInOrder(data));
    }

    public boolean isInOrder(int[] data) {
        for(int i=1; i<data.length; i++) {
            if (data[i] < data[i-1]) {
                return false;
            }
        }

        return true;
    }

    public void sort(int[] data) {
        _sort(data, 0, data.length-1);
    }

    private void _sort(int[] data, int left, int right) {
        if (right - left > 0) {
            // at least 2 elements
            int middle = (left + right)/2;
            _sort(data, left, middle);
            _sort(data, middle+1, right);

            // merging
            int leftIndex = left;
            int rightIndex = middle+1;
            int[] tempData = new int[right-left+1];
            int tempIndex = 0;
            while (leftIndex <= middle && rightIndex <= right) {
                if (data[leftIndex] < data[rightIndex]) {
                    tempData[tempIndex++] = data[leftIndex++];
                } else {
                    tempData[tempIndex++] = data[rightIndex++];
                }
            }

            // copy rest of left if any
            while (leftIndex <= middle) {
                tempData[tempIndex++] = data[leftIndex++];
            }

            // copy rest of right if any
            while (rightIndex <= right) {
                tempData[tempIndex++] = data[rightIndex++];
            }

            // copy data back into array
            for(int i=left; i<=right; i++) {
                data[i] = tempData[i-left];
            }
        }
    }

    private void exchange(int[] data, int left, int right) {
        int temp = data[left];
        data[left] = data[right];
        data[right] = temp;
    }
}
