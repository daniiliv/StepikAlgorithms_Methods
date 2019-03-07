/**
 * Дано число \( 1 \le n \le 10^7 \), необходимо найти последнюю
 * цифру \( n \)-го числа Фибоначчи.
 *
 * Как мы помним, числа Фибоначчи растут очень быстро, поэтому
 * при их вычислении нужно быть аккуратным с переполнением. В
 * данной задаче, впрочем, этой проблемы можно избежать, поскольку
 * нас интересует только последняя цифра числа Фибоначчи: если
 * \( 0 \le a,b \le 9 \) — последние цифры чисел \( F_i \) и
 * \( F_{i+1} \) соответственно, то \( (a+b) \bmod{10} \) —
 * последняя цифра числа \( F_{i+2} \).
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
      fibArray.add(i, (fibArray.get(i - 1) % 10 + fibArray.get(i - 2) % 10) % 10);
    }

    System.out.println(fibArray.get(n));
  }
}