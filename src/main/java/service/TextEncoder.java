package service;

import entity.HuffmanTree;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Text encoder.
 */
public class TextEncoder {

    private final FrequencyAnalyzer frequencyAnalyzer;

    private final HuffmanTreeGenerator huffmanTreeGenerator;

    private final CodeTableFiller codeTableFiller;

    public TextEncoder() {
        this.frequencyAnalyzer = new FrequencyAnalyzer();
        this.huffmanTreeGenerator = new HuffmanTreeGenerator();
        this.codeTableFiller = new CodeTableFiller();
    }

    public String messageEncoding(String text) {
        Map<Character, Double> alphabet = frequencyAnalyzer.getResultOfFrequencyAnalysis(text);
        HuffmanTree tree = huffmanTreeGenerator.generateHuffManTree(alphabet);
        Map<String, String> codeTable = codeTableFiller.fillCodeTable(tree);

        return Arrays.stream(text.split(""))
                .map(codeTable::get)
                .collect(Collectors.joining());
    }
}
