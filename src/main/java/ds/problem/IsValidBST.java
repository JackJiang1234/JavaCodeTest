package ds.problem;

public class IsValidBST {

    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }

    private boolean isValidBST(TreeNode root, Integer lower, Integer upper) {
        if (root == null) {
            return true;
        }
        int val = root.val;

        //如果lower不为null，表示其左子树有值，当前结点值必须大于其左子树
        if (lower != null && val <= lower) {
            return false;
        }

        //如果upper不为null, 表示其右子树有值，当前结点值必须小于其右子树
        if (upper != null && val >= upper) {
            return false;
        }

        // 递归检查其左子树，所有左子树值必须小于当前结点, root.val 作为上界
        if (!isValidBST(root.left, lower, root.val)) {
            return false;
        }
        // 递归检查其右子树，所有右子树值必须大于当前结点, root.val 作为下界
        if (!isValidBST(root.right, root.val, upper)) {
            return false;
        }

        return true;
    }

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
