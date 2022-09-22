package knightly.PriceService.server;

import com.google.gson.Gson;
import knightly.PriceService.server.dto.PriceReply;
import knightly.PriceService.server.dto.PriceRequest;
import knightly.PriceService.service.PriceCalculator;
import knightly.PriceService.service.impl.PriceCalculatorImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;


public class PriceServer {

    @Autowired
    PriceCalculator priceCalculatorImpl;
    private static final Logger logger = LoggerFactory.getLogger(PriceServer.class);

    @RabbitListener(queues = "${price.queue.name}")
    public String handlePriceRequest(String priceRequestString) {
        List<Integer> prices;
        try {
            PriceRequest priceRequest = convertJsonToPriceRequest(priceRequestString);
            prices = priceRequest.getPrices();
            logger.info("got list of prices" + prices.toString());
        } catch (NullPointerException e) {
            logger.error("Error unpacking pricerequest:" + this.getClass());
            return createErrorPricereplyJson();
        }
        try {
            PriceReply priceReply =  new PriceReply(this.priceCalculatorImpl.calculatePrice(prices));
            logger.info("returning:" + priceReply.getCalculatedPrice());
            return convertPriceReplyToJson(priceReply);
        } catch (Exception e) {
            logger.error("Error calculating Price in:" + this.getClass());
            return createErrorPricereplyJson();
        }
    }

    private PriceRequest convertJsonToPriceRequest(String json) {
        return new Gson().fromJson(json, PriceRequest.class);
    }

    private String convertPriceReplyToJson(PriceReply priceReply) {
        return new Gson().toJson(priceReply);
    }

    private String createErrorPricereplyJson(){
        return convertPriceReplyToJson(new PriceReply(new BigDecimal("0.00")));
    }
}
