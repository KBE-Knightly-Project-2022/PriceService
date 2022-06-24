package Mock.Client;

import Mock.Client.service.PriceCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

public class PriceCalculatorTest {

    private PriceCalculator priceCalculator;

    @BeforeEach
    void setUp(){
        this.priceCalculator = new PriceCalculator();
    }

    @Test
    public void calculatepricesValid() {
        List<Integer> toCalculate = List.of(200, 2340, 3245,370);
        BigDecimal calculatedPrice = priceCalculator.calculatePrice(toCalculate);

        BigDecimal expectedPrice = new BigDecimal("6155");

        Assertions.assertEquals(expectedPrice, calculatedPrice);
    }

    @Test
    public void calculatePriceMAXInteger() {
        List<Integer> toCalculate = List.of(Integer.MAX_VALUE, 100, 2,3);
        BigDecimal calculatedPrice = priceCalculator.calculatePrice(toCalculate);

        BigDecimal expectedPrice = new BigDecimal("2147483752");

        Assertions.assertEquals(expectedPrice, calculatedPrice);
    }
}
