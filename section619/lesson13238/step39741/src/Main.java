/**
 * По данному числу \( 1 \le n \le 10^9 \) найдите максимальное число \( k \),
 * для которого \( n \) можно представить как сумму \( k \) различных натуральных
 * слагаемых. Выведите в первой строке число \( k \), во второй — \( k \) слагаемых.
 */
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

class Main {
    public static void main(String[] args) {
        Logger log = Logger.getLogger(Main.class.getName());

        Scanner scanner = new Scanner(System.in);
        BigInteger n = scanner.nextBigInteger();
        BigInteger i = BigInteger.ONE;
        List<BigInteger> resultArray = new ArrayList<>();

        log.warning("n: " + n);

        while (n.compareTo(BigInteger.ZERO) > 0) {

                if (i.add(BigInteger.ONE).compareTo(n.subtract(i)) > 0) {
                    i = n;
                }

                resultArray.add(i);
                log.warning("i: " + i + "| n: " + n);

                n = n.subtract(i);
                i = i.add(BigInteger.ONE);
        }

        System.out.println(resultArray.size());
        resultArray.forEach(x -> System.out.print(x + " "));

    }
}