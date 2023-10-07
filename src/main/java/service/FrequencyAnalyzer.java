package service;

import java.util.HashMap;
import java.util.Map;

/**
 * Service for frequency analysis of text.
 */
public class FrequencyAnalyzer {

    public Map<Character, Double> getResultOfFrequencyAnalysis(String text) {
        Map<Character, Double> alphabet = new HashMap<>();

        for (Character curChar : text.toCharArray()) {
            if (alphabet.containsKey(curChar)) {
                alphabet.put(curChar, alphabet.get(curChar) + 1);
            } else {
                alphabet.put(curChar, 1.0);
            }
        }

        calculateProbability(alphabet, text.length());
        return alphabet;
    }

    private void calculateProbability(Map<Character, Double> alphabet, int textLength) {
        for (Map.Entry<Character, Double> symbol : alphabet.entrySet()) {
            alphabet.put(symbol.getKey(), alphabet.get(symbol.getKey()) / textLength);
        }
    }
}
