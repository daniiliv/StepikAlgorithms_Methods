import java.util.Scanner;

/**
 * Задача на программирование: число инверсий
 *
 * ﻿
 *
 * Первая строка содержит число \( 1 \le n \le 10^5 \), вторая — массив
 * \( A[1\ldots n] \), содержащий натуральные числа, не превосходящие \( 10^9 \).
 * Необходимо посчитать число пар индексов \( 1 \le i \lt j \le n \), для которых
 * \( A[i] \gt A[j] \). (Такая пара элементов называется инверсией массива. Количество
 * инверсий в массиве является в некотором смысле его мерой неупорядоченности:
 * например, в упорядоченном по неубыванию массиве инверсий нет вообще, а в массиве,
 * упорядоченном по убыванию, инверсию образуют каждые два элемента.)
 */

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        //Число элементов массива.
        int arrayLength = scanner.nextInt();
        scanner.nextLine();

        int[] array = convertInputtedLineToIntArray(scanner.nextLine().split(" "), arrayLength);


        new MergeSorting().mergeSort(array, 0, arrayLength - 1);

        //System.out.println(Arrays.toString(array));
        System.out.println(MergeSorting.getNumberOfInversions());

    }

    /**
     * Читает пользовательский ввод согласно заданию.
     */
    private static int[] convertInputtedLineToIntArray(String[] strings, int len) {

        int[] resultArray = new int[len];

        for (int i = 0; i < len; i++) {
            resultArray[i] = Integer.valueOf(strings[i]);
        }
        return resultArray;
    }
}

