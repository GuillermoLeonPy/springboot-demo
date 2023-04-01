package py.com.kyron.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import py.com.kyron.entity.Person;
import py.com.kyron.exception.SpringBootDemoException;
import py.com.kyron.repository.PersonRepository;
import py.com.kyron.service.PersonService;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonRepository personRepository;
	
	@Override
	public Person createPerson(Person person) throws SpringBootDemoException {
		// TODO Auto-generated method stub
		
		Optional<Person> vPerson = personRepository.getByRuc(person.getRuc());
		
		if(vPerson.isPresent()) {
			throw new SpringBootDemoException("person with ruc: " + person.getRuc() + " already exists",HttpStatus.CONFLICT
					);
		}
		
		return personRepository.save(person);
	}

}