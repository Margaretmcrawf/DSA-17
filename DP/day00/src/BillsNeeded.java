import java.util.HashMap;
import java.util.Map;

public class BillsNeeded {

    int[] billDenominations;
    Map<Integer, Integer> map;

    public int billsNeeded(int N, int[] billDenominations) {
        // TODO
        this.billDenominations = billDenominations;
        map = new HashMap<>();
        return billRecursive(N);
    }

    private int billRecursive(int N) {
        if (N == 0) {
            return 0;
        }
        else if (N < 0) {
            return -1;
        }

        else if (map.containsKey(N)) {
            return map.get(N);
        }
        int minval = Integer.MAX_VALUE;

        for (int denom : billDenominations) {
            if (denom == N) {
                return 1;
            }

            int value = billRecursive(N-denom);
            if ((value != -1) && (value < minval)){
                minval = value;
            }
            else if (value != -1) {
                map.put(N-denom, value);
            }
        }
        return minval+1;
    }

}
