package org.example;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

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

    private static List<Integer> genList(int elementCount, int maxValue) {
        Random random = new Random();

        List<Integer> list = new ArrayList<>();

        for (int j = 0; j < elementCount; j++) {
            list.add(random.nextInt(maxValue));
        }
        return list;
    }

    private static void hardTest() {
        int elementCount = 20;
        int maxValue = 7;
        int listCount = 4;
        AndOrTree tree = new AndOrTree();
        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 0; i < listCount; i++) {
            lists.add(genList(elementCount, maxValue));
        }
        tree.create(lists);
        System.out.println("дерево - " + tree);
        List<Integer> list = genList(listCount, elementCount);
        System.out.println("вариант для кодирования - " + list);
        Root root = new Root(NodeType.AND, 0, 0);

        long start1 = System.currentTimeMillis();
        System.out.println("максимальный ранг - " + 4 * 20);
        BigInteger rank = RankingAlgorithm.rank(root, list, tree);
        System.out.println("время на ранг - " + (System.currentTimeMillis() - start1));
        System.out.println("ранг код - " + rank);

        long start2 = System.currentTimeMillis();
        System.out.println("unранг код - " + RankingAlgorithm.unRank(rank, tree));
        System.out.println("время на unранг - " + (System.currentTimeMillis() - start2));

    }

}


/*
z - корень дерева (узел)

u - вариант

D - дерево

 */