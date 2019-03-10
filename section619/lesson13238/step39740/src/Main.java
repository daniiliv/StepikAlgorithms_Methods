/**
 * Задача о непрерывном рюкзаке.
 *
 * Выведите максимальную стоимость частей предметов (от каждого предмета можно
 * отделить любую часть, стоимость и объём при этом пропорционально уменьшатся),
 * помещающихся в данный рюкзак, с точностью не менее трёх знаков после запятой.
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //Logger logger = Logger.getLogger(Main.class.getName());

        //количество предметов
        int n = scanner.nextInt();
        //logger.warning("n: " + n);

        //вместимость рюкзака
        int w = scanner.nextInt();
        //logger.warning("\nw: " + w);

        //массив предметов
        Item[] items = new Item[n];

        //читаем из консоли стоимость и объём предмета и записываем в Item
        for (int i = 0; i < n; i++) {
            int cost = scanner.nextInt();
            int volume = scanner.nextInt();

            items[i] = new Item(cost, volume);
            //logger.warning("cost: " + cost + "| volume: " + volume + "\n");
        }

        Comparator<Item> valuePerVolumeComparator =
                (o1, o2) -> (int) (o2.getCostPerVolume() - o1.getCostPerVolume());

        //сортируем предметы в порядке убывания цены за единицу объема
        Arrays.sort(items, valuePerVolumeComparator);

        double valueStolen = 0d;
        int i = 0;

        while (i < n) {
            //пока рюкзак не пуст и предметы не закончились
            while (w != 0 && i < n) {
                valueStolen += items[i].getCostPerVolume();
                items[i].steal();
                w--;
                //если опустошили предмет, переход к следующему предмету
                if (items[i].isEmpty()) {
                    i++;
                }
            }
            i++;
        }
        System.out.printf("%.3f", valueStolen);
    }
}

class Item implements Comparable {
    private int cost;
    private int volume;
    private int amountStolen = 0;

    Item(int cost, int volume) {
        this.cost = cost;
        this.volume = volume;
    }

    boolean isEmpty() {
        return amountStolen == volume;
    }

    void steal() {
        amountStolen++;
    }

    double getCostPerVolume() {
        return (double) cost / volume;
    }

    public int compareTo(Object o) {
        Item item = (Item) o;
        return Double.compare(item.getCostPerVolume(), this.getCostPerVolume());
    }
}