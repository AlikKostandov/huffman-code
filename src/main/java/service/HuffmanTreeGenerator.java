package service;

import entity.HuffmanTree;
import entity.Node;

import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Service for generate binary tree from alphabet.
 */
public class HuffmanTreeGenerator {

    public HuffmanTree generateHuffManTree(Map<Character, Double> alphabet) {
        Queue<HuffmanTree> trees = getSortedTreesQueue(alphabet);
        while (trees.size() > 1) {
            mergeTrees(trees);
        }
        return trees.peek();
    }

    private Queue<HuffmanTree> getSortedTreesQueue(Map<Character, Double> alphabet) {
        Queue<HuffmanTree> trees = new PriorityQueue<>();

        for (Map.Entry<Character, Double> dataForNode : alphabet.entrySet()) {
            Node node = new Node.Builder().
                    symbol(dataForNode.getKey())
                    .probability(dataForNode.getValue())
                    .build();
            HuffmanTree tree = new HuffmanTree(node);
            trees.add(tree);
        }

        return trees;
    }

    private static void mergeTrees(Queue<HuffmanTree> trees) {
        Node firstInQue = Objects.requireNonNull(trees.poll()).root;
        Node secondInQue = Objects.requireNonNull(trees.poll()).root;

        Node newNode = new Node.Builder()
                .probability(firstInQue.probability + secondInQue.probability)
                .leftChild(firstInQue)
                .rightChild(secondInQue)
                .build();

        firstInQue.parent = newNode;
        secondInQue.parent = newNode;

        HuffmanTree newTree = new HuffmanTree(newNode);
        trees.add(newTree);
    }
}
