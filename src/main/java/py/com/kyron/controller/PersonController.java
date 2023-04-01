package py.com.kyron.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
	
	@Value("${applicationconstants.controllerconstants.person}")
	private String personControllerConstant;
	
	@Value("${profileconstants.controllerconstants.person}")
	private String personControllerProfileConstant;
	
	private final Logger LOGGER = LoggerFactory.getLogger(PersonController.class);

	@PostMapping("/person")
	public Person createPerson(@Valid @RequestBody Person person) throws SpringBootDemoException {
		LOGGER.info("## PersonController::" + "::person controller constant " + personControllerConstant + ":: profile constant " + personControllerProfileConstant + "::createPerson" + person);
		Person createdPerson = personService.createPerson(person);
		LOGGER.info("## PersonController::createPerson:: createdPerson " + createdPerson);
		return createdPerson;
	}
}
