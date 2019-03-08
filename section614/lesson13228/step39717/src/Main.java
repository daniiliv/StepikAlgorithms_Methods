import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        long n;
        int m;
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextLong();
        m = scanner.nextInt();
        List<Integer> fibArray = new ArrayList<>((int)n);

        fibArray.add(0);
        fibArray.add(1);

        for (int i = 2; i <= n; i++) {
            fibArray.add(i, (fibArray.get(i - 1) % m + fibArray.get(i - 2) % m) % m);
        }

        System.out.println(fibArray.get(n));
    }
}