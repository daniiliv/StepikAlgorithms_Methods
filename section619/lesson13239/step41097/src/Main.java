import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    class Node implements Comparable<Node> {
        //Частота появления символа / сумма частот.
        final int sum;

        //Закодированный символ.
        String code;

        void buildCode(String code) {
            this.code = code;
        }

        Node(int sum) {
            this.sum = sum;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(sum, o.sum);
        }
    }

    //Внутренний узел.
    class InternalNode extends Node {

        //Левый и правый потомки.
        Node left;
        Node right;

        //Рекурсивный сбор ребёр.
        @Override
        void buildCode(String code) {
            super.buildCode(code);
            left.buildCode(code + "0");
            right.buildCode(code + "1");
        }

        InternalNode(Node left, Node right) {
            //Родителю - сумму правого и левого потомка.
            super(left.sum + right.sum);

            this.left = left;
            this.right = right;
        }
    }

    //Листовой узел.
    class LeafNode extends Node {
        char symbol; //символ

        //Строим код Хаффмана для данного символа.
        @Override
        void buildCode(String code) {
            super.buildCode(code);
            System.out.println(symbol + ": " + code);
        }

        /**
         * @param symbol Буква.
         * @param count Частота встречаемости.
         */
        private LeafNode(char symbol, int count) {
            super(count);
            this.symbol = symbol;
        }
    }

    //Запуск кодирования.
    private void run() {

        //Считывание со стандартного потока ввода
        Scanner input = new Scanner(System.in);

        //Запишем в строку прочитанную последовательность.
        String initialString = input.next();

        //Составим мапу с частотой встречаемости каждого символа.
        Map<Character, Integer> count = new HashMap<>();
        for (int i = 0; i < initialString.length(); i++) {
            char c = initialString.charAt(i);
            if (count.containsKey(c)) {
                count.put(c, count.get(c) + 1);
            } else {
                count.put(c, 1);
            }
        }

        //Буквы и соответствующие им листовые узлы.
        Map<Character, Node> charNodes = new HashMap<>();


        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();

        //Добавили в приоритетную очередь каждый листовой узел.
        for (Map.Entry<Character, Integer> entry : count.entrySet()) {
            LeafNode node = (new LeafNode(entry.getKey(), entry.getValue()));
            charNodes.put(entry.getKey(), node);
            priorityQueue.add(node);
        }

        //Длина закодированного сообщения. (Так и не понял, как это работает)
        int sum = 0;

        //Составим двоичное дерево Хаффмана.
        //Берем два элемента с наименьшей частотой - делаем их потомками нового узла.
        //Кладём новый узел обратно в приоритетную очередь.
        while (priorityQueue.size() > 1) {
            Node first = priorityQueue.poll();
            Node second = priorityQueue.poll();
            InternalNode node = new InternalNode(first, second);
            sum += node.sum;
            priorityQueue.add(node);
        }

        //Частный случай: если на вход строка из одной буквы.
        if (count.size() == 1) {
            sum = initialString.length();
        }

        //Число уникальный символов, длина закодированного сообщения.
        System.out.println(count.size() + "  " + sum);

        //Делаем обход дерева, начиная с корня дерева. (К левому, правому потомку и т.д.)
        Node root = priorityQueue.poll();

        //Частный случай: если на вход строка из одной буквы.
        //Присвоим этому единственному символу код 0.
        if (count.size() == 1) {
            root.buildCode("0");

        } else {
            root.buildCode("");
        }

        //Посимвольно считываем строку. Добавляем в результирующую строку код,
        //соответствующий считанному символу.
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < initialString.length(); i++) {
            char c = initialString.charAt(i);
            result.append(charNodes.get(c).code);
        }

        //Закодированная последовательность.
        System.out.println(result.toString());
    }

    public static void main(String[] args) {
        new Main().run();
    }
}