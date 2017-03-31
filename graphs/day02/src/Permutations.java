import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Permutations {

    public static List<List<Integer>> permutations(List<Integer> A) {
        int size = A.size();
        List<List<Integer>> listOfLists = new ArrayList<>();
        Set<Integer> used = new HashSet<>();
        List<Integer> subList = new ArrayList<>();
        permutationsBacktrack(A, size, subList, listOfLists, used);
        return listOfLists;
    }

    private static void permutationsBacktrack(List<Integer> A, int size, List<Integer> subList, List<List<Integer>> listOfLists, Set<Integer> used) {
        if (size == 0) {
            List<Integer> copyList = new ArrayList<>();
            for (int v: subList) {
                copyList.add(v);
            }
            listOfLists.add(copyList);
            return;
        }
        for (int n : A) {
            if (!used.contains(n)) {
                subList.add(n);
                used.add(n);
                permutationsBacktrack(A, size-1, subList, listOfLists, used);
                used.remove(n);
                subList.remove(subList.size()-1);
            }
        }

    }

}
