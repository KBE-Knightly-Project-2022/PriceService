package knightly.PriceService.service;

import java.math.BigDecimal;
import java.util.List;

public interface PriceCalculator {

    public BigDecimal calculatePrice(List<Integer> prices);
}
