package py.com.kyron.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import py.com.kyron.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

	/*
	 * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
	 * */
	
	@Query("SELECT p FROM Person p WHERE ruc = :ruc")
	public Optional<Person> getByRuc(String ruc);
}
