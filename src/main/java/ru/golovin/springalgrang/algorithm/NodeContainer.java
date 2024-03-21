package ru.golovin.springalgrang.algorithm;

import java.math.BigInteger;

public class NodeContainer {

    private BigInteger rank;
    private Root root;

    public NodeContainer(BigInteger rank, Root root) {
        this.rank = rank;
        this.root = root;
    }

    public BigInteger getRank() {
        return rank;
    }

    public Root getRoot() {
        return root;
    }

    public void setRank(BigInteger rank) {
        this.rank = rank;
    }

    public void setRoot(Root root) {
        this.root = root;
    }
}
