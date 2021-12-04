import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Algorithm {
    public static Map<String, String> mainAlphabet;
    public static String textForEncoding;
    public static String pathToNewFile;


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Укажи путь к файлу: ");
        String pathToFile = scan.nextLine();
        System.out.print("Укажи имя нового файла: ");
        String newFileName = scan.nextLine();
        messageEncoding(pathToFile, newFileName);


    }

    public static void messageEncoding(String pathToFile, String fileName) {
        String text = thisMyText(pathToFile);
        Alphabet alphabet = getMyAlpha(text);
        calculateProbability(alphabet, text);
        HuffmanTree tree = buildATree(alphabet);
        mainAlphabet = buildCodeTable(tree);
        createLink(pathToFile);
        File compressedFile = new File(pathToNewFile + fileName + ".txt");
        try (FileWriter writer = new FileWriter(compressedFile, false)){
            encoding(mainAlphabet, text, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String thisMyText(String fileName) {
        return Reader.readTxtFile(fileName).toLowerCase(Locale.ROOT);
    }

    public static Alphabet getMyAlpha(String text) {
        Alphabet myAlpha = new Alphabet(new ArrayList<>());
        text = getCorrectText(text);
        ArrayList<String> wordList = new ArrayList<>(Arrays.asList(text.split(" ")));
        Set<String> uniqueValues = new HashSet<>(wordList);
        for (String word : uniqueValues) {
            int count = Collections.frequency(wordList, word);
            Element el = new Element(word, count);
            myAlpha.symbols.add(el);
        }
        return myAlpha;
    }

    public static String getCorrectText(String text) {
        ArrayList<String> symbolList = new ArrayList<>(Arrays.asList(text.split("")));
        ArrayList<String> sign = new ArrayList<>(Arrays.asList(",", ".", "-", "(", ")", "—", "\"", "'"));
        symbolList.removeAll(sign);
        textForEncoding = String.join("", symbolList);
        return textForEncoding;
    }

    public static void calculateProbability(Alphabet alpha, String text) {
        for (Element el : alpha.symbols) {
            el.frequency = el.count * 1.0 / text.length();
        }
    }

    public static HuffmanTree buildATree(Alphabet myAlpha) {
        Queue<HuffmanTree> trees = new PriorityQueue<>();
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
        Node firstInQue = Objects.requireNonNull(trees.poll()).root;
        Node secondInQue = Objects.requireNonNull(trees.poll()).root;
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
        if (node.leftChild != null) {
            code = code + "0";
            return searchLeafNode(node.leftChild, code);
        } else if (node.rightChild != null) {
            code = code + "1";
            return searchLeafNode(node.rightChild, code);
        } else if (node.value != null) {
            Node parent = node.parent;
            String lastInput = code.split("")[code.length() - 1];
            if (lastInput.equals("1")) {
                parent.rightChild = null;

            } else {
                parent.leftChild = null;
            }
        }
        if (node.value == null) {
            Node parent = node.parent;
            if (code.length() > 0) {
                String lastInput = code.split("")[code.length() - 1];
                if (lastInput.equals("1")) {
                    parent.rightChild = null;
                    code = code.substring(0, code.length() - 1);
                    return searchLeafNode(parent, code);

                } else if (lastInput.equals("0")) {
                    parent.leftChild = null;
                    code = code.substring(0, code.length() - 1);
                    return searchLeafNode(parent, code);
                }
            }
        }
        return new String[]{node.value, code};
    }



    public static void createLink(String path){
        ArrayList<String> list  = new ArrayList<>(Arrays.asList(path.split(" ")));
        list.remove(list.get(list.size() - 1 ));
        pathToNewFile = String.join("\\", list);
    }


    public static void encoding(Map<String, String> binaryForm, String text, FileWriter writer) {
        text = getCorrectText(text);
        ArrayList<String> encoder = new ArrayList<>(Arrays.asList(text.split(" ")));
        String encodingMessage = encoder.stream().map(o1 -> binaryForm.get(o1) + " ").collect(Collectors.joining());
        try {
            writer.write(encodingMessage);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void messageDecoding(Map<String, String> normalForm, String path) {
        String text = thisMyText(path);
        ArrayList<String> decoder = new ArrayList<>(Arrays.asList(text.split(" ")));
        for (int i = 0; i < decoder.size(); i++) {
            for (Map.Entry<String, String> entry : normalForm.entrySet()) {
                if (entry.getValue().equals(decoder.get(i))) {
                    decoder.set(i, entry.getKey());
                }
            }
        }
        String decodingMessage = String.join(" ", decoder);
        System.out.println(decodingMessage);
    }

}