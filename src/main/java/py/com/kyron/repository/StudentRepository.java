package py.com.kyron.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import py.com.kyron.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

	/*
	 * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
	 * */
	
	
	//findBy + {entity bean property name}
	List<Student> findByFirstName(String firstName);
	
	//containig = like
	List<Student> findByFirstNameContaining(String name);
	
	//notNull = is not null
	List<Student> findByLastNameNotNull();
	
	//property Guardian and in Guardian property name
	List<Student> findByGuardianName(String guardianName);
	
    Student findByFirstNameAndLastName(String firstName,
            String lastName);
    
    //JPQL; not native sql
    @Query("select s from Student s where s.emailId = ?1")
    Student getStudentByEmailAddress(String emailId);
    
    //Native SQL
    @Query(
            value = "SELECT * FROM t_student s where s.email_id = ?1",
            nativeQuery = true
    )
    Student getStudentByEmailAddressNative(String emailId);
    
    //Native Named Param
    @Query(
            value = "SELECT * FROM t_student s where s.email_id = :emailId",
            nativeQuery = true
    )
    Student getStudentByEmailAddressNativeNamedParam(
            @Param("emailId") String emailId
    );

    @Modifying
    @Transactional
    @Query(
            value = "update t_student set first_name = ?1 where email_id = ?2",
            nativeQuery = true
    )
    int updateStudentNameByEmailId(String firstName, String emailId);
}
