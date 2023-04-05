package py.com.kyron.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.web.multipart.MultipartFile;

import py.com.kyron.entity.Person;
import py.com.kyron.exception.SpringBootDemoException;

public interface PersonService {

	public Person createPerson(Person person) throws SpringBootDemoException;
	public void createPersonByRabbitMqQueue(Person person) throws SpringBootDemoException;
	public void createPersonByKafkaQueue(Person person) throws SpringBootDemoException;
	public CompletableFuture<List<Person>> createPersonByFile(MultipartFile file)throws SpringBootDemoException;
}
