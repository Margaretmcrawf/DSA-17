import java.util.InputMismatchException;

public class Stocks {

    int prices[];
    public int maxProfit(int[] prices) {
        this.prices = prices;
        int smallestBehindIndex = 0;
        int largestdiff = 0;
        for (int sellIndex = 0; sellIndex < prices.length; sellIndex++) {
            if (prices[sellIndex] - prices[smallestBehindIndex] > largestdiff) {
                largestdiff = prices[sellIndex] - prices[smallestBehindIndex];
            }
            if (prices[sellIndex] < prices[smallestBehindIndex]) {
                smallestBehindIndex = sellIndex;
            }

        }
        return largestdiff;
    }


    public int maxProfitWithK(int[] prices, int k) {
        // TODO: Optional
        return -1;
    }

}
