/**
 * Задача на программирование: наибольший общий делитель
 *
 * По данным двум числам \( 1 \le a, b \le 2 \cdot 10^9 \)
 * найдите их наибольший общий делитель.
 */
import java.math.BigInteger;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input a and b: ");
        BigInteger a = scanner.nextBigInteger();
        BigInteger b = scanner.nextBigInteger();

        BigInteger result = greatCommonDivisor(a, b);

        System.out.println("GCD of a and b: " + result);

    }

    private static BigInteger greatCommonDivisor(BigInteger a, BigInteger b) {
        while (true) {
            if (a.equals(BigInteger.ZERO)) return b;
            else if (b.equals(BigInteger.ZERO)) return a;
            else if (a.compareTo(b) >= 0) {
                // a, b <- a % b, b
                a = a.mod(b);
            } else {
                // a, b <- a, b % a
                b = b.mod(a);
            }
        }
    }

}