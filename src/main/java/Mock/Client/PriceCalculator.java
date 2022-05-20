package Mock.Client;

public class PriceCalculator {

    public float calculatePrices(int[] priceArray) {
        int finalPrice = 0;

        for (int price : priceArray) {
            finalPrice += price;
        }

        return finalPrice;
    }
}
