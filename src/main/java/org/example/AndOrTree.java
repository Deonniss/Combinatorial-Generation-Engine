package org.example;

import java.util.ArrayList;
import java.util.List;

public class AndOrTree {
    AndNode root;

    public AndOrTree() {
        this.root = null;
    }

    public void create(List<List<Integer>> tree) {
        AndNode tempRoot = null;
        for (List<Integer> branch : tree) {
            if (root == null) {
                root = new AndNode();
                tempRoot = root;
            } else {
                tempRoot.right = new AndNode();
                tempRoot = tempRoot.right;
            }
            tempRoot.left = insertOR();
            for (Integer value : branch) {
                tempRoot.left.children.add(insertNONE(value));
            }
        }
    }

    private Node insertNONE(int value) {
        return new Node(value);
    }

    private OrNode insertOR() {
        return new OrNode();
    }

    @Override
    public String toString() {
        return "Tree{" +
                "root=" + root +
                '}';
    }
}

class AndNode {
    OrNode left;
    AndNode right;

    @Override
    public String toString() {
        return "AND-{" +
                "left=" + left +
                ", right=" + right +
                '}';
    }
}

class OrNode {
    List<Node> children;

    public OrNode() {
        this.children = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "OR-{" +
                "children=" + children +
                '}';
    }
}

class Node {
    int value;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
