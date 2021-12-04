public class HuffmanTree implements Comparable<HuffmanTree>{
    public Node root;
    public double frequency;


    public HuffmanTree(Node root) {
        this.root = root;
        frequency = root.fData;
    }


    @Override
    public String toString() {
        return "HuffmanTree{" +
                "frequancy=" + frequency +
                '}';
    }

    @Override
    public int compareTo(HuffmanTree tree) {
        return Double.compare(this.frequency, tree.frequency);
    }



}
