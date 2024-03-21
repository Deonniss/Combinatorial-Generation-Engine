package ru.golovin.springalgrang.algorithm;

public class Root {
    private NodeType type;
    private int andId;
    private int orId;

    public Root(NodeType type, int andId, int orId) {
        this.type = type;
        this.andId = andId;
        this.orId = orId;
    }

    public NodeType getType() {
        return type;
    }

    public int getAndId() {
        return andId;
    }

    public int getOrId() {
        return orId;
    }
}
