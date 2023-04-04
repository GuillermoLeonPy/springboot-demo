package py.com.kyron.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import py.com.kyron.entity.Person;
import py.com.kyron.exception.SpringBootDemoException;
import py.com.kyron.service.PersonService;

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
	public ResponseEntity<String> personByRabbitMqQueue(@Valid @RequestBody Person person) throws SpringBootDemoException {	
		LOGGER.info("## PersonController::" + "::person controller constant " + personControllerConstant + ":: profile constant " + personControllerProfileConstant + "::createPerson" + person);
		personService.createPersonByRabbitMqQueue(person);
		return ResponseEntity.ok("person creation message published to the Rabbit Mq queue");
	}
	
	@PostMapping("/createPersonByKafkaQueue")
	public ResponseEntity<String> createPersonByKafkaQueue(@Valid @RequestBody Person person) throws SpringBootDemoException {	
		LOGGER.info("## PersonController::" + "::person controller constant " + personControllerConstant + ":: profile constant " + personControllerProfileConstant + "::createPerson" + person);
		personService.createPersonByKafkaQueue(person);
		return ResponseEntity.ok("person creation message published to Kafka queue");
	}
}
	
