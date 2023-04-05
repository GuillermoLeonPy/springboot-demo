package py.com.kyron.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import py.com.kyron.entity.Person;
import py.com.kyron.exception.SpringBootDemoException;
import py.com.kyron.service.PersonService;
import java.util.ArrayList;
import java.util.Collection;

@RestController
public class PersonController {

	@Autowired
	private PersonService personService;
	
	@Value("${applicationconstants.controllerconstants.person}")
	private String personControllerConstant;
	
	@Value("${profileconstants.controllerconstants.person}")
	private String personControllerProfileConstant;
	
	private final Logger LOGGER = LoggerFactory.getLogger(PersonController.class);

	//http://localhost:8181/swagger-ui/index.html
    @Operation(summary = "This is to create person record in Db")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
            description = "person created in Db",
            content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
            description = "end point not available",
            content = @Content)
    })
	@PostMapping("/person")
    @ResponseStatus(HttpStatus.CREATED)
	public Person createPerson(@Valid @RequestBody Person person) throws SpringBootDemoException {
		LOGGER.info("## PersonController::" + "::person controller constant " + personControllerConstant + ":: profile constant " + personControllerProfileConstant + "::createPerson" + person);
		Person createdPerson = personService.createPerson(person);
		LOGGER.info("## PersonController::createPerson:: createdPerson " + createdPerson);
		return createdPerson;
	}
	
	@PostMapping("/personByRabbitMqQueue")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public String personByRabbitMqQueue(@Valid @RequestBody Person person) throws SpringBootDemoException {	
		LOGGER.info("## PersonController::" + "::person controller constant " + personControllerConstant + ":: profile constant " + personControllerProfileConstant + "::createPerson" + person);
		personService.createPersonByRabbitMqQueue(person);
		return "person creation message published to the Rabbit Mq queue";
	}
	
	@PostMapping("/createPersonByKafkaQueue")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public String createPersonByKafkaQueue(@Valid @RequestBody Person person) throws SpringBootDemoException {	
		LOGGER.info("## PersonController::" + "::person controller constant " + personControllerConstant + ":: profile constant " + personControllerProfileConstant + "::createPerson" + person);
		personService.createPersonByKafkaQueue(person);
		return "person creation message published to Kafka queue";
	}
	
	
	@PostMapping(value = "/createPersonByFiles", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public List<Person> createPersonByFiles(@RequestParam(value = "files") MultipartFile[] files) throws SpringBootDemoException {
		LOGGER.info("## PersonController::" + "::person controller constant " + personControllerConstant + ":: profile constant " + personControllerProfileConstant);
		List<CompletableFuture<List<Person>>> cfl = new ArrayList<>();
		CompletableFuture<List<Person>> cf = null;
		for (MultipartFile file : files) {			
			cf = personService.createPersonByFile(file);
			cfl.add(cf);			
		}
		
		CompletableFuture.allOf(cfl.toArray(new CompletableFuture[cfl.size()])).join();
		List<Person> result = new ArrayList<>();
		for(int i = 0; i < cfl.size(); i++) {
			try {
				result.addAll(cfl.get(i).get());	
			}catch (Exception e) {
				// TODO: handle exception
				LOGGER.error("error creation persons from file", e);
			}			
		}
		
		return result;
	}
}
	
