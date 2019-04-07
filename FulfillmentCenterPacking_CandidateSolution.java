package iv;

import java.util.ArrayList;
import java.util.Arrays;

public class FulfillmentCenterPacking_CandidateSolution {

    public static boolean check_arr(ArrayList<Integer> arrival, ArrayList<Integer> departure, int k)
    {
        int first=0,dep=0,sec=0;

        for(int i = 1; i< arrival.size();i++) // size =3 , i=0
        {
            first = arrival.get(i-1); // 2
            dep = departure.get(i-1); // 4
            sec = arrival.get(i); // 2
            if(k>0) // 0
            {
                if(sec>=dep) // 2>=4
                {
                    //no issues
                }
                else
                {
                    k = k-1; // k=0
                }
            }
            else
            {
                //here capacity is zero, so cannot accomodate
                return false;

            }
        }//end of for
        return true;

    }

    public static void main(String[] args) {

        ArrayList<Integer> arrival = new ArrayList<>();
        arrival.addAll(Arrays.asList(1, 3, 5));
        ArrayList<Integer> departure = new ArrayList<>();
        departure.addAll(Arrays.asList(2, 6, 10));

        System.out.println(check_arr(arrival, departure, 1));
    }
}
