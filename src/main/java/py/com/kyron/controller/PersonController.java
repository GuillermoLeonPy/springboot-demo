package py.com.kyron.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import py.com.kyron.entity.Person;
import py.com.kyron.exception.SpringBootDemoException;
import py.com.kyron.service.PersonService;

@RestController
public class PersonController {

	@Autowired
	private PersonService personService;
	
	private final Logger LOGGER = LoggerFactory.getLogger(PersonController.class);

	@PostMapping("/person")
	public Person createPerson(@Valid @RequestBody Person person) throws SpringBootDemoException {
		LOGGER.info("## PersonController::createPerson" + person);
		Person createdPerson = personService.createPerson(person);
		LOGGER.info("## PersonController::createPerson:: createdPerson " + createdPerson);
		return personService.createPerson(createdPerson);
	}
}
