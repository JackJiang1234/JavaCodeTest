package ds.stack;

import java.util.HashMap;
import java.util.Stack;

public class MatchTest {

    private Stack<Character> stack;
    private HashMap<Character, Character> symbols;

    public MatchTest() {
        this.stack = new Stack<>();
        symbols = new HashMap<>();
        symbols.put('(', ')');
        symbols.put('[', ']');
        symbols.put('{', '}');
    }

    public boolean isValid(String s) {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (symbols.containsKey(c)) {
                stack.push(c);
            } else {
                if (this.stack.empty()){
                    return false;
                }
                char expect = symbols.get(this.stack.pop());
                if (expect != c) {
                    return false;
                }
            }
        }
        return this.stack.empty();
    }
}
