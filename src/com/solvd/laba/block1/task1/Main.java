package com.solvd.laba.block1.task1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

class Array{
    public static void sort(int[] ar, int left, int right)
    {
        if((right - left) <= 1)
            return;


        int p = left;
        int q = right-1;

        int pivotValue = ar[(left+right)/2];


        while (p <= q)
        {
            while(ar[p] < pivotValue)
                p++;

            while(ar[q] > pivotValue)
                q--;

            if(p <= q)
            {
                int temp = ar[p];
                ar[p] = ar[q];
                ar[q] = temp;

                p++;
                q--;
            }
        }

        if(q+1 > left)
            sort(ar, left, q+1);

        if(p < right)
            sort(ar, p , right);
    }
}

public class Main {
    public static void main(String[] args) {
        for(String str : args)
            System.out.print(str + ' ');

        System.out.print('\n');

        //ArrayList<Integer> array = new ArrayList<>(NUMBERS);
        //ArrayList<Integer> array = new ArrayList<>(Arrays.asList(45, 66, 53, 39, 98, 61, 33  ));

        int NUMBERS = 20;


        for(int j = 0; j < 1000; j++) {

            Random rand = new Random();

            int[] array = new int[NUMBERS];
            int[] array2;

            for (int i = 0; i < NUMBERS; i++)
                array[i] = rand.nextInt(100);

            array2 = array.clone();


            for (int x : array)
                System.out.print(x + " ");

            System.out.print('\n');

            Array.sort(array, 0, array.length);
            Arrays.sort(array2);


            for (int x : array)
                System.out.print(x + " ");

            System.out.print("\t\t");

            for (int x : array2)
                System.out.print(x + " ");

            if(!Arrays.equals(array, array2))
            {
                System.out.println("Sorting error");
                return;
            }

            System.out.print('\n');
            System.out.print('\n');
        }
    }
}