
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Узел двоичной (макс-кучи).
 */
class HeapNode {
    private int iData;

    HeapNode(int key) {
        iData = key;
    }

    int getKey() {
        return iData;
    }

    void setKey(int id) {
        iData = id;
    }
}

/**
 * Двоичная (макс-)куча.
 */
class Heap {

    //Массив для хранения узлов кучи (узлов двоичного дерева).
    private HeapNode[] heapArray;

    private int maxSize;
    private int currentSize;

    Heap(int maxSize) {
        this.maxSize = maxSize;
        this.currentSize = 0;
        this.heapArray = new HeapNode[maxSize];
    }

    private boolean isEmpty() {
        return currentSize == 0;
    }

    //Вставка в приоритетную очередь.
    //Подвесим новый элемент листом в произвольное место.
    //(по последнему индексу, так как у нас полное двоичное дерево - узлы хранятся в ячейках массива)
    boolean insert(int key) {
        if (currentSize == maxSize)
            return false;
        HeapNode newNode = new HeapNode(key);
        heapArray[currentSize] = newNode;
        trickleUp(currentSize++);
        return true;
    }


    /**
     * Просеивание вверх.
     */
    private void trickleUp(int index) {
        int parent = (index - 1) / 2;
        HeapNode bottom = heapArray[index];

        // Пока ключ узла с переданным индексом больше ключа
        // его родителя -> этот узел всплывает вверх вплодь до корня дерева.
        while (index > 0 && heapArray[parent].getKey() < bottom.getKey()) {
            heapArray[index] = heapArray[parent];
            index = parent;
            parent = (parent - 1) / 2;
        }
        heapArray[index] = bottom;
    }


    /**
     * Изъять элемент (корень дерева).
     */
    HeapNode remove() {
        if (isEmpty()) {
            return null;
        }

        //Сохраним извлекаемый узел (корень) для его возврата методом.
        HeapNode root = heapArray[0];

        //Положим в корень последний лист дерева.
        heapArray[0] = heapArray[--currentSize];

        //Затем утопим положенный узел, чтобы восстановить свойство кучи.
        trickleDown(0);

        return root;
    }

    /**
     * Просеивание вниз.
     */
    private void trickleDown(int index) {
        int largerChild;
        HeapNode top = heapArray[index];

        //while node has at least one child
        while (index < currentSize / 2) {
            int leftChild = 2 * index + 1;
            int rightChild = leftChild + 1;

            //Проверка наличия правого ребёнка и значения его ключа.
            //Если ключ правого больше левого, то работаем с правым.
            //Иначе - с левым.
            //Сравниваем утопленника с правым или левым.
            //Если ключ утопленника оказался меньше, то топим вниз.
            if (rightChild < currentSize &&
                    heapArray[leftChild].getKey() <
                            heapArray[rightChild].getKey())
                largerChild = rightChild;
            else
                largerChild = leftChild;
            if (top.getKey() >= heapArray[largerChild].getKey())
                break;

            heapArray[index] = heapArray[largerChild];
            index = largerChild;
        }
        heapArray[index] = top;
    }

    /**
     * Метод изменения ключа узла.
     */
    public boolean change(int index, int newValue) {
        if (index < 0 || index >= currentSize)
            return false;
        int oldValue = heapArray[index].getKey();
        heapArray[index].setKey(newValue);

        //Просеивание вверх или вниз в зависимости от нового значения.
        if (oldValue < newValue)
            trickleUp(index);
        else
            trickleDown(index);
        return true;
    }

    public void displayHeap() {
        System.out.print("heapArray: ");
        for (int m = 0; m < currentSize; m++)
            if (heapArray != null)
                System.out.print(heapArray[m].getKey() + " ");
            else
                System.out.print("-- ");
        System.out.println();

        int nBlanks = 32;
        int itemsPerRow = 1;
        int column = 0;
        int j = 0;
        String dots = "............................";
        System.out.println(dots + dots);

        while (currentSize > 0) {
            if (column == 0)
                for (int k = 0; k < nBlanks; k++)
                    System.out.print(' ');
            System.out.print(heapArray[j].getKey());
            if (++j == currentSize)
                break;

            if (++column == itemsPerRow) {
                nBlanks /= 2;
                itemsPerRow *= 2;
                column = 0;
                System.out.println();
            } else
                for (int k = 0; k < nBlanks * 2 - 2; k++)
                    System.out.print(' ');
        }
        System.out.println("\n" + dots + dots);
    }
} //end class Heap

public class Main {
    public static void main(String[] args) throws IOException {
        /**
         * Sample Input 1:
         * 6
         * Insert 200
         * Insert 10
         * ExtractMax
         * Insert 5
         * Insert 500
         * ExtractMax
         *
         * Sample Output 1:
         * 200
         * 500
         */

        Heap theHeap = new Heap(99999);

        Scanner scanner = new Scanner(System.in);

        int operationsAmount = scanner.nextInt();

        //Здесь хранятся вводимые с консоли данные (инструкции для программы).
        String[] input;

        int value = 0;
        String operation = "";

        scanner.nextLine();

        for (int i = 0; i < operationsAmount; i++) {
            input = scanner.nextLine().split(" ");
            if (input.length != 0) {
                operation = input[0];
                if (input.length == 2) {
                    value = Integer.valueOf(input[1]);
                }
            }
            switch (operation) {
                case "Insert":
                    theHeap.insert(value);
                    break;
                case "ExtractMax":
                    HeapNode heap = theHeap.remove();
                    if (heap != null) {
                        System.out.println(heap.getKey());
                    }
                    break;
            }

        }


//------------------
        /*theHeap.insert(70);
        theHeap.insert(40);
        theHeap.insert(50);
        theHeap.insert(20);
        theHeap.insert(60);
        theHeap.insert(100);
        theHeap.insert(80);
        theHeap.insert(30);
        theHeap.insert(10);
        theHeap.insert(90);

        while (true) {
            System.out.print("Enter first letter of ");
            System.out.print("show, insert, remove, change: ");
            int choice = getChar();
            switch (choice) {
                case 's':
                    theHeap.displayHeap();
                    break;
                case 'i':
                    System.out.print("Enter value to insert: ");
                    value = getInt();
                    success = theHeap.insert(value);
                    if (!success)
                        System.out.println("Can't insert; heap full");
                    break;
                case 'r':
                    if (!theHeap.isEmpty())
                        theHeap.remove();
                    else
                        System.out.println("Can't remove; heap empty");
                    break;
                case 'c':
                    System.out.print("Enter current index of item: ");
                    value = getInt();
                    System.out.print("Enter new key: ");
                    value2 = getInt();
                    success = theHeap.change(value, value2);
                    if (!success)
                        System.out.println("Invalid index\n");
                    break;
                default:
                    System.out.println("Invalid entry!\n");
            } //end switch
        } //end while*/
    } //end main

    public static String getString() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        return s;
    }

    public static char getChar() throws IOException {
        String s = getString();
        return s.charAt(0);
    }

    public static int getInt() throws IOException {
        String s = getString();
        return Integer.parseInt(s);
    }
}