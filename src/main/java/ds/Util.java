package ds;

import java.util.*;


public class Util {
    public static void main(String[] args) {
        Util util = new Util();
        //util.generateParenthesis(3);
        //util.combine(4, 2);
        //System.out.println(util.subsets(new int[]{1, 2, 3}));

        //System.out.println(util.letterCombinations("23"));

        System.out.println(util.ladderLength("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog")));
    }


    public List<List<Integer>> subsets(int[] nums) {
        //
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> set = new ArrayList<>();
        generateSubSet(nums, 0, set, res);

        return res;
        //
    }

    private void generateSubSet(int[] nums, int start, List<Integer> set, List<List<Integer>> res) {
        res.add(new ArrayList<>(set));
        for (int i = start; i < nums.length; i++) {
            set.add(nums[i]);
            generateSubSet(nums, i + 1, set, res);
            set.remove(set.size() - 1);
        }
    }


    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        generate(0, 0, n, "", result);
        return result;
    }

    private void generate(int left, int right, int n, String s, List<String> res) {
        if (left == n && right == n) {
            res.add(s);
            return;
        }

        if (left < n) {
            generate(left + 1, right, n, s + "(", res);
        }
        if (left > right) {
            generate(left, right + 1, n, s + ")", res);
        }
    }


    Map<String, String> phone = new HashMap<String, String>() {{
        put("2", "abc");
        put("3", "def");
        put("4", "ghi");
        put("5", "jkl");
        put("6", "mno");
        put("7", "pqrs");
        put("8", "tuv");
        put("9", "wxyz");
    }};

    public List<String> letterCombinations(String digits) {
        if (digits.length() > 0) {
            StringBuilder builder = new StringBuilder();
            generateCombination(builder, digits);
        }

        return result;
    }

    List<String> result = new ArrayList<String>();

    private void generateCombination(StringBuilder comb, String digits) {
        if (digits.length() == 0) {
            result.add(comb.toString());

        } else {
            String digit = digits.substring(0, 1);
            String letters = phone.get(digit);
            //phone.getOrDefault()
            for (int i = 0; i < letters.length(); i++) {
                comb.append(letters.charAt(i));
                generateCombination(comb, digits.substring(1));
                comb.deleteCharAt(comb.length() - 1);
            }
        }
    }

    int minStepCount = Integer.MAX_VALUE;

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        dfs(new HashSet<String>(), beginWord, endWord, wordList);
        return minStepCount == Integer.MAX_VALUE ? 0 : minStepCount;
    }

    private void dfs(HashSet<String> steps, String current, String endWord, List<String> wordList) {
        if (current.equals(endWord)) {
            minStepCount = Math.min(steps.size(), minStepCount);
        }
        for (String str : wordList) {
            int diff = 0;
            for (int i = 0; i < str.length(); i++) {
                if (current.charAt(i) != str.charAt(i)) {
                    diff++;
                    if (diff > 1) {
                        break;
                    }
                }
            }

            if (diff == 1 && !steps.contains(str)) {
                steps.add(str);
                dfs(steps, str, endWord, wordList);
                steps.remove(str);
            }
        }
    }

}
