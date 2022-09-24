package knightly.PriceService.server;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import knightly.PriceService.server.PriceServer;
import knightly.PriceService.server.dto.PriceReply;
import knightly.PriceService.server.dto.PriceRequest;
import knightly.PriceService.service.PriceCalculator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class PriceServerTest {

    @InjectMocks
    PriceServer priceServer;
    @Mock
    PriceCalculator priceCalculator;

    @Test
    public void handlePriceRequest() {
        String priceRequestJson = getPriceRequest();

        priceServer.handlePriceRequest(priceRequestJson);

        verify(priceCalculator, times(1)).calculatePrice(any());
    }

    @Test
    public void handleFaultyPriceRequest() {
        String faultyPriceRquest = "This is not a proper request";

        priceServer.handlePriceRequest(faultyPriceRquest);

        verifyNoInteractions(priceCalculator);
    }

    @Test
    public void handleWrongDTO() {
        String wrongDTOJson = getWrongDTOJson();

        priceServer.handlePriceRequest(wrongDTOJson);

        verifyNoInteractions(priceCalculator);
    }

    private String getPriceRequest() {
        return new Gson().toJson(new PriceRequest(List.of(1, 3, 4)), PriceRequest.class);
    }

    public String getWrongDTOJson() {
        return new Gson().toJson(new PriceReply(new BigDecimal("2")), PriceReply.class );
    }


}
