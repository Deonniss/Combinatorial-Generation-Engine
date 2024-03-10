package org.example;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    private static List<Integer> genList(int size, int size2) {
        Random random = new Random();

        List<Integer> list = new ArrayList<>();

        for (int j = 0; j < size; j++) {
            list.add(random.nextInt(size2));
        }
        return list;
    }

    public static void main(String[] args) {

//        simpleTest();

        System.out.println("\n\n\n");

        hardTest();

    }

    private static void simpleTest() {
        AndOrTree tree = new AndOrTree();
        List<List<Integer>> lists = new ArrayList<>();
        lists.add(List.of(1, 2, 3, 4, 5));
        lists.add(List.of(1, 2));
        lists.add(List.of(1));
        tree.create(lists);
        System.out.println(tree);
        List<Integer> list = List.of(1, 1, 0);
        System.out.println(list);
        Root root = new Root(NodeType.AND, 0, 0);
        BigInteger rank = RankingAlgorithm.rank(root, list, tree);
        System.out.println(rank);
        System.out.println(RankingAlgorithm.unRank(rank, tree));
    }

    private static void hardTest() {
        int size = 1_000;
        AndOrTree tree = new AndOrTree();
        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 0; i < 1_000; i++) {
            lists.add(genList(size, size));
        }
        tree.create(lists);
//        System.out.println(tree);
//        List<Integer> list = genList(size);
        List<Integer> list = genList(1_000, 1_000);
        System.out.println(list);
        Root root = new Root(NodeType.AND, 0, 0);


        long start1 = System.currentTimeMillis();
        BigInteger rank = RankingAlgorithm.rank(root, list, tree);
        System.out.println("ранг - " + (System.currentTimeMillis() - start1));
        System.out.println(rank);

        long start2 = System.currentTimeMillis();
        System.out.println(RankingAlgorithm.unRank(rank, tree));
        System.out.println("unранг - " + (System.currentTimeMillis() - start2));

    }

}


/*
z - корень дерева (узел)

u - вариант

D - дерево

 */