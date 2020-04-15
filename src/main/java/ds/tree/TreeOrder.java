package ds.tree;

import java.util.*;
import java.util.stream.Collectors;

public class TreeOrder {
    public static void main(String[] args) {
        TreeNode node = new TreeNode(3);
        node.left = new TreeNode(9);
        node.right = new TreeNode(20);
        node.right.left = new TreeNode(15);
        node.right.right = new TreeNode(7);

        System.out.println(levelOrder(node));
        System.out.println(levelOrderBottom(node));
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static List<List<Integer>> levelOrderBottom(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        ArrayDeque<List<Integer>> results = new ArrayDeque<>();

        if (root == null) {
            return null;
        }

        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node;
            List<TreeNode> nodes = new ArrayList<>();
            while ((node = queue.poll()) != null) {
                nodes.add(node);
            }
            List<Integer> result = new ArrayList<>();
            for (TreeNode e : nodes) {
                result.add(Integer.valueOf(e.val));
                if (e.left != null) {
                    queue.offer(e.left);
                }
                if (e.right != null) {
                    queue.offer(e.right);
                }
            }
            results.addFirst(result);
        }

        return results.stream().collect(Collectors.toList());
    }

    public static List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        List<List<Integer>> results = new ArrayList<>();

        if (root == null) {
            return results;
        }

        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node;
            List<TreeNode> nodes = new ArrayList<>();
            while ((node = queue.poll()) != null) {
                nodes.add(node);
            }
            List<Integer> result = new ArrayList<>();
            for (TreeNode e : nodes) {
                result.add(Integer.valueOf(e.val));
                if (e.left != null) {
                    queue.offer(e.left);
                }
                if (e.right != null) {
                    queue.offer(e.right);
                }
            }
            results.add(result);
        }
        return results;
    }
}
