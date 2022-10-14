package ru.academits.paveldik.tree_main;

import ru.academits.paveldik.tree.Tree;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Tree<Integer> tree1 = new Tree<>();
        tree1.add(500);
        System.out.println(tree1);

        ArrayList<Integer> arrayList = new ArrayList<>(Arrays.asList(100, 50, 200, 150, 250, 225, 275, 215, 220));
        Tree<Integer> tree2 = new Tree<>(arrayList);

        System.out.println("Содержит элемент: " + tree2.contains(10));
        System.out.println(tree2);
        System.out.println("Размер: " + tree2.size());

        for (Integer i : arrayList) {
            tree2.remove(i);
            System.out.println("Удаление значения " + i + ": " + tree2 + " Размер: " + tree2.size());

            tree2.add(i);
            System.out.println("Добавление значения " + i + ": " + tree2 + " Размер: " + tree2.size());
            System.out.println();

            tree2 = new Tree<>(arrayList);
        }

        System.out.print("Обход в ширину: ");
        tree2.traverseBreadthFirst(integer -> System.out.print(integer + ", "));
        System.out.println();

        System.out.print("Обход в глубину рекурсией: ");
        tree2.traverseDepthFirstByRecursion(integer -> System.out.print(integer + ", "));
        System.out.println();

        System.out.print("Обход в глубину без рекурсии: ");
        tree2.traverseDepthFirst(integer -> System.out.print(integer + ", "));
        System.out.println();
    }
}