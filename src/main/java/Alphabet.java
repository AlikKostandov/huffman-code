import java.util.ArrayList;
import java.util.Comparator;

public class Alphabet {
    public ArrayList<Element> symbols;


    public Alphabet(ArrayList<Element> symbols) {
        this.symbols = symbols;
    }

    public static class SortByCount implements Comparator<Element>
    {
        @Override
        public int compare(Element o1, Element o2) {
            return o2.count - o1.count;
        }

}}
