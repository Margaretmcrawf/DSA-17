import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CoinsOnAClock {

    public static List<char[]> coinsOnAClock(int pennies, int nickels, int dimes, int hoursInDay) {
        List<char[]> listOfListsCoins = new ArrayList<>();
        char[] currentList = new char[hoursInDay];
        int currentPoint = 0;
        Set<Integer> usedVals = new HashSet<>();
        coinsRecursive(pennies, nickels, dimes, hoursInDay, listOfListsCoins, currentList, currentPoint, usedVals);
        return listOfListsCoins;
    }

    private static void coinsRecursive(int pennies, int nickels, int dimes, int hoursInDay, List<char[]> listOfListsCoins, char[] currentList, int currentPoint, Set<Integer> usedVals) {
        if ((pennies == 0) && (dimes == 0) && (nickels == 0)) {
            char[] copyList = new char[hoursInDay];
            for (int i = 0; i < hoursInDay; i++) {
                copyList[i] = currentList[i];
            }
            listOfListsCoins.add(copyList);
            return;
        }

        if (pennies != 0) {
            currentList[currentPoint] = 'p';
            int newPoint = (currentPoint + 1) % hoursInDay;
            usedVals.add(currentPoint);
            if (!usedVals.contains(newPoint)) {
                coinsRecursive(pennies-1, nickels, dimes, hoursInDay, listOfListsCoins, currentList, newPoint, usedVals);
            } else if ((pennies == 1) && (dimes == 0) && (nickels == 0)) {
                coinsRecursive(pennies-1, nickels, dimes, hoursInDay, listOfListsCoins, currentList, newPoint, usedVals);
            }
            usedVals.remove(currentPoint);
        }

        if (nickels != 0) {
            currentList[currentPoint] = 'n';
            int newPoint = (currentPoint + 5) % hoursInDay;
            usedVals.add(currentPoint);
            if (!usedVals.contains(newPoint)) {
                coinsRecursive(pennies, nickels-1, dimes, hoursInDay, listOfListsCoins, currentList, newPoint, usedVals);
            }
            else if ((pennies == 0) && (dimes == 0) && (nickels == 1)) {
                coinsRecursive(pennies, nickels-1, dimes, hoursInDay, listOfListsCoins, currentList, newPoint, usedVals);
            }
            usedVals.remove(currentPoint);
        }

        if (dimes != 0) {
            currentList[currentPoint] = 'd';
            int newPoint = (currentPoint + 10) % hoursInDay;
            usedVals.add(currentPoint);
            if (!usedVals.contains(newPoint)) {
                coinsRecursive(pennies, nickels, dimes-1, hoursInDay, listOfListsCoins, currentList, newPoint, usedVals);
            }
            else if ((pennies == 0) && (dimes == 1) && (nickels == 0)) {
                coinsRecursive(pennies, nickels, dimes-1, hoursInDay, listOfListsCoins, currentList, newPoint, usedVals);
            }
            usedVals.remove(currentPoint);
        }

    }

}
