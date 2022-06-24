package Mock.Client.server;

import Mock.Client.service.PriceCalculator;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;


public class PriceServer {

    private final PriceCalculator priceCalculator = new PriceCalculator();

    @RabbitListener(queues = "${price.queue.name}")
    public BigDecimal calculatePrice(List<Integer> prices) {
        try {
            return this.priceCalculator.calculatePrice(prices);
        } catch (Exception e) {
            e.printStackTrace();
            return new BigDecimal("0.00");
        }
    }
}
