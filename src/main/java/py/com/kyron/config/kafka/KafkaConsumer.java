package py.com.kyron.config.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import py.com.kyron.entity.Person;
import py.com.kyron.exception.SpringBootDemoException;
import py.com.kyron.service.PersonService;


@Slf4j
@Component
public class KafkaConsumer {

	@Value("${kafka.topic.person}")
	private String kafkaTopicPerson;
	
	@Autowired
	private PersonService personService;
	
    @KafkaListener(topics = "${kafka.topic.person}", groupId = "group_id", containerFactory = "myKafkaListenerContainerFactory")
    public void consume(Person message) throws SpringBootDemoException
    {
    	log.info("kafka message received : " + message);
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
