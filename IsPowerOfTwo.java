import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class IsPowerOfTwo {

    public static void main(String[] arc) {
        System.out.println(isPowerOfTwo(536870912));
        System.out.println(isPowerOfTwo(5));
        System.out.println(findDisappearedNumbers(new int[] {4,3,2,7,8,2,3,1}));
    }

    public static boolean isPowerOfTwo(int n) {
        //double x = Math.log(n) / Math.log(2);
        //return (x * 10) % 10 == 0;
        if (n == 0) {
            return true;
        }
        double x = n / 2.0;
        while (x > 1.0 && ((x * 10) % 10 == 0)) {
            x = x / 2.0;
        }
        return (x * 10) % 10 == 0;
    }

    public static List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            res.add(i, i+1);
        }
        for (int i = 0; i < nums.length; i++) {
            res.set(nums[i] - 1, 0);
        }
        Iterator<Integer> it = res.iterator();
        while (it.hasNext()) {
            if (it.next() == 0) {
                it.remove();
            }
        }
        return res;
    }
}
