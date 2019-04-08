/**
 *
 */
public class BespokeStack {

    public static class MyStack<T extends Comparable<T>> {

        private static class StackNode<T> {

            T value;
            T minValue;
            StackNode nextElement;

            public StackNode(T value,
                             T minValue,
                             StackNode nextElement) {
                this.value = value;
                this.minValue = minValue;
                this.nextElement = nextElement;
            }
        }

        private StackNode top = null;

        public void push(T value) {

            if (top == null) {
                top = new StackNode<>(value, value, null);
            } else {
                T minValue = (T)top.minValue;
                if (value.compareTo(minValue) < 0) {
                    top = new StackNode<>(value, value, top);
                } else {
                    top = new StackNode<>(value, minValue, top);
                }
            }
        }

        public T pop() {

            if (top == null) {
                return null;
            }

            T tmp = (T)top.value;
            top = top.nextElement;
            return tmp;
        }

        public T getMin() {

            if (top == null) {
                return null;
            }
            return (T)top.minValue;
        }
    }

    public static void main(String argc[]) {

        MyStack<Integer> integerMyStack = new MyStack<>();

        for (int i = 0; i < 10; i++) {
            integerMyStack.push(i);
        }

        for (int i = 0; i < 5; i++) {
            System.out.println("popped " + integerMyStack.pop());
        }
        System.out.println("The stack's min value equals " + integerMyStack.getMin());

        MyStack<Character> charMyStack = new MyStack<>();
        String str = "hahppasdasih";
        char[] strArray = str.toCharArray();
        for (int i = 0; i < strArray.length; i++) {
            charMyStack.push(strArray[i]);
        }

        for (int i = 0; i < 7; i++) {
            System.out.println("popped " + charMyStack.pop());
        }
        System.out.println("The stack's min value equals " + charMyStack.getMin());
    }
}
