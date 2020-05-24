package ds.tree;

import java.util.*;
import java.util.stream.Collectors;

public class TreeOrder {
    public static void main(String[] args) {
        /*
        TreeNode node = new TreeNode(3);
        node.left = new TreeNode(9);
        node.right = new TreeNode(20);
        node.right.left = new TreeNode(15);
        node.right.right = new TreeNode(7);

        System.out.println(levelOrder(node));
        System.out.println(levelOrderBottom(node));
        */

        TreeNode node = new TreeNode(1);
        node.right = new TreeNode(3);
        node.right.left = new TreeNode(2);

        System.out.println(getMinimumDifference(node));
    }

    public static int getMinimumDifference(TreeNode root) {
        tavelTree(root);
        return minDiff;
    }

    private static TreeNode pre;
    private static int minDiff = Integer.MAX_VALUE;
    private static void tavelTree(TreeNode root) {
        if (root == null){
            return;
        }
        tavelTree(root.left);
        if (pre != null){
           minDiff = Math.min(minDiff, root.val - pre.val);
        }
        pre = root;
        tavelTree(root.right);
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

    public static List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        List<TreeNode> previousLayer = Arrays.asList(root);
        while (!previousLayer.isEmpty()) {
            List<TreeNode> curLayer = new ArrayList<>();
            List<Integer> previousVals = new ArrayList<>();

            for (TreeNode node : previousLayer) {
                previousVals.add(node.val);
                //curLayer.addAll(node.children);
            }
            result.add(previousVals);
            previousLayer = curLayer;
        }

        return result;
    }


    // m - l - r
    public List<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node != null) {
                res.add(node.val);
                stack.push(node.right);
                stack.push(node.left);
            }
        }

        return res;
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        //左右中
        LinkedList<Integer> output = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node != null) {
                output.addFirst(node.val);
            }
            if (node.right != null) {
                stack.add(node.right);
            }
            if (node.left != null) {
                stack.add(node.left);
            }
        }

        return output;
    }


    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inorderTraversal(root, res);
        return res;
    }

    private void inorderTraversal(TreeNode root, List<Integer> res) {
        if (root != null) {
            inorderTraversal(root.left, res);
            res.add(root.val);
            inorderTraversal(root.right, res);
        }
    }
}
