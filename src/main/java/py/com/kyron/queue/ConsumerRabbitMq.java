package py.com.kyron.queue;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import py.com.kyron.entity.Person;
import py.com.kyron.exception.SpringBootDemoException;
import py.com.kyron.service.PersonService;

@Slf4j
@Component
public class ConsumerRabbitMq {

	@Autowired
	private PersonService personService;
	
	@RabbitListener(queues = { "${kyron.rabbit.mq.queue.name}" })
	public void receive(@Payload Person message) throws SpringBootDemoException {

		log.info("Received message {}", message);		
		makeSlow();
		Person createdPerson = personService.createPerson(message);
		log.info("## ConsumerRabbitMq::receive:: person created" + createdPerson);
	}

	private void makeSlow() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			log.error("error", e);
		}
	}
}
