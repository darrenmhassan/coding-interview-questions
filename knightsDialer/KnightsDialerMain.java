package knightsDialer;

public class KnightsDialerMain {

    public static void main(String[] args) {
        int numberOfHops = 4;
        for (int i = 0; i < 10; i++) {
            KnightsDialer knightsDialer = new KnightsDialer();
            int startingPos = i;
            System.out.println(
                    String.format("Distinct numbers starting from %s with %s hops: %s",
                            startingPos,
                            numberOfHops,
                            knightsDialer.getN(startingPos, numberOfHops)));
            System.out.println("Path: " + knightsDialer.getPath());
        }
    }
}
