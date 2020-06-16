package ds.backtrace;


import java.util.ArrayList;
import java.util.List;

public class LetterPermutation {

    public static void main(String[] args) {
        System.out.println(permutation("abc"));
    }

    private static List<String> permutation(String letters) {
        List<String> res = new ArrayList<>();
        generatePermutation(new ArrayList<>(), 0, res, letters.toCharArray());
        return res;
    }

    private static void generatePermutation(List<Character> trace, int start, List<String> res, char[] letters) {
        if (trace.size() == letters.length) {
            res.add(convert(trace));
        }
        for(int i = start; i < letters.length; i++){
            trace.add(letters[i]);
            generatePermutation(trace, i + 1, res, letters);
            trace.remove(trace.size() - 1);

            if (Character.isLetter(letters[i])){
                char c = Character.isLowerCase(letters[i]) ? Character.toUpperCase(letters[i]): Character.toLowerCase(letters[i]);
                trace.add(c);
                generatePermutation(trace, i + 1, res, letters);
                trace.remove(trace.size() - 1);
            }
        }
    }

    private static String convert(List<Character> trace){
        StringBuilder builder = new StringBuilder();
        for (Character c : trace) {
            builder.append(c);
        }
        return builder.toString();
    }
}
