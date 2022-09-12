package Mock.Client.server;

import Mock.Client.service.PriceCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;


public class PriceServer {

    @Autowired
    PriceCalculator priceCalculator = new PriceCalculator();
    private static final Logger logger = LoggerFactory.getLogger(PriceServer.class);

    @RabbitListener(queues = "${price.queue.name}")
    public BigDecimal calculatePrice(List<Integer> prices) {
        try {
            return this.priceCalculator.calculatePrice(prices);
        } catch (Exception e) {
            logger.error("Error calculating Price in:" + this.getClass());
            return new BigDecimal("0.00");
        }
    }
}
