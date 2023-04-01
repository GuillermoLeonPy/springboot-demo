package py.com.kyron.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import py.com.kyron.entity.Person;
import py.com.kyron.exception.SpringBootDemoException;
import py.com.kyron.repository.PersonRepository;
import py.com.kyron.service.PersonService;

@SpringBootTest
class PersonServiceImplTest {

	@Autowired
	PersonService personService;
	
	@MockBean
	private PersonRepository personRepository;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		Person person = Person.builder().ruc("123").build();//build pattern		
		Mockito.when(personRepository.save(Mockito.any(Person.class))).thenReturn(person);
				
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@DisplayName("person creation test")
	//@Disabled //to skip the test
	final void testCreatePerson() throws SpringBootDemoException {
		 Person person = new Person();
		 person.setRuc("123");
		 person.setPersonalName("abc");
		 person.setPersonalLastName("def");
		 person.setPersonalEmailAddress("ab@ab.com");
		 Person personCreated = personService.createPerson(person);
		 assertEquals(person.getRuc(), personCreated.getRuc());
	}

}
