package entity;

import java.util.AbstractMap;
import java.util.Map;

/**
 * Pair of "symbol"-"binaryValue".
 */
public class SymbolBinaryPair {

    private final String symbol;

    private final String binary;

    public SymbolBinaryPair(String symbol, String binary) {
        this.symbol = symbol;
        this.binary = binary;
    }

    public Map.Entry<String, String> toMapEntry() {
        return new AbstractMap.SimpleEntry<>(symbol, binary);
    }

}