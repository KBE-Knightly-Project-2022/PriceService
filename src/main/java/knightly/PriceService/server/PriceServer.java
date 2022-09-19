package knightly.PriceService.server;

import knightly.PriceService.server.dto.PriceRequest;
import knightly.PriceService.service.PriceCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;


public class PriceServer {

    @Autowired
    PriceCalculator priceCalculator = new PriceCalculator();
    private static final Logger logger = LoggerFactory.getLogger(PriceServer.class);

    @RabbitListener(queues = "${price.queue.name}")
    public BigDecimal calculatePrice(PriceRequest priceRequest) {
        List<Integer> prices;
        try {
            prices = priceRequest.getPrices();
        } catch (NullPointerException e) {
            logger.error("Error unpacking pricerequest:" + this.getClass());
            return new BigDecimal("0.00");
        }
        try {
            return this.priceCalculator.calculatePrice(prices);
        } catch (Exception e) {
            logger.error("Error calculating Price in:" + this.getClass());
            return new BigDecimal("0.00");
        }
    }
}
