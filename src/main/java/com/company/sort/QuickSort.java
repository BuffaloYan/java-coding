package com.company.sort;

public class QuickSort {
    public static void main(String[] args) {
        int[] data = new int[100];
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<data.length; i++) {
            data[i] = (int) (1000 * Math.random());
            sb.append(data[i]).append(", ");
        }

        QuickSort quickSort = new QuickSort();
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
        // System.out.println("_sort(" + left + ", " + right + ")");
        if (right - left <= 0) {
            // nothing to do for one element (or less) array
            return;
        } else if (right - left == 1){
            // 2 elements, minor improvement
            if (data[right] < data[left]) {
                exchange(data, left, right);
                return;
            }
        }

        int middle = (left+right)/2;
        int pivotValue = data[middle];
        // pivoting
        int leftScan = left;
        int rightScan = right;
        while (leftScan < rightScan) {
            while (data[leftScan] < pivotValue) leftScan++;
            while (data[rightScan] > pivotValue) rightScan--;

            if (leftScan < rightScan) {
                // exchange out of order elements and continue
                exchange(data, leftScan, rightScan);
                leftScan++;
                rightScan--;
            } else if (leftScan == rightScan) {
                // skip the middle one for next recursion
                leftScan++;
                rightScan--;
                break;
            } else {
                // nothing to do
                break;
            }

        }

        // sort left
        _sort(data, left, rightScan);
        // sort right
        _sort(data, leftScan, right);
    }

    private void exchange(int[] data, int left, int right) {
        int temp = data[left];
        data[left] = data[right];
        data[right] = temp;
    }
}
