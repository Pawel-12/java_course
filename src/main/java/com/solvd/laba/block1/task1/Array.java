package com.solvd.laba.block1.task1;

class Array {
    public static void sort(int[] ar, int left, int right) {
        if ((right - left) <= 1)
            return;


        int p = left;
        int q = right - 1;

        int pivotValue = ar[(left + right) / 2];


        while (p <= q) {
            while (ar[p] < pivotValue)
                p++;

            while (ar[q] > pivotValue)
                q--;

            if (p <= q) {
                int temp = ar[p];
                ar[p] = ar[q];
                ar[q] = temp;

                p++;
                q--;
            }
        }

        if (q + 1 > left)
            sort(ar, left, q + 1);

        if (p < right)
            sort(ar, p, right);
    }
}
