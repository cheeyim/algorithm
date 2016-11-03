import java.util.*;

/**
 * Created by cheeyim on 3/11/2016.
 */
public class LargestNumberDivisibleBy3 {

    public static void main(String[] args) {

        int[] j = {3, 1, 4, 1, 5, 9};

        System.out.println("Answer: " + answer(j));
    }

    public static int answer(int[] l) {
        List<Integer> intList = new ArrayList<Integer>();
        Queue<Integer> q0 = new PriorityQueue();
        Queue<Integer> q1 = new PriorityQueue();
        Queue<Integer> q2 = new PriorityQueue();
        boolean found = false;

        for (int index = 0; index < l.length; index++)
        {
            if (l[index] % 3 == 0)
                q0.add(l[index]);
            else if (l[index] % 3 == 1)
                q1.add(l[index]);
            else if (l[index] % 3 == 2)
                q2.add(l[index]);

            intList.add(l[index]);
        }

        Collections.sort(intList);
        found = remainderOfSumDivisibleBy3(intList) == 0;

        if (!found) {
            if (remainderOfSumDivisibleBy3(intList) == 1) {
                if (!q1.isEmpty()) {
                    q1.poll();
                } else if (q2.size() > 1) {
                    q2.poll();
                    q2.poll();
                } else
                    return 0;
            } else if (remainderOfSumDivisibleBy3(intList) == 2) {
                if (!q2.isEmpty()) {
                    q2.poll();
                } else if (q1.size() > 1) {
                    q1.poll();
                    q1.poll();
                } else
                    return 0;
            }

            //merge queue to intList
            intList = new ArrayList<Integer>(q0);
            intList.addAll(new ArrayList<Integer>(q1));
            intList.addAll(new ArrayList<Integer>(q2));

            Collections.sort(intList);
        }

        return getNumber(intList);
    }

    private static int remainderOfSumDivisibleBy3(List<Integer> l) {

        int sum = 0;

        for (int i =0; i<l.size(); i++) {
            sum += l.get(i);
        }

        return sum % 3;
    }

    private static int getNumber(List<Integer> l) {

        String result = "";

        for (int i= l.size() -1; i >= 0; i-- ) {
            result = result.concat(Integer.toString(l.get(i)));
        }

        return result.isEmpty() ? 0 : Integer.parseInt(result);

    }
}
