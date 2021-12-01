import java.util.Comparator;

public class Node {
    public String value;
    public double fData;
    public Node parent;
    public Node leftChild;
    public Node rightChild;



    public Node(String value, double fData, Node parent, Node leftChild, Node rightChild) {
        this.value = value;
        this.fData = fData;
        this.parent = parent;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }
    public Node(String value, double fData, Node leftChild, Node rightChild) {
        this.value = value;
        this.fData = fData;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }
    public Node(String value, double fData, Node parent) {
        this.value = value;
        this.fData = fData;
        this.parent = parent;
    }

    public Node(String value, double fData){
        this.value = value;
        this.fData = fData;
    }

    public Node(double fData){
        this.fData = fData;
    }

    public Node(){

    }



}
