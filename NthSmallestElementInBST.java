import java.util.Stack;

/**
 * Find the nth smallest element in a binary search tree.
 *
 * Created by dhssn on 2/20/17.
 */
public class NthSmallestElementInBST {

    public class Node {
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

        NthSmallestElementInBST nthSmallestElementInBST = new NthSmallestElementInBST();
        Node root = nthSmallestElementInBST.buildBST();

        int n = 7;
        Node nthElement = nthSmallestElementInBST.findNthSmallestElementRec(root, n);
        nthSmallestElementInBST.printResult(n, nthElement);
    }

    public void printResult(int n, Node nthElement) {
        System.out.println(String.format("%s th element has the value %s", n, nthElement.value));
    }

    public Node buildBST() {

        Node root = new Node(5,
                new Node(4, new Node(3, new Node(2, null, null), null), new Node(5, null, null)),
                new Node(10, new Node(9, new Node(8, new Node(7, null, null), null), null), new Node(11, null, null)));
        return root;
    }

    public Node findNthSmallestElement(Node root, int n) {

        if (n < 1 || root == null) {
            return null;
        }
        Stack<Node> s = new Stack<>();
        //s.push(root);
        int count = 0;
        Node node = root;

        while (!s.isEmpty() || node != null) {

            if (!s.isEmpty()) {
                System.out.println(String.format("%s at the top of the stack", s.peek().value));
            }
            if (node != null) {
                s.push(node);
                node = node.left;
            } else {
                node = s.pop();
                if (++count == n) {
                    return node;
                }
                node = node.right;
            }
        }
        return null;
    }

    public class IntRef {
        private int num;

        public IntRef(int num) {
            this.num = num;
        }
        public int getNum() {
            return this.num;
        }
        public void increment(int num) {
            this.num += num;
        }
    }

    public class SimpleIntRef {
        public int num;
        public SimpleIntRef(int num) { this.num = num; }
    }

    public Node findNthSmallestElementRec(Node root, int n) {

        if (n < 1 || root == null) {
            return null;
        }
        return nthSmallestElement(root, n, new SimpleIntRef(0));
    }

    private Node nthSmallestElement(Node node, int n, SimpleIntRef count) {

        if (node == null) {
            return null;
        }

        Node tmp = nthSmallestElement(node.left, n, count);

        if (tmp != null) {
            return tmp;
        }

        count.num += 1;
        if (count.num == n) {
            return node;
        }

        return nthSmallestElement(node.right, n, count);
    }
}
