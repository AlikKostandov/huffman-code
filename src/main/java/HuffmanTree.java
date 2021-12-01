import java.util.Comparator;

public class HuffmanTree implements Comparable<HuffmanTree>{
    public Node root;
    public double frequancy;


    public HuffmanTree(Node root) {
        this.root = root;
        frequancy = root.fData;
    }

    public HuffmanTree() {

    }

    public void inOrder(Node localRoot) {
        if (localRoot != null) {
            inOrder(localRoot.leftChild);
            System.out.println(localRoot.fData + " ");
            inOrder(localRoot.rightChild);
        }
    }

    @Override
    public String toString() {
        return "HuffmanTree{" +
                "frequancy=" + frequancy +
                '}';
    }

    @Override
    public int compareTo(HuffmanTree tree) {
        return Double.compare(this.frequancy, tree.frequancy);
    }

    public Double getFrequancy() {
        return frequancy;
    }

    public int compare(HuffmanTree x, HuffmanTree y)
    {
        if (x.root.fData < y.root.fData)
        {
            return -1;
        }
        if (x.root.fData > y.root.fData)
        {
            return 1;
        }
        return 0;
    }


}
