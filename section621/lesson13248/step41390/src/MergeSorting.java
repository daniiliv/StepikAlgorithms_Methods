class MergeSorting {

    //Число инверсий.
    private static int numberOfInversions = 0;

    static int getNumberOfInversions() {
        return numberOfInversions;
    }

    //Сортировка слиянием.
    void mergeSort(int[] array, int left, int right) {
        if (left < right) {

            //Рекурсивно разбиваем массив на подмассивы (вплодь до подмассивов из одного элемента).
            int middle = (left + right) / 2;
            mergeSort(array, left, middle);
            mergeSort(array, middle + 1, right);

            //Производим процедуру слияния (Начиная с подмассивов единичной длины и далее).
            merge(array, left, middle, right);
        }
    }


    //Слияние.
    private void merge(int[] array, int left, int middle, int right) {

        //Длина левого и правого подмассива соответственно.
        int leftLen = middle - left + 1;
        int rightLen = right - middle;

        //Подмассивы, элементы которых потом сравниваем между собой.
        //Т.е. временный буфер, все изменения происходят в исходном массиве.
        //Здесь лишь хранятся левые и правые подмассивы.
        int[] leftArray = new int[leftLen];
        int[] rightArray = new int[rightLen];

        //Заполняем буферный подмассив значениями исходного массива.
        //Значения уже упорядочены нашим же алгоритмом, который рекурсивно отработал.
        //Т.е. подмассивы длиной 1 сливаются в упорядоченный подмассив длиной 2, затем 2 в 4, и т.д.
        for (int i = 0; i < leftLen; i++) {
            leftArray[i] = array[left + i];
        }
        for (int i = 0; i < rightLen; i++) {
            rightArray[i] = array[middle + 1 + i];
        }

        //Индексы буферов и индекс исходного массива.
        int i = 0;
        int j = 0;
        int k = left;

        while (i < leftLen && j < rightLen) {

            //Подмассив упорядочен, перестановок нет.
            if (leftArray[i] <= rightArray[j]) {
                array[k] = leftArray[i++];

            } else {
                //Упорядоченности не было, произошла перестановка.
                //К общему числу инверсий добавялем количество элементов,
                //которые остались в левом подмассиве.
                array[k] = rightArray[j++];
                numberOfInversions += leftLen - i;
            }
            k++;
        }

        //Заполняем результирующий массив остатками левого подмассива, когда в правом закончились элементы.
        while (i < leftLen) {
            array[k++] = leftArray[i++];
        }

        while (j < rightLen) {
            array[k++] = rightArray[j++];
        }
    }
}
