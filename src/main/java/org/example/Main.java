package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    private static List<Integer> genList(int size) {
        Random random = new Random();

        List<Integer> list = new ArrayList<>();

        for (int j = 0; j < size; j++) {
            list.add(random.nextInt(100));
        }
        return list;
    }

    public static void main(String[] args) {

        AndOrTree tree = new AndOrTree();

        List<List<Integer>> lists = new ArrayList<>();
        lists.add(genList(100));
        lists.add(genList(100));
        lists.add(genList(100));
        lists.add(genList(100));
        lists.add(genList(100));
        lists.add(genList(100));
        lists.add(genList(100));
        lists.add(genList(100));
        lists.add(genList(100));
        lists.add(genList(100));
        lists.add(genList(100));
        lists.add(genList(100));
        lists.add(genList(100));
        lists.add(genList(100));
        lists.add(genList(100));
        lists.add(genList(100));
        lists.add(genList(100));
        lists.add(genList(100));
        lists.add(genList(100));
        lists.add(genList(100));
        tree.create(lists);

        System.out.println(tree);


        List<Integer> list = genList(20);

        Root root = new Root(NodeType.AND, 0, 0);

        long start = 0;
        start = System.currentTimeMillis();
        System.out.println(RankingAlgorithm.rank(root, list, tree));
        System.out.println(System.currentTimeMillis() - start);

//        start = System.nanoTime();
//        System.out.println(RankingAlgorithm.rank(root, List.of(1, 0, 0), tree));
//        System.out.println(System.nanoTime() - start);
//
//        start = System.nanoTime();
//        System.out.println(RankingAlgorithm.rank(root, List.of(2, 0, 0), tree));
//        System.out.println(System.nanoTime() - start);
//
//        start = System.nanoTime();
//        System.out.println(RankingAlgorithm.rank(root, List.of(0, 1, 0), tree));
//        System.out.println(System.nanoTime() - start);
//
//        start = System.nanoTime();
//        System.out.println(RankingAlgorithm.rank(root, List.of(1, 1, 0), tree));
//        System.out.println(System.nanoTime() - start);
//
//        start = System.nanoTime();
//        System.out.println(RankingAlgorithm.rank(root, List.of(2, 1, 0), tree));
//        System.out.println(System.nanoTime() - start);

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