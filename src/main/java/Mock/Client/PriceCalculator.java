package Mock.Client;

import java.math.BigDecimal;
import java.util.List;

public class PriceCalculator {

    public static BigDecimal calculatePrice(List<Integer> prices) {
        BigDecimal finalPrice = new BigDecimal("0");

        for (int price : prices) {
            finalPrice = finalPrice.add(new BigDecimal(price));
        }

        return finalPrice;
    }
}
