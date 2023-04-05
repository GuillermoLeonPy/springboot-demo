package py.com.kyron.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import py.com.kyron.entity.Person;
import py.com.kyron.exception.SpringBootDemoException;
import py.com.kyron.queue.PublisherRabbitMq;
import py.com.kyron.repository.PersonRepository;
import py.com.kyron.service.PersonService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.ArrayList;

@Service
public class PersonServiceImpl implements PersonService {

	private static final Logger log = LoggerFactory.getLogger(PersonServiceImpl.class);
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private PublisherRabbitMq publisherRabbitMq;
	
    @Autowired
    private KafkaTemplate<String,Person> kafkaTemplate;
	@Value("${kafka.topic.person}")
	private String kafkaTopicPerson;	
	
	@Override
	public Person createPerson(Person person) throws SpringBootDemoException {
		// TODO Auto-generated method stub
		
		Optional<Person> vPerson = personRepository.getByRuc(person.getRuc());
		
		if(vPerson.isPresent()) {
			throw new SpringBootDemoException("person with ruc: " + person.getRuc() + " already exists",HttpStatus.CONFLICT
					);
		}
		
		return personRepository.save(person);
	}

	@Override
	public void createPersonByRabbitMqQueue(Person person) throws SpringBootDemoException {
		// TODO Auto-generated method stub
		publisherRabbitMq.sendPerson(person);		
	}

	@Override
	public void createPersonByKafkaQueue(Person person) throws SpringBootDemoException {
		// TODO Auto-generated method stub
		kafkaTemplate.send(kafkaTopicPerson, person);
	}

	@Override
	@Async("asyncExecutor")
	public CompletableFuture<List<Person>> createPersonByFile(MultipartFile file) throws SpringBootDemoException {
		// TODO Auto-generated method stub		
		log.info("get list of users by "+Thread.currentThread().getName());
		List<Person> persons = personRepository.saveAll(parseCSVFile(file));
		try {Thread.sleep(3000);}catch (Exception e) {}		
		return CompletableFuture.completedFuture(persons);
	}
	
    private List<Person> parseCSVFile(final MultipartFile file) throws SpringBootDemoException {
        final List<Person> persons = new ArrayList<>();
        try {
            try (final BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                String line;
                while ((line = br.readLine()) != null) {
                    final String[] data = line.split(",");
                    final Person person = new Person();
                    person.setRuc(data[0]);
                    person.setPersonalName(data[1]);
                    //person.setPersonalEmailAddress(data[2]);
                    persons.add(person);
                }
                return persons;
            }
        } catch (final IOException e) {
        	log.error("Failed to parse CSV file {}", e);
            throw new SpringBootDemoException("Failed to parse CSV file");
        }
    }

}
