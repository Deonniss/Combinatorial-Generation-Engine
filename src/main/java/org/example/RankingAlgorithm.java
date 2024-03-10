package org.example;

import java.math.BigInteger;
import java.util.List;

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

//    public static int rank(List<Integer> v, AndOrTree tree) {
//        AtomicInteger rank = new AtomicInteger(0);
//        for (int i = 0; i < v.size(); i++) {
//            int order = v.get(i);
//            int w = i * tree.getOrNodeByIndex(i).children.size();
//            long count = tree.getOrNodeByIndex(i).children.stream().filter(c -> c.order < order).count();
//            rank.addAndGet((int) (w * count));
//        }
//        return rank.get();
//    }
}

/*
z - корень дерева (узел)
u - вариант
D - дерево
 */
