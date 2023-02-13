package ru.drogunov.springcource.util.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.drogunov.springcource.dao.PersonDAO;
import ru.drogunov.springcource.model.Person;

import java.util.Optional;

@Component
public class PersonValidator implements Validator {
    private final PersonDAO personDAO;
    private Optional<Person> personFromDb;
    
    @Autowired
    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }
    
    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }
    
    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        personFromDb = personDAO.show(person.getEmail());
        if (personFromDb.isPresent()
                && person.getId() != personFromDb.get().getId()
        ) {
            errors.rejectValue("email", "", "This email is already taken");
        }
    }
}








