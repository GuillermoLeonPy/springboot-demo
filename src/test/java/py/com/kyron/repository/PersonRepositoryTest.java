package py.com.kyron.repository;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import py.com.kyron.entity.Person;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)//for the TestEntityManager to work
class PersonRepositoryTest {

	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private TestEntityManager testEntityManager;//for in memory testing, not to the real database
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		Person person = Person.builder().ruc("123").personalName("abc").personalEmailAddress("abc@abc.com").build();
		testEntityManager.persist(person);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@DisplayName("person query test")
	//@Disabled //to skip the test
	public void testGetByRuc() {
		Optional<Person> person = personRepository.getByRuc("123");
		assertEquals(person.get().getRuc(), "123");
	}

}
