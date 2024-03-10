package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        AndOrTree tree = new AndOrTree();

        List<List<Integer>> lists = new ArrayList<>();
        lists.add(List.of(1, 2));
        lists.add(List.of(1, 2, 3));
        lists.add(List.of(1));

        tree.create(lists);

        System.out.println(tree);

    }


    private static int rank() {
        return 0;
    }
}


/*
z - корень дерева (узел)

u - вариант

D - дерево

 */