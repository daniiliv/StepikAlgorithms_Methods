/**
 * Задача на программирование: наибольший общий делитель
 *
 * По данным двум числам \( 1 \le a, b \le 2 \cdot 10^9 \)
 * найдите их наибольший общий делитель.
 */

import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long a = scanner.nextLong();
        long b = scanner.nextLong();

        long result = greatCommonDivisor(a, b);

        System.out.println(result);

    }

    private static long greatCommonDivisor(long a, long b) {
        if (a == 0) return b;
        else if (b == 0) return a;
        else if (a >= b) return greatCommonDivisor(a % b, b);
        else return greatCommonDivisor(a, b % a);
    }

}