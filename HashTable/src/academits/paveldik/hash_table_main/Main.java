package academits.paveldik.hash_table_main;

import academits.paveldik.hash_table.HashTable;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Double[] numbers2 = {1.1, 101.1, 2.1, 202.1, 3.1, 4.1, 7.1};

        HashTable<Number> hashTable1 = new HashTable<>(10, Arrays.asList(1, 101, 2, 202, 3, 4, 7, 3, 3));
        System.out.println("Созданная таблица: " + hashTable1);

        System.out.println("Кол-во элементов в таблице: " + hashTable1.size());

        hashTable1.add(90000);
        System.out.println("После добавления элемента: " + hashTable1);

        hashTable1.addAll(Arrays.asList(numbers2));
        System.out.println("После добавления коллекции: " + hashTable1);

        System.out.println("Содержит ли коллекция все элементы другой коллекции: " + hashTable1.containsAll(Arrays.asList(numbers2)));

        hashTable1.removeAll(Arrays.asList(numbers2));
        System.out.println("После удаления всех элементов содержащихся в другой коллекции: " + hashTable1);

        System.out.println("Содержит ли коллекция данный элемент: " + hashTable1.contains(100500));

        hashTable1.retainAll(Arrays.asList(1, 2, 3, 3));
        System.out.println("Оставить только элементы содержащиеся в переданной коллекции: " + hashTable1);

        hashTable1.clear();
        System.out.println("После очистки таблицы: "+hashTable1);
    }
}
