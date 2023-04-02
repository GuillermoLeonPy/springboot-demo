package py.com.kyron.repository;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import py.com.kyron.entity.Guardian;
import py.com.kyron.entity.Student;

@SpringBootTest
//@DataJpaTest//when declared the database will not be impacted
class StudentRepositoryTest {

	private final Logger LOGGER = LoggerFactory.getLogger(StudentRepositoryTest.class);
	
	@Autowired
	private StudentRepository studentRepository;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@Disabled //to skip the test
	final void saveStudent() {
		studentRepository.save(getStudent("test@test.com"));
	}
	
    @Test
    @Disabled //to skip the test
    public void printAllStudent() {
        List<Student> studentList =
                studentRepository.findAll();

        LOGGER.info("studentList = " + studentList);
    }
    
    @Test
    @Disabled //to skip the test
    public void printStudentByFirstName() {
        
        List<Student> students =
                studentRepository.findByFirstName("test");

        LOGGER.info("students by name test :: " + students);
    }
    
    @Test
    @Disabled //to skip the tests
    public void printStudentByFirstNameContaining() {

        List<Student> students =
                studentRepository.findByFirstNameContaining("est");

        LOGGER.info("students by name like :: " + students);
    }
    
    @Test
    @Disabled //to skip the test
    public void printStudentBasedOnGuardianName(){
        List<Student> students =
                studentRepository.findByGuardianName("test");
        LOGGER.info("students by guardian name :: " + students);
    }
    
    @Test
    @Disabled //to skip the test
    public void printgetStudentByEmailAddress() {
        Student student =
                studentRepository.getStudentByEmailAddress(
                        "test@test.com"
                );

        LOGGER.info("student = " + student);
    }
    
    @Test
    //@Disabled //to skip the test
    public void printgetStudentByEmailAddressNative(){
        Student student =
                studentRepository.getStudentByEmailAddressNative(
                        "test@test.com"
                );

        LOGGER.info("student = " + student);
    }

    @Test
    //@Disabled //to skip the test
    public void printgetStudentByEmailAddressNativeNamedParam() {
        Student student =
                studentRepository.getStudentByEmailAddressNativeNamedParam(
                        "test@test.com"
                );

        LOGGER.info("student = " + student);
    }

    @Test
    //@Disabled //to skip the test
    public void updateStudentNameByEmailIdTest() {
        studentRepository.updateStudentNameByEmailId(
                "mathew",
                "test@test.com");
    }
    
	private Student getStudent(String emailId) {
		return Student.builder()
		.emailId(UUID.randomUUID().toString().substring(0, 3) + emailId)
		.firstName("test")
		.lastName("test")
		.guardian(
				Guardian.builder()
				.email(UUID.randomUUID().toString().substring(0, 3) + emailId)
				.name("test")
				.mobile("123").build())
		.build();
	}
}
