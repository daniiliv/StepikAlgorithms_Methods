/**
 * Задача о покрытии отрезков точками.
 *
 * Дано N отрезков на прямой. Требуется покрыть их наименьшим числом точек.
 * Нужно найти наименьшее множество точек такое, что каждому отрезку
 * принадлежит хотя бы одна точка.
 */
import java.util.*;

class Point implements Comparable{
    int numInterval;
    int coord;
    char side;

    Point(int numInterval, int coord, char side) {
        this.numInterval = numInterval;
        this.coord = coord;
        this.side = side;
    }

    @Override
    public int compareTo(Object o) {
        Point point = (Point)o;
        int res = Integer.compare(this.coord, point.coord);
        if (res == 0) {
            return side == 'R' ? 1 : -1;
        }
        return res;
    }
}

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //Количество отрезков
        int n = scanner.nextInt();

        int [][] intervals = new int[n][2];
        List<Point> pointList = new ArrayList<>();
        List<Integer> stack = new ArrayList<>();
        List<Integer> result = new ArrayList<>();

        //Получаем края отрезков и записываем в массив
        for (int i = 0; i < n; i++) {
            int left = scanner.nextInt();
            int right = scanner.nextInt();
            intervals[i] = new int[2];
            intervals[i][0] = left;
            intervals[i][1] = right;
        }

        //Заполняем массив точек
        for (int i = 0; i < n; i++) {
            pointList.add(new Point(i + 1, intervals[i][0], 'L'));
            pointList.add(new Point(i + 1, intervals[i][1], 'R'));
        }

        //Сортируем массив точек по возрастанию координат
        pointList.sort(Comparator.naturalOrder());

        /**
         * Алгоритм:
         *
         * Идём по массиву отсортированных точек. Если текущая точка является левым
         * концом отрезка, то добавляем номер отрезка в стэк. Иначе, если это правый
         * конец отрезка, лежащего в стэке, иными словами это отрезок не покрыт, то
         * добавляем точку в результирующий список и очищаем стэк непокрытых отрезков.
         */
        for (Point point : pointList) {
            if (point.side == 'L')
                stack.add(point.numInterval);
            else if (stack.contains(point.numInterval)) {
                result.add(point.coord);
                stack.clear();
            }
        }
        System.out.println(result.size());
        result.forEach(x -> System.out.print(x + " "));
    }
}