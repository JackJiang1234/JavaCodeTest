package ds.backtrace;

import java.util.ArrayList;
import java.util.List;

public class LetterCasePermutation {

    public static void main(String[] args) {
        System.out.println(letterCasePermutation("a1b2"));
    }

    static List<String> list;

    public static List<String> letterCasePermutation(String S) {
        list = new ArrayList<>();
        recurse(S.toCharArray(), 0);
        return list;
    }

    public static void recurse(char[] chars, int idx){
        if(idx == chars.length){
            list.add(new String(chars));
            return;
        }
        recurse(chars, idx + 1);
        if(Character.isLetter(chars[idx])){
            chars[idx] = Character.isLowerCase(chars[idx]) ? Character.toUpperCase(chars[idx]): Character.toLowerCase(chars[idx]);
            recurse(chars, idx + 1);
        }
    }
}
