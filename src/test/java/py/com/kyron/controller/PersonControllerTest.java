package py.com.kyron.controller;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import py.com.kyron.entity.Person;
import py.com.kyron.service.PersonService;

@WebMvcTest(PersonController.class)
class PersonControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private PersonService personService;
	
	private Person person;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {		
		person = Person.builder().ruc("123458").id(1).build();//persisted person will have id property value
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	final void testCreatePerson() throws Exception {
		Person inputPerson = Person.builder().ruc("123458").build();
		Mockito.when(personService.createPerson(inputPerson)).thenReturn(person);
		mockMvc.perform(MockMvcRequestBuilders.post("/person").contentType(MediaType.APPLICATION_JSON)
				.content("{\n"//real json from a performed call to the application
						+ "    \"id\": 4,\n"
						+ "    \"ruc\": \"123458\",\n"
						+ "    \"personalName\": \"Ray\",\n"
						+ "    \"personalLastName\": null,\n"
						+ "    \"personalAddress\": null,\n"
						+ "    \"personalEmailAddress\": \"ray@test.com\",\n"
						+ "    \"personalTelephoneNumber\": null,\n"
						+ "    \"commercialName\": null,\n"
						+ "    \"creationUser\": null,\n"
						+ "    \"creationDate\": null,\n"
						+ "    \"lastUpdaterUser\": null,\n"
						+ "    \"lastUpdateDate\": null,\n"
						+ "    \"personalCivilIdDocument\": null\n"
						+ "}")).andExpect(MockMvcResultMatchers.status().isOk());
	}

}
