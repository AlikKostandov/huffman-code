package service;

import entity.HuffmanTree;
import entity.Node;
import entity.SymbolBinaryPair;

import java.util.HashMap;
import java.util.Map;

/**
 * CodeTable filler service.
 */
public class CodeTableFiller {

    public static final String ZERO = "0";

    public static final String ONE = "1";

    public Map<String, String> fillCodeTable(HuffmanTree tree) {
        Map<String, String> table = new HashMap<>();
        while ((tree.root.leftChild != null) || (tree.root.rightChild != null)) {

            StringBuilder code = new StringBuilder();
            Map.Entry<String, String> pair = searchLeafNode(tree.root, code);
            table.put(pair.getKey(), pair.getValue());

        }
        table.remove(null);
        return table;
    }

    private Map.Entry<String, String> searchLeafNode(Node node, StringBuilder binaryCode) {
        if (node.leftChild != null) {

            binaryCode.append(ZERO);
            return searchLeafNode(node.leftChild, binaryCode);

        } else if (node.rightChild != null) {

            binaryCode.append(ONE);
            return searchLeafNode(node.rightChild, binaryCode);

        } else if (node.symbol != null) {

            Node parent = node.parent;
            String lastInput = binaryCode.substring(binaryCode.length() - 1);

            if (lastInput.equals(ONE)) {
                parent.rightChild = null;
            } else {
                parent.leftChild = null;
            }

        }
        if (node.symbol == null) {

            Node parent = node.parent;
            if (!binaryCode.isEmpty()) {
                String lastInput = binaryCode.substring(binaryCode.length() - 1);

                if (lastInput.equals(ONE)) {

                    parent.rightChild = null;
                    binaryCode = new StringBuilder(binaryCode.substring(0, binaryCode.length() - 1));
                    return searchLeafNode(parent, binaryCode);

                } else {
                    parent.leftChild = null;
                    binaryCode = new StringBuilder(binaryCode.substring(0, binaryCode.length() - 1));
                    return searchLeafNode(parent, binaryCode);
                }
            }

        }
        return new SymbolBinaryPair(node.symbol != null ? node.symbol.toString() : "", binaryCode.toString()).toMapEntry();
    }
}
