package ru.golovin.springalgrang.algorithm;

import java.util.ArrayList;
import java.util.List;

public class AndOrTree {
    AndNode root;

    public AndOrTree() {
        this.root = null;
    }

    public OrNode getOrNodeByIndex(int index) {
        AndNode temp = root;
        for (int i = 0; i < index; i++) {
            temp = temp.right;
        }
        return temp.left;
    }

    public void create(List<List<String>> tree) {
        AndNode tempRoot = null;
        for (List<String> branch : tree) {
            if (root == null) {
                root = new AndNode();
                tempRoot = root;
            } else {
                tempRoot.right = new AndNode();
                tempRoot = tempRoot.right;
            }
            tempRoot.left = new OrNode();
            int order = 0;
            for (String value : branch) {
                tempRoot.left.children.add(new Node(order++, value));
            }
        }
        tempRoot.right = new AndNode();
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
    String value;
    int order;

    public Node(int order, String value) {
        this.value = value;
        this.order = order;
    }

    @Override
    public String toString() {
        return "{" +
                "value=" + value +
                ", order=" + order +
                '}';
    }
}
