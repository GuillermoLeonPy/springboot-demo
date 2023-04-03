package py.com.kyron.queue.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PublisherConfigRabbitMq {

    @Value("${kyron.rabbit.mq.queue.name}")
    private String rabbitMqQueueName;

    @Bean
    public Queue queue() {
        return new Queue(rabbitMqQueueName, true);
    }

}
