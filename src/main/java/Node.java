public class Node {
    public String value;
    public double fData;
    public Node parent;
    public Node leftChild;
    public Node rightChild;


    public Node(String value, double fData){
        this.value = value;
        this.fData = fData;
    }

    public Node(double fData){
        this.fData = fData;
    }


}
