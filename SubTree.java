import java.util.Stack;

/**
 * Write an algorithm to determine if a tree is a sub-tree of another tree.
 *
 *
 */
public class SubTree {

    public static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    public static class BooleanRef {
        boolean bool = false;

        public BooleanRef(boolean bool) {
            this.bool = bool;
        }
    }


    public static void main(String argc[]) {

        SubTree st = new SubTree();
        Node treeRoot = st.buildTree();
        Node subTreeRoot = st.buildSubTree();

        if (st.isSubTree(treeRoot, subTreeRoot)) {
            System.out.println("is sub tree");
        } else {
            System.out.println("is NOT sub tree");
        }
    }

    public Node buildTree() {

        Node root = new Node(5,
                new Node(4, new Node(3, new Node(2, null, null), null), new Node(5, null, null)),
                new Node(10, new Node(9, new Node(8, new Node(7, null, null), null), null), new Node(11, null, null)));
        return root;
    }

    public Node buildSubTree() {

        Node root = new Node(10, new Node(9, null, null), new Node(12, null, null));
        return root;
    }

    public boolean isSubTree(Node treeRoot, Node subTreeRoot) {

        Stack<Node> stack = new Stack();
        Node node = treeRoot;
        BooleanRef contains = new BooleanRef(false);
        while (!contains.bool && (!stack.isEmpty() || node != null)) {

            if (node != null) {
                if (node.value == subTreeRoot.value) {
                    contains.bool = true;
                    compareSubTrees(node, subTreeRoot, contains);
                }

                stack.push(node);
                node = node.left;
            } else {
                Node tmp = stack.pop();
                node = tmp.right;
            }
        }
        return contains.bool;
    }

    private BooleanRef compareSubTrees(Node treeNode, Node subTreeNode, BooleanRef contains) {

        if (subTreeNode == null) {
            return contains;
        }

        if (!contains.bool) {
            return contains;
        }

        if (treeNode == null || treeNode.value != subTreeNode.value) {
            contains.bool = false;
            return contains;
        }

        contains = compareSubTrees(treeNode.left, subTreeNode.left, contains);
        if (contains.bool) {
            contains = compareSubTrees(treeNode.right, subTreeNode.right, contains);
        }
        return contains;
    }
}
