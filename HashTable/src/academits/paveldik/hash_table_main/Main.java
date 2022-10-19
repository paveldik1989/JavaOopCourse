package academits.paveldik.hash_table_main;

import academits.paveldik.hash_table.HashTable;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        HashTable<Number> hashTable = new HashTable<>(10, Arrays.asList(1, 101, 2, null, 3, 4, 7, 3, 3));
        System.out.println("Созданная таблица: " + hashTable);

        System.out.println("Кол-во элементов в таблице: " + hashTable.size());

        hashTable.add(90000);
        System.out.println("После добавления элемента: " + hashTable);

        Double[] numbers = {1.1, 101.1, 2.1, 202.1, 3.1, 4.1, 7.1};
        hashTable.addAll(Arrays.asList(numbers));
        System.out.println("После добавления коллекции: " + hashTable);

        System.out.println("Содержит ли коллекция все элементы другой коллекции: " + hashTable.containsAll(Arrays.asList(numbers)));

        hashTable.removeAll(Arrays.asList(numbers));
        System.out.println("После удаления всех элементов содержащихся в другой коллекции: " + hashTable);

        System.out.println("Содержит ли коллекция данный элемент: " + hashTable.contains(100500));

        hashTable.retainAll(Arrays.asList(1, 2, 3, 3));
        System.out.println("Оставить только элементы содержащиеся в переданной коллекции: " + hashTable);

        hashTable.clear();
        System.out.println("После очистки таблицы: " + hashTable);
    }
}