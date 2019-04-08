import java.util.ArrayList;
import java.util.List;

/**
 * Question: Given a binary tree, return the inorder traversal of its nodes' values.
 *
 * Example:
 * Input: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * Output: [1,3,2]
 *
 * Source: https://leetcode.com/problems/binary-tree-inorder-traversal/
 */
class BinaryTreeTraversal {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public void dfs(TreeNode node, List<Integer> ret) {
        if (node == null) {
            return;
        }
        dfs(node.left, ret);
        ret.add(node.val);
        dfs(node.right, ret);
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ret = new ArrayList<Integer>();
        dfs(root, ret);
        return ret;
    }
}
