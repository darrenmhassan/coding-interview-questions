package recursion;

import java.time.Duration;

/**
 *
 */
public class FibNumbers {

    public static long findFib(long n) {
        if (n <= 1) {
            return n;
        }
        return findFib(n - 1)  + findFib(n - 2);
    }

    public static void main(String[] args) {
        for (long n = 48; n <= 51; n++) {
            long start = System.currentTimeMillis();
            long fibNum = findFib(n);
            long end = System.currentTimeMillis();
            Duration duration = Duration.ofMillis(end - start);
            System.out.println(String.format("Found the %s th fib number in %s: %s", n, duration, fibNum));
        }
    }
}
