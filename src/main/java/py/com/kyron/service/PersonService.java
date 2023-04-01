package py.com.kyron.service;

import py.com.kyron.entity.Person;
import py.com.kyron.exception.SpringBootDemoException;

public interface PersonService {

	public Person createPerson(Person person) throws SpringBootDemoException;
}
