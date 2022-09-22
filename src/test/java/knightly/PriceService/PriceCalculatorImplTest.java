package knightly.PriceService;

import knightly.PriceService.service.impl.PriceCalculatorImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class PriceCalculatorImplTest {

    private PriceCalculatorImpl priceCalculatorImpl;

    @BeforeEach
    void setUp(){
        this.priceCalculatorImpl = new PriceCalculatorImpl();
    }

    @Test
    public void calculatepricesValid() {
        List<Integer> toCalculate = List.of(200, 2340, 3245,370);
        BigDecimal calculatedPrice = priceCalculatorImpl.calculatePrice(toCalculate);

        BigDecimal expectedPrice = new BigDecimal("6155");

        Assertions.assertEquals(expectedPrice, calculatedPrice);
    }

    @Test void calculatePricesNegative() {
        List<Integer> toCalculate = List.of(-50, 30, 70,-45);
        BigDecimal calculatedPrice = priceCalculatorImpl.calculatePrice(toCalculate);

        BigDecimal expectedPrice = new BigDecimal("5");

        Assertions.assertEquals(expectedPrice, calculatedPrice);
    }

    @Test
    public void calculatePriceMAXInteger() {
        List<Integer> toCalculate = List.of(Integer.MAX_VALUE, 100, 2,3);
        BigDecimal calculatedPrice = priceCalculatorImpl.calculatePrice(toCalculate);

        BigDecimal expectedPrice = new BigDecimal("2147483752");

        Assertions.assertEquals(expectedPrice, calculatedPrice);
    }

    @Test
    public void calculateEmptyList() {
        List<Integer> toCalculate = Collections.emptyList();
        BigDecimal calculatedPrice = priceCalculatorImpl.calculatePrice(toCalculate);

        BigDecimal expectedPrice = new BigDecimal("0");

        Assertions.assertEquals(expectedPrice, calculatedPrice);
    }
}
