/**
 * Created by dhssn on 2/28/17.
 */
public class ElegantSubTree {


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


    public static void main(String argc[]) {

        ElegantSubTree st = new ElegantSubTree();
        Node treeRoot = st.buildTree();
        Node subTreeRoot = st.buildSubTree();

        if (st.containsTree(treeRoot, subTreeRoot)) {
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

    public boolean containsTree(Node t1, Node t2)
    {
        if(t2 == null)
            return true;
        return subTree(t1, t2);
    }

    private boolean subTree(Node t1, Node t2)
    {
        if (t1 == null)
            return false;
        if(t1.value == t2.value)
            if(matchTree(t1, t2))
                return true;
        return subTree(t1.left, t2) || subTree(t1.right, t2);
    }

    private boolean matchTree(Node t1, Node t2)
    {
        if (t1 == null && t2 == null)
            return true;
        if (t2 == null)
            return true;
        if (t1 == null)
            return false;
        if (t1.value != t2.value)
            return false;
        return (matchTree(t1.left, t2.left) && matchTree(t1.right, t2.right));
    }
}
