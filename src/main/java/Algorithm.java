
import javax.swing.*;
import java.util.*;
import java.util.stream.Collectors;

public class Algorithm {


    public static void main(String[] args) {
        String text = thisMyText("C:\\Program Files\\Holmes.txt");
        messageEncoding(text);

    }

    public static String getCorrectText(String text){
        ArrayList<String> symbolList = new ArrayList<String>(Arrays.asList(text.split("")));
        ArrayList<String> sign = new ArrayList<String>(Arrays.asList(",", ".", "-", "(", ")", "—", "\"", "\'"));
        symbolList.removeAll(sign);
        return symbolList.stream().collect(Collectors.joining());
    }
    public static void messageEncoding(String text){
        Alphabet alphabet = getMyAlpha(text);
        calculateProbability(alphabet, text);
        HuffmanTree tree = buildATree(alphabet);
        Map<String, String> map = buildCodeTable(tree);
        System.out.println(map);
        System.out.println(encoding(map,text));
    }


    public static String thisMyText(String fileName) {
        ReadFile reader = new ReadFile();
        String text = reader.readTxtFile(fileName).toLowerCase(Locale.ROOT);
        return text;
    }


    public static Alphabet getMyAlpha(String text) {
        Alphabet myAplpha = new Alphabet(new ArrayList<Element>());
        text = getCorrectText(text);
        ArrayList<String> wordList = new ArrayList<String>(Arrays.asList(text.split(" ")));
        Set<String> uniqueValues = new HashSet<>(wordList);
        for (String word : uniqueValues) {
            int count = Collections.frequency(wordList, word);
            Element el = new Element(word, count);
            myAplpha.symbols.add(el);
        }
//        Collections.sort(myAplpha.symbols, new Alphabet.SortByCount());
        return myAplpha;
    }

    public static void calculateProbability(Alphabet alpha, String text) {
        for (Element el : alpha.symbols) {
            el.frequency = el.count * 1.0 / text.length();
        }
    }


    public static HuffmanTree buildATree(Alphabet myAlpha) {
        Queue<HuffmanTree> trees = new PriorityQueue<HuffmanTree>();
        for (Element futureNode : myAlpha.symbols) {
            Node node = new Node(futureNode.value, futureNode.frequency);
            HuffmanTree tree = new HuffmanTree(node);
            trees.add(tree);
        }
        while (trees.size() > 1) {
            createParentNode(trees);
        }
        return trees.peek();
    }


    public static void createParentNode(Queue<HuffmanTree> trees) {
        Node firstInQue = trees.poll().root;
        Node secondInQue = trees.poll().root;
        Node newNode = new Node(firstInQue.fData + secondInQue.fData);
        newNode.leftChild = firstInQue;
        newNode.rightChild = secondInQue;
        firstInQue.parent = newNode;
        secondInQue.parent = newNode;
        HuffmanTree newTree = new HuffmanTree(newNode);
        trees.add(newTree);
    }

    public static Map<String, String> buildCodeTable(HuffmanTree tree) {
        Map<String, String> table = new HashMap<>();
        while ((tree.root.leftChild != null) || (tree.root.rightChild != null)) {
            String code = "";
            String[] pair = searchLeafNode(tree.root, code);
            table.put(pair[0], pair[1]);
        }
        table.remove(null);
        return table;
    }


    public static String[] searchLeafNode(Node node, String code) {
        Node currentNode = node;
        if (currentNode.leftChild != null) {
            code = code + "0";
            return searchLeafNode(currentNode.leftChild, code);
        } else if (currentNode.rightChild != null) {
            code = code + "1";
            return searchLeafNode(currentNode.rightChild, code);
        }
        else if (currentNode.value != null) {
            Node parent = currentNode.parent;
            String lastInput = code.split("")[code.length() - 1];
            if (lastInput.equals("1")) {
                parent.rightChild = null;

            } else {
                parent.leftChild = null;
            }
        }
        if((currentNode.leftChild == null) && (currentNode.rightChild == null) && (currentNode.value == null)){
            Node parent = currentNode.parent;
            if(code.length() > 0){
            String lastInput = code.split("")[code.length() - 1];
            if (lastInput.equals("1")) {
                parent.rightChild = null;
                if(code.length() > 0){
                code = code.substring(0, code.length() - 1);}
                return searchLeafNode(parent, code);

            } else if(lastInput.equals("0")) {
                parent.leftChild = null;;
                if(code.length() > 0){
                code = code.substring(0, code.length() - 1);}
                return searchLeafNode(parent, code);
            }}
        }
        String value = currentNode.value;
        String[] pair = {value, code};
        return pair;
    }


    public static String encoding(Map<String, String > binaryForm, String text){
        text = getCorrectText(text);
        ArrayList<String> encoder = new ArrayList<>(Arrays.asList(text.split(" ")));
        String encodingMessage = encoder.stream().map(o1 -> binaryForm.get(o1)).collect(Collectors.joining());
        return encodingMessage;
    }

}