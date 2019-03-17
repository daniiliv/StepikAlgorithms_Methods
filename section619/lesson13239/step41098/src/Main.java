import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


/**
 * Декодирование Хаффмана.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int charAmount = scanner.nextInt();
        int codedMessageLength = scanner.nextInt();
        scanner.nextLine();

        Map<String, Character> codedChars = new HashMap<>();
        String currentChar;
        String currentCharCode;

        //Храним в мапе код (является ключом) и символ, соответствующий этому коду.
        for (int i = 0; i < charAmount; i++) {
            currentChar = scanner.next();
            currentCharCode = scanner.next();
            codedChars.put(currentCharCode, currentChar.charAt(0));
        }

        scanner.nextLine();

        //Переданная в поток ввода последовательность.
        String codedMessage = scanner.nextLine();

        StringBuilder currentBit = new StringBuilder();
        StringBuilder resultString = new StringBuilder();

        //Побитовое считывание.
        for (int i = 0; i < codedMessageLength; i++) {
            currentBit.append(codedMessage.charAt(i));

            //Как только нашли код в мапе, добавляем в результирующую строку символ, соответствующий этому коду.
            if (codedChars.containsKey(currentBit.toString())) {
                resultString.append(codedChars.get(currentBit.toString()));
                currentBit = new StringBuilder(); //Чистим стрингбилдер для записи следующих битов.
            }
        }

        System.out.println(resultString.toString());
    }
}
