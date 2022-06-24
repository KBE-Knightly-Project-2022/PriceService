package Mock.Client.config;

import Mock.Client.server.PriceServer;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Value("${routing.key.price.service}")
    private String routingKeyPriceService;

    @Value("${price.queue.name}")
    private String priceQueueName;

    @Value("${xchange.name}")
    private String directXchangeName;

    @Bean
    public Queue queue() {
        return new Queue(priceQueueName);
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(directXchangeName);
    }

    @Bean
    public Binding binding(DirectExchange exchange, Queue queue) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKeyPriceService);
    }

    @Bean
    public PriceServer server() {
        return new PriceServer();
    }
}
