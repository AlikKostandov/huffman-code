package entity;

/**
 * Binary tree for fill codeTable.
 */
public class HuffmanTree implements Comparable<HuffmanTree> {

    public Node root;

    public double frequency;

    public HuffmanTree(Node root) {
        this.root = root;
        frequency = root.probability;
    }

    @Override
    public int compareTo(HuffmanTree tree) {
        return Double.compare(this.frequency, tree.frequency);
    }
}
