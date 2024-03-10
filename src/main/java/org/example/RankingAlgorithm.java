package org.example;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class RankingAlgorithm {

    public static BigInteger rank(Root root, List<Integer> v, AndOrTree tree) {
        if (root.getType() == NodeType.NONE) {
            return BigInteger.ZERO;
        } else if (root.getType() == NodeType.AND) {
            OrNode orNode = tree.getOrNodeByIndex(root.getAndId());
            if (orNode == null) {
                return BigInteger.ZERO;
            }
            BigInteger l = rank(new Root(NodeType.AND, root.getAndId() + 1, 0), v, tree);
            return rank(new Root(NodeType.OR, root.getAndId(), root.getOrId()), v, tree).add(
                    l.multiply(new BigInteger(String.valueOf(tree.getOrNodeByIndex(root.getAndId()).children.size()))));
        } else {
            BigInteger k = new BigInteger(String.valueOf(v.get(root.getAndId())));
            BigInteger l = rank(new Root(NodeType.NONE, root.getAndId(), root.getOrId()), v, tree);
            return l.add(k);
        }
    }

    public static List<Integer> unRank(BigInteger r, AndOrTree tree) {
        Stack<NodeContainer> stack = new Stack<>();
        stack.add(new NodeContainer(r, new Root(NodeType.AND, 0, 0)));
        List<Integer> v = new ArrayList<>();
        while (!stack.empty()) {
            NodeContainer node = stack.pop();
            BigInteger l = node.getRank();
            if (node.getRoot().getType() == NodeType.AND) {
                if (tree.getOrNodeByIndex(node.getRoot().getAndId()) != null) {
                    for (int i = 0; i < 2; i++) {
                        OrNode orNode;
                        BigInteger l1;
                        NodeContainer newNode;
                        if (i == 0) {
                            orNode = tree.getOrNodeByIndex(node.getRoot().getOrId());
                            l1 = l.mod(BigInteger.valueOf(orNode.children.size()));
                            newNode = new NodeContainer(l1, new Root(NodeType.OR,
                                    node.getRoot().getAndId(), node.getRoot().getOrId()));
                            stack.add(newNode);
                            l = l.divide(BigInteger.valueOf(orNode.children.size()));
                        } else {

                            int id = node.getRoot().getAndId();
                            OrNode node1 = tree.getOrNodeByIndex(++id);
                            BigInteger count = BigInteger.ONE;
                            while (node1 != null) {
                                count = count.multiply(BigInteger.valueOf(node1.children.size()));
                                node1 = tree.getOrNodeByIndex(++id);
                            }
                            l1 = l.mod(count);
                            newNode = new NodeContainer(l1, new Root(NodeType.AND,
                                    node.getRoot().getAndId() + 1, node.getRoot().getOrId()));
                            stack.add(newNode);
                            l = l.divide(count); //кол-во листов в правой ветке
                        }
                    }
                }
            } else if (node.getRoot().getType() == NodeType.OR) {
                int n = tree.getOrNodeByIndex(node.getRoot().getAndId()).children.size();
                BigInteger sum = BigInteger.ZERO;

                for (int i = 0; i < n; i++) {
                    if (sum.add( BigInteger.ONE).compareTo(l) > 0) {
                        v.add(tree.getOrNodeByIndex(node.getRoot().getAndId()).children.get(i).order);
                        break;
                    }
                    sum = sum.add(BigInteger.ONE);
                }
            }
        }
        return v.reversed();
    }
}

/*
z - корень дерева (узел)
u - вариант
D - дерево
 */
