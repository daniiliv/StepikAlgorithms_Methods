/**
 * Дано целое число \( 1 \le n \le 40 \), необходимо вычислить
 * \( n \)-е число Фибоначчи (напомним, что \( F_0=0 \),
 * \( F_1=1 \) и \( F_n=F_{n-1}+F_{n-2} \) при \( n \ge 2 \)).
 */

import java.math.BigInteger;
import java.util.*;

class Main {

    private BigInteger fibonacci(int n) {
        Map<Integer, BigInteger> cache = new HashMap<>();

        if (n < 2) {
            return BigInteger.valueOf(n);
        }
        if (cache.containsKey(n)) {
            return cache.get(n);
        } else {
            for (int i = 2; i <= n; i++) {
                BigInteger result = fibonacci(i - 1).add(fibonacci(i - 2));
                cache.put(i, result);
            }
            return cache.get(n);
        }
    }

    public void run(int n) {
        System.out.println("n: " + n + " fib number: " + fibonacci(n));
    }


    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        Scanner in = new Scanner(System.in);
        System.out.print("Input n: ");
        int n = in.nextInt();

        new Main().run(n);

        long stopTime = System.currentTimeMillis();

        System.out.println("Time: " + (stopTime - startTime) + " ms");
    }
}