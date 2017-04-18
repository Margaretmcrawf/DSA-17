import java.util.ArrayList;
import java.util.List;

public class TextJustification {

    public static List<Integer> justifyText(String[] w, int m) {
        List<Integer> lineLengths = new ArrayList<>();
        int index = 0;
        justifyRecursive(w, m, index, lineLengths);
        return lineLengths;
    }

    private static void justifyRecursive(String[] w, int m, int index, List<Integer> indices) {
        int sum = 0;
        for (int i = index; i < w.length; i++) {
            sum += w[i].length();
            if (sum >= m) {
                indices.add(index);
                justifyRecursive(w, m, i, indices);
                return;
            }
        }
        indices.add(index); //all of the remaining elements can be on one line, so just add it.
    }

}