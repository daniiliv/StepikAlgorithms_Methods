/**
 * Дано целое число \( 1 \le n \le 40 \), необходимо вычислить
 * \( n \)-е число Фибоначчи (напомним, что \( F_0=0 \),
 * \( F_1=1 \) и \( F_n=F_{n-1}+F_{n-2} \) при \( n \ge 2 \)).
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        int n;
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        List<Integer> fibArray = new ArrayList<>(n);

        fibArray.add(0);
        fibArray.add(1);

        for (int i = 2; i <= n; i++) {
            fibArray.add(i, fibArray.get(i - 1) + fibArray.get(i - 2));
        }

        System.out.println(fibArray.get(n));
    }
}