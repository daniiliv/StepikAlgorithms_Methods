import java.util.Arrays;
import java.util.Scanner;

/**
 * Двоичный поиск.
 * <p>
 * ﻿
 * <p>
 * В первой строке даны целое число \( 1 \le n \le 10^5 \) и массив
 * \( A[1 \ldots n] \) из \( n \) различных натуральных чисел, не превышающих \( 10^9 \),
 * в порядке возрастания, во второй — целое число \( 1 \le k \le 10^5 \) и \( k \)
 * натуральных чисел \( b_1, \ldots, b_k \), не превышающих \( 10^9 \).
 * Для каждого \( i \) от 1 до \( k \) необходимо вывести индекс \( 1 \le j \le n \),
 * для которого \( A[j]=b_i \), или \( -1 \), если такого \( j \) нет.
 */
class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //Вход - упорядоченный массив.
        int[] initialArray = convertInputtedLineToIntArray(scanner.nextLine().split(" "));
        
        //Искомые ключи.
        int[] keys = convertInputtedLineToIntArray(scanner.nextLine().split(" "));
        
        //Результат двоичного поиска.
        int[] result = new int[keys.length];

        //Для каждого ключа произведем двоичный поиск, добавим в результирующий массив.
        for (int i = 0; i < result.length; i++) {
            result[i] = binarySearch(initialArray, keys[i]);
        }

        //Выведем найденные значения.
        Arrays.stream(result).forEach(x -> System.out.print(x + " "));
    }

    /**
     * Читает пользовательский ввод согласно заданию.
     */
    private static int[] convertInputtedLineToIntArray(String[] strings) {

        //Number of elements in the array.
        int n = Integer.valueOf(strings[0]);
        int[] resultArray = new int[n];

        for (int i = 0; i < n; i++) {
            resultArray[i] = Integer.valueOf(strings[i + 1]);
        }
        return resultArray;
    }


    /**
     * Метод двоичного поиска.
     * @param initialArray Упорядоченный массив.
     * @param key Искомое значение.
     * @return Индекс найденного значения (Индексация начинается с 1).
     */
    private static int binarySearch(int[] initialArray, int key) {
        int result;

        //Левая граница.
        int l = 0;

        //Правая граница.
        int r = initialArray.length - 1;

        //Средняя точка между левой и правой границей.
        int m;

        //Пока область поиска не равна одному элементу.
        while (l <= r) {

            //Вычисляем среднюю точку как (l + r) / 2.
            m = l + (r - l) / 2;
            if (key == initialArray[m]) {
                result = m;
                return ++result;

                //Если искомое значение меньше значения по указанному индексу.
                //Сдвигаем правую границу.
            } else if (key < initialArray[m]) {
                r = m - 1;

                //Иначе сдвигаем левую.
            } else {
                l = m + 1;
            }
        }

        return -1;
    }
}