package entity;

/**
 * Node of binary tree.
 */
public class Node {

    public Character symbol;

    public double probability;

    public Node parent;

    public Node leftChild;

    public Node rightChild;

    public Node(Builder builder) {
        this.symbol = builder.symbol;
        this.probability = builder.probability;
        this.parent = builder.parent;
        this.leftChild = builder.leftChild;
        this.rightChild = builder.rightChild;
    }

    public static class Builder {

        private Character symbol;

        private double probability;

        private Node parent;

        private Node leftChild;

        private Node rightChild;

        public Builder symbol(Character symbol) {
            this.symbol = symbol;
            return this;
        }

        public Builder probability(double probability) {
            this.probability = probability;
            return this;
        }

        public Builder parent(Node parent) {
            this.parent = parent;
            return this;
        }

        public Builder leftChild(Node leftChild) {
            this.leftChild = leftChild;
            return this;
        }

        public Builder rightChild(Node rightChild) {
            this.rightChild = rightChild;
            return this;
        }

        public Node build() {
            return new Node(this);
        }
    }

}