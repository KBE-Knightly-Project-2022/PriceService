package knightly.PriceService.service.impl;

import knightly.PriceService.service.PriceCalculator;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PriceCalculatorImpl implements PriceCalculator {

    @Override
    public BigDecimal calculatePrice(List<Integer> prices) {
        BigDecimal finalPrice = new BigDecimal("0");

        for (int price : prices) {
            finalPrice = finalPrice.add(new BigDecimal(price));
        }

        return finalPrice;
    }
}
