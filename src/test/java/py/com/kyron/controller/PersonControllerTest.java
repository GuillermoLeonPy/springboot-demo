package py.com.kyron.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
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

	@MockBean
	private PersonService personService;
	
	@Autowired
	private MockMvc mockMvc;
	
	private Person person;

	@BeforeEach
	void setUp() throws Exception {		
		person = getPersonMockInstance();
	}


	@Test
	@DisplayName("person create test")
	@Disabled //to skip the test
	final void testCreatePerson() throws Exception {
		/*
		 * https://stackoverflow.com/questions/41168352/unit-test-post-with-webmvctest-mockbean-service-returns-null
		 *  the object you mock in the service call has to be identical to the object passed into the controller
		 * */
		Person inputPerson = getPersonMockInstance();
		Mockito.when(personService.createPerson(inputPerson)).thenReturn(person);
		mockMvc.perform(MockMvcRequestBuilders.post("/person").contentType(MediaType.APPLICATION_JSON)
				.content(getSampleJsonCreatePerson())).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	@DisplayName("person create test")
	//@Disabled //to skip the test
	final void testCreatePersonImproved() throws Exception {
		/*
		 * https://stackoverflow.com/questions/41168352/unit-test-post-with-webmvctest-mockbean-service-returns-null
		 *  the object you mock in the service call has to be identical to the object passed into the controller
		 * */
		Person inputPerson = getPersonMockInstance();
		Mockito.when(personService.createPerson(inputPerson)).thenReturn(person);
		mockMvc.perform(MockMvcRequestBuilders.post("/person").contentType(MediaType.APPLICATION_JSON).content(getSampleJsonCreatePerson()))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(jsonPath("$.ruc").value(person.getRuc()));
	}
	
	private Person getPersonMockInstance() {
		/*
		 * https://stackoverflow.com/questions/41168352/unit-test-post-with-webmvctest-mockbean-service-returns-null
		 *  the object you mock in the service call has to be identical to the object passed into the controller
		 * */
		return Person.builder().ruc("123458").personalName("Ray").personalEmailAddress("ray@test.com").build();
	}
	
	private String getSampleJsonCreatePerson() {
		/*
		 * https://stackoverflow.com/questions/41168352/unit-test-post-with-webmvctest-mockbean-service-returns-null
		 *  the object you mock in the service call has to be identical to the object passed into the controller
		 * */
		return "{\n"//real json from a performed call to the application
				+ "    \"id\": null,\n"
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
				+ "}";
	}

}
